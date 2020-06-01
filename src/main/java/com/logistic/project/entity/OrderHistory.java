package com.logistic.project.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "order_history")
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_order_id")
    private Integer userOrderId;

    @Column(name = "finish_time")
    private Date finishTime;

    @Column(name = "comment")
    private String comment;

    @Column(name = "score")
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(Integer userOrderId) {
        this.userOrderId = userOrderId;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
