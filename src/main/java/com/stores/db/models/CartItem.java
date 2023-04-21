package com.stores.db.models;

public class CartItem {

    private final Sku product;
    private final int quantity;

    public CartItem(Sku product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Sku getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalCost() {
        return product.getPrice() * quantity;
    }
}
