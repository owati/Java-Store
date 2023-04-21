package com.stores.db.models;

import java.util.ArrayList;

public class Orders {
    // primary key
    private int id;

    // user name
    private String name;

    // the total amount
    private int total;

    // the deleivery status
    private boolean delivered;

    // items
    private ArrayList<OrderItem> items;

    public Orders(int id, String name, int total, boolean delivered, ArrayList<OrderItem> items) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.delivered = delivered;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public boolean isDelivered() {
        return delivered;
    }

}
