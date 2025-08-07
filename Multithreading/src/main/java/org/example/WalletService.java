package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class WalletService {


     public final ConcurrentHashMap<String, Double> walletBalances=new ConcurrentHashMap<>();

    public void addBalance(String userId, Double amount){
        walletBalances.put(userId, amount);
        System.out.println("add money");
        for (Map.Entry<String,Double> entry:walletBalances.entrySet()){
            System.out.println(entry.getKey()+"-->"+entry.getValue());
        }
    }

    public synchronized boolean processPayment(String userId, double payment){
        double balance=walletBalances.get(userId);
//        System.out.println("balance---->"+balance);
        if(payment<=balance){
            walletBalances.put(userId,balance-payment);
//            System.out.println("After update money");
//            for (Map.Entry<String,Double> entry:walletBalances.entrySet()){
//                System.out.println(entry.getKey()+"-->"+entry.getValue());
//            }
            return true;
        }
        return false;
    }
}
