package com.logistic.project.dto;

import com.logistic.project.entity.Country;
import com.logistic.project.entity.PriceProfile;
import com.logistic.project.entity.RouteInfo;

public class PriceProfileDTO extends PriceProfile {

    private Country country;

    private RouteInfo routeInfo;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(RouteInfo routeInfo) {
        this.routeInfo = routeInfo;
    }
}
