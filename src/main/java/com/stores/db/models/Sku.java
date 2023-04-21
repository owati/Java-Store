package com.stores.db.models;

import com.stores.db.models.constants.SkuCategory;

public class Sku {
    // primary key
    private int id;

    // name of product
    private String name;

    // price of produce
    private int price;

    // quantity in stock
    private int stockQuantity;

    // category
    private SkuCategory category;

    // image
    private String image;

    public Sku(int id, String name, int price, int stockQuantity,
         SkuCategory category, String image) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public SkuCategory getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

}
