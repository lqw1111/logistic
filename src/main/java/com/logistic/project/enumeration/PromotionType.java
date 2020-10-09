package com.logistic.project.enumeration;

public enum PromotionType {
    PRICE(2, "price"),
    DISCOUNT(1, "discount");

    private Integer id;
    private String type;

    PromotionType(Integer id, String type){
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }
}
