package com.logistic.project.service;

import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.exception.LogisticException;
import java.util.List;

public interface ParcelService {

    ParcelDTO createParcel(ParcelDTO parcelDTO) throws LogisticException;

    void deleteParcel(Integer parcelId, Integer parcelUserOrderId) throws LogisticException;

    void deleteParcelFromUserOrder(Integer parcelId, Integer parcelUserOrderId) throws LogisticException;

    List<ParcelDTO> findAllParcelByUserOrderId(Integer userOrderId) throws LogisticException;

    ParcelDTO updateParcelInformation(ParcelDTO parcelDTO) throws LogisticException;

    List<ParcelDTO> moveParcelForUserOrder(List<Integer> parcelIds, Integer newUserOrderId, Integer orginUserOrderId) throws LogisticException;
}
