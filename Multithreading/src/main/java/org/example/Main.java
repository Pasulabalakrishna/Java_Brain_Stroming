package org.example;


import java.util.Map;

public class Main {

    public static void main(String[] args) {
        WalletService wallet=new WalletService();
        InventoryService inventory=new InventoryService();
// initially we are setting balance and inventory
        wallet.addBalance("User1", 5000.0);
        wallet.addBalance("User2",10000.0);
        inventory.addStock("Product1",10);
        inventory.addStock("Product2",15);
//        System.out.println("after add balance and inventory");
        OrderProcess orderProcessor=new OrderProcess(wallet,inventory);
        Order order1=new Order("101","Product1","User1",2);
        Order order2=new Order("102","Product1","User2",2);
        Order order3=new Order("101","product1","User1",2);

        Order order4=new Order("103","product1","User1",2);

        orderProcessor.orderProcess(order1);
        orderProcessor.orderProcess(order2);
        orderProcessor.orderProcess(order3);
//        orderProcessor.orderProcess(order4);
        System.out.println("After Order");
        for (Map.Entry<String,Double> entry:wallet.walletBalances.entrySet()){
            System.out.println(entry.getKey()+"-->"+entry.getValue());
        }

    }
}