package com.stores.db.models;

public class OrderItem {
    private int id;

    private String product;

    private int total;

    private int quantity;

    public OrderItem(int id, String product, int total, int quantity) {
        this.id = id;
        this.product = product;
        this.total = total;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotal() {
        return total;
    }
}
