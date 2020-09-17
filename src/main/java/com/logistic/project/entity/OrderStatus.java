package com.logistic.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
public class OrderStatus {

    public static final Integer NEW = 1;
    public static final Integer SUBMIT = 2;
    public static final Integer APPROVED = 3;
    public static final Integer CLOSED = 4;
    public static final Integer PROCESSING = 5;
    public static final Integer FINISH = 6;
    public static final Integer ISSUE = 7;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
