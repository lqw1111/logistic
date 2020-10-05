package com.logistic.project.enumeration;

public enum PaymentType {

    WECHAT(1, "wechat"),
    ALIPAY(2, "alipay");

    private Integer id;
    private String name;

    PaymentType(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
