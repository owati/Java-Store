package com.stores.db.models;

import java.util.ArrayList;

public class Cart extends ArrayList<CartItem>  {

    public int getTotalCost() {
        int total = 0;
        for (CartItem item : this) {
            total += item.getTotalCost();
        }

        return total;
    }

    @Override
    public int size() {
        int total = 0;
        for (CartItem item : this) {
            total += item.getQuantity();
        }

        return total;
    }
}
