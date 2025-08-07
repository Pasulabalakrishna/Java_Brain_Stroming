package org.example.async_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;


public class FileProcessor {
    public static void main(String[] args) throws IOException {
        Path path=Paths.get("/Users/bpasula/JavaBrainstoming/Java_Brain_Stroming/Multithreading/src/main/resources/data.txt");
        System.out.println(path);
        List<String> allData=new ArrayList<>();

        if(!Files.exists(path)){
            System.out.println("file is not existed");
//           Files.createFile(path);
        }else{
            allData=Files.readAllLines(path);
            ForkJoinPool pool=new ForkJoinPool(10);
            List<List<String>> chunks=chunckList(allData,100);
           List<CompletableFuture<Boolean>> features= chunks.stream()
                    .map(chunk->CompletableFuture.supplyAsync(
                            ()-> {
                                chunkProcess(chunk);
                                return true;
                            },pool))
                    .collect(Collectors.toList());
            CompletableFuture.allOf(features.toArray(new CompletableFuture[0])).join();
            //this line ensures all tasks are completed other-wise main thread execute his work and says my work is done, then we get unexpected result

        }
    }

    private static void chunkProcess(List<String> chunk) {
     chunk.stream()
             .forEach(line-> System.out.println(line));
    }

    private static List<List<String>> chunckList(List<String> allData, int chunkSize) {
        List<List<String>> chucks=new ArrayList<>();

        for(int i=0;i<allData.size();i=i+chunkSize){
            chucks.add(allData.subList(i,Math.min(i+chunkSize, allData.size())));
        }
        return chucks;

    }
}
