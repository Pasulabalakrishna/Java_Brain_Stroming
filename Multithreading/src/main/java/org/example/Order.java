package org.example;


public class Order  {
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;

    public Order(String orderId, String productId, String userId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public String getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }
}
