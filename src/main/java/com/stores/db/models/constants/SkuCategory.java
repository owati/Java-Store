package com.stores.db.models.constants;

public enum SkuCategory {
    SHIRTS("Shirts"),
    TROUSER("Trousers"),
    CAP("Cap"),
    SHOE("Shoe"),
    UNKNOWN("");



    private final String value;

    SkuCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SkuCategory fromString(String value) {
        for (SkuCategory category : SkuCategory.values()) {
            if (category.getValue().equals(value))
                return category;
        }

        return SkuCategory.SHIRTS;
    }
}
