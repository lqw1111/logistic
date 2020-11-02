package com.logistic.project.dto;

import java.util.List;

public class UserOrderWithParcelDTO extends UserOrderDTO {

    private List<ParcelDTO> parcels;

    public UserOrderWithParcelDTO(UserOrderDTO userOrderDTO, List<ParcelDTO> parcels) {
        super(userOrderDTO);
        this.parcels = parcels;
    }

    public List<ParcelDTO> getParcels() {
        return parcels;
    }

    public void setParcels(List<ParcelDTO> parcels) {
        this.parcels = parcels;
    }
}
