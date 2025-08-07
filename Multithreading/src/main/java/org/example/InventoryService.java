package org.example;

import java.util.concurrent.ConcurrentHashMap;

public class InventoryService {
  private ConcurrentHashMap<String,Integer> productStocks=new ConcurrentHashMap<>();

   public void addStock(String productId,int quantity){
       productStocks.put(productId,quantity);

    }
     public synchronized boolean updateInventory(String productId,int quantity){
        Integer stock=productStocks.get(productId);
         System.out.println("----->"+stock);
        if(stock>quantity){
            productStocks.put(productId,stock-quantity);
            return true;
        }
        return false;
    }
}
