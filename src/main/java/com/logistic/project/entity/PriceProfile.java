package com.logistic.project.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "price_profile")
public class PriceProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "first_weight_price")
    private BigDecimal firstWeightPrice;

    @Column(name = "continue_weight_price")
    private BigDecimal continueWeightPrice;

    @Column(name = "minimum_weight")
    private BigDecimal minimumWeight;

    @Column(name = "route_id")
    private Integer routeId;

    @Column(name = "weight_range_low")
    private BigDecimal weightRangeLow;

    @Column(name = "weight_range_high")
    private BigDecimal weightRangeHigh;

    public PriceProfile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public BigDecimal getFirstWeightPrice() {
        return firstWeightPrice;
    }

    public void setFirstWeightPrice(BigDecimal firstWeightPrice) {
        this.firstWeightPrice = firstWeightPrice;
    }

    public BigDecimal getContinueWeightPrice() {
        return continueWeightPrice;
    }

    public void setContinueWeightPrice(BigDecimal continueWeightPrice) {
        this.continueWeightPrice = continueWeightPrice;
    }

    public BigDecimal getMinimumWeight() {
        return minimumWeight;
    }

    public void setMinimumWeight(BigDecimal minimumWeight) {
        this.minimumWeight = minimumWeight;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public BigDecimal getWeightRangeLow() {
        return weightRangeLow;
    }

    public void setWeightRangeLow(BigDecimal weightRangeLow) {
        this.weightRangeLow = weightRangeLow;
    }

    public BigDecimal getWeightRangeHigh() {
        return weightRangeHigh;
    }

    public void setWeightRangeHigh(BigDecimal weightRangeHigh) {
        this.weightRangeHigh = weightRangeHigh;
    }

}
