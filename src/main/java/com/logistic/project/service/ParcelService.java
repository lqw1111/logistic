package com.logistic.project.service;

import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.exception.LogisticException;
import java.util.List;
import java.util.Map;

public interface ParcelService {

    ParcelDTO createParcel(ParcelDTO parcelDTO) throws LogisticException;

    void deleteParcel(Integer parcelId, Integer parcelUserOrderId) throws LogisticException;

    List<ParcelDTO> deleteParcelFromUserOrder(List<Integer> parcelId, Integer parcelUserOrderId) throws LogisticException;

    List<ParcelDTO> findAllParcelByUserOrderId(Integer userOrderId, String username) throws LogisticException;

    ParcelDTO updateParcelInformation(ParcelDTO parcelDTO, String username) throws LogisticException;

    List<ParcelDTO> moveParcelForUserOrder(List<Integer> parcelIds, Integer newUserOrderId, Integer orginUserOrderId) throws LogisticException;

    List<ParcelDTO> findAllParcelByUserId(Integer userId, String username) throws LogisticException;

    List<ParcelDTO> findAllParcelByUserName(String userName);

    ParcelDTO findById(Integer parcelId, String username) throws LogisticException;

    ParcelDTO updateParcelToWaiting(Integer parcelId) throws LogisticException;

    ParcelDTO updateParcelToProblem(Integer parcelId) throws LogisticException;

    ParcelDTO updateParcelToVerify(Integer parcelId) throws LogisticException;

    ParcelDTO updateParcelToShipping(Integer parcelId) throws LogisticException;

    List<ParcelDTO> findAll() throws LogisticException;

    List<ParcelDTO> findAllWaiting() throws LogisticException;

    List<ParcelDTO> findAllProblem() throws LogisticException;

    List<ParcelDTO> findAllVerify() throws LogisticException;

    List<ParcelDTO> findAllShipping() throws LogisticException;

    ParcelDTO updateParcelToFinish(Integer parcelId) throws LogisticException;

    List<ParcelDTO> findAllFinish() throws LogisticException;

    Map<String,Integer> statisticsData(Integer userId) throws LogisticException;
}
