package org.example;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class OrderProcess   {

    WalletService walletService;
    InventoryService inventoryService;
    private Set<String> processedOrders = ConcurrentHashMap.newKeySet();

    public OrderProcess(WalletService walletService, InventoryService inventoryService) {
        this.walletService = walletService;
        this.inventoryService = inventoryService;
    }

    public void orderProcess(Order order){
        if(processedOrders.contains(order.getOrderId())){
            System.out.println("Already Processed");
            return;
        }
        processedOrders.add(order.getOrderId());
        Thread thread=new Thread(()->{
            boolean paymentStatus=  walletService.processPayment(order.getUserId(), order.getQuantity()*100);
            boolean inventoryStatus= inventoryService.updateInventory(order.getProductId(), order.getQuantity());
            System.out.println(paymentStatus+"<------->"+inventoryStatus);
            if(paymentStatus && inventoryStatus){
                System.out.println("Order Success");
            }else{
                System.out.println("Order failed");
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
