package com.logistic.project.service.Impl;

import com.logistic.project.controller.BaseController;
import com.logistic.project.dao.repository.ParcelRepository;
import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dao.repository.UserOrderRepository;
import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.entity.Parcel;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.enumeration.ParcelStatus;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.ParcelMapper;
import com.logistic.project.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public ParcelDTO createParcel(ParcelDTO parcelDTO) throws LogisticException {
        Parcel parcel = ParcelMapper.INSTANCE.entity(parcelDTO);
        parcel.setParcelStatus(ParcelStatus.waiting);
        parcel.setUserOrderId(parcelDTO.getUserOrderId() == 0 ? -1: parcelDTO.getUserOrderId());

        //update user time
        userInfoRepository.updateUserLastActiveTime(parcelDTO.getUserId());

        Parcel p = parcelRepository.save(parcel);
        ParcelDTO res = ParcelMapper.INSTANCE.toDTO(p);
        return res;
    }

    @Override
    public void deleteParcel(Integer parcelId, Integer parcelUserOrderId) throws LogisticException {
        Optional<Parcel> parcelOptional = parcelRepository.findParcelById(parcelId);
        if (!parcelOptional.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");
        Parcel parcel = parcelOptional.get();
        if (parcel.getUserOrderId() != parcelUserOrderId)
            throw new LogisticException("User Order Id Doesn't Match");
        parcelRepository.deleteParcelById(parcel.getId());
    }

    @Override
    public List<ParcelDTO> deleteParcelFromUserOrder(List<Integer> parcelId, Integer parcelUserOrderId) throws LogisticException {
        List<Parcel> parcels = parcelRepository.findByIds(parcelId);
        if (parcels == null || parcels.size() == 0)
            throw new LogisticException("Parcel Doesn't Exist");

        for (Parcel parcel : parcels) {
            if (parcel.getUserOrderId() != parcelUserOrderId)
                throw new LogisticException("User Order Id Doesn't Match");
            //-1 不属于任何包裹
            parcel.setUserOrderId(-1);
        }

        //update user time
        userInfoRepository.updateUserLastActiveTime(parcels.get(0).getUserId());

        List<Parcel> dbParcels = parcelRepository.saveAll(parcels);
        List<ParcelDTO> res = dbParcels.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
        return res;
    }

    @Override
    public List<ParcelDTO> findAllParcelByUserOrderId(Integer userOrderId, String username) throws LogisticException {
        UserOrder userOrder = userOrderRepository.findById(userOrderId).orElse(null);
        if (userOrder == null) {
            throw new LogisticException("Order Doesn't Exist");
        }
        if (!BaseController.validAccess(userInfoRepository.findById(userOrder.getUserId()).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }

        List<Parcel> parcels = parcelRepository.findParcelsByUserOrderId(userOrderId);
        List<ParcelDTO> res = new ArrayList<>();
        parcels.forEach(parcel -> {res.add(ParcelMapper.INSTANCE.toDTO(parcel));});

        return res;
    }

    @Override
    public ParcelDTO updateParcelInformation(ParcelDTO parcelDTO, String username) throws LogisticException {
        Optional<Parcel> p = parcelRepository.findParcelById(parcelDTO.getId());
        if (!p.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");

        Parcel updateParcel = p.get();

        if (!BaseController.validAccess(userInfoRepository.findById(updateParcel.getUserId()).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }

        updateParcel.setOrderNumber(parcelDTO.getOrderNumber());
        updateParcel.setSenderName(parcelDTO.getSenderName());
        updateParcel.setSenderAddress(parcelDTO.getSenderAddress());
        updateParcel.setSenderPhone(parcelDTO.getSenderPhone());
        updateParcel.setSenderPostCode(parcelDTO.getSenderPostCode());
        updateParcel.setReceiverName(parcelDTO.getReceiverName());
        updateParcel.setReceiverAddress(parcelDTO.getReceiverAddress());
        updateParcel.setReceiverPhone(parcelDTO.getReceiverPhone());
        updateParcel.setReceiverPostCode(parcelDTO.getReceiverPostCode());
        updateParcel.setContentType(parcelDTO.getContentType());
        updateParcel.setDescription(parcelDTO.getDescription());
        updateParcel.setParcelStatus(parcelDTO.getParcelStatus());
        updateParcel.setUserOrderId(parcelDTO.getUserOrderId());
        updateParcel.setComment(parcelDTO.getComment());

        //update user time
        userInfoRepository.updateUserLastActiveTime(parcelDTO.getUserId());

        Parcel parcel = parcelRepository.save(updateParcel);
        ParcelDTO res = ParcelMapper.INSTANCE.toDTO(parcel);
        return res;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ParcelDTO> moveParcelForUserOrder(List<Integer> parcelIds, Integer newUserOrderId, Integer orginUserOrderId) throws LogisticException {
        if (orginUserOrderId != -1) {
            Optional<UserOrder> oldOrder = userOrderRepository.findUserOrderById(orginUserOrderId);
            if (!oldOrder.isPresent())
                throw new LogisticException("Old Order Doesn't Exist");
        }

        Optional<UserOrder> newUserOrder = userOrderRepository.findUserOrderById(newUserOrderId);
        if (!newUserOrder.isPresent())
            throw new LogisticException("New Order Doesn't Exist");

        Optional<List<Parcel>> parcelOptional = Optional.ofNullable(parcelRepository.findByIds(parcelIds));
        if (!parcelOptional.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");

        List<Parcel> updateParcels = parcelOptional.get();
        List<Integer> parcelUserId = updateParcels.stream().map(parcel -> parcel.getUserId()).distinct().collect(Collectors.toList());
        List<Integer> parcelUserOrderId = updateParcels.stream().map(parcel -> parcel.getUserOrderId()).distinct().collect(Collectors.toList());
        if (parcelUserId.size() != 1)
            throw new LogisticException("Find Multiple User Id");
        if (parcelUserOrderId.size() != 1 || !parcelUserOrderId.get(0).equals(orginUserOrderId))
            throw new LogisticException("Can not Change due to the different User Order Id");

        UserOrder userOrder = newUserOrder.get();
        if (!userOrder.getUserId().equals(parcelUserId.get(0))) {
            throw new LogisticException("Parcel and Order Doesn't belong to one User");
        }

        updateParcels.forEach(parcel -> parcel.setUserOrderId(newUserOrderId));

        //update user time
        userInfoRepository.updateUserLastActiveTime(newUserOrder.get().getUserId());

        List<Parcel> save = parcelRepository.saveAll(updateParcels);
        List<ParcelDTO> res = save.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
        return res;
    }

    @Override
    public List<ParcelDTO> findAllParcelByUserId(Integer userId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }
        List<Parcel> parcels = parcelRepository.findParcelsByUserId(userId);
        return parcels.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
    }

    @Override
    public List<ParcelDTO> findAllParcelByUserName(String userName) {
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        List<Parcel> parcels = parcelRepository.findParcelsByUserId(userInfo.getUid());
        return parcels.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
    }

    @Override
    public ParcelDTO findById(Integer parcelId, String username) throws LogisticException {
        Optional<Parcel> parcelOptional = parcelRepository.findParcelById(parcelId);
        if (!parcelOptional.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");

        Parcel parcel = parcelOptional.get();
        if (!BaseController.validAccess(userInfoRepository.findById(parcel.getUserId()).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }

        ParcelDTO parcelDTO = ParcelMapper.INSTANCE.toDTO(parcel);
        return parcelDTO;
    }

    @Override
    public ParcelDTO updateParcelToWaiting(Integer parcelId) throws LogisticException {
        Optional<Parcel> parcel = parcelRepository.findParcelById(parcelId);
        if (!parcel.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");
        Parcel p = parcel.get();
        p.setParcelStatus(ParcelStatus.waiting);
        return ParcelMapper.INSTANCE.toDTO(parcelRepository.save(p));
    }

    @Override
    public ParcelDTO updateParcelToProblem(Integer parcelId) throws LogisticException {
        Optional<Parcel> parcel = parcelRepository.findParcelById(parcelId);
        if (!parcel.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");
        Parcel p = parcel.get();
        p.setParcelStatus(ParcelStatus.problem);
        return ParcelMapper.INSTANCE.toDTO(parcelRepository.save(p));
    }

    @Override
    public ParcelDTO updateParcelToVerify(Integer parcelId) throws LogisticException {
        Optional<Parcel> parcel = parcelRepository.findParcelById(parcelId);
        if (!parcel.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");
        Parcel p = parcel.get();
        p.setParcelStatus(ParcelStatus.verify);
        return ParcelMapper.INSTANCE.toDTO(parcelRepository.save(p));
    }

    @Override
    public ParcelDTO updateParcelToShipping(Integer parcelId) throws LogisticException {
        Optional<Parcel> parcel = parcelRepository.findParcelById(parcelId);
        if (!parcel.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");
        Parcel p = parcel.get();
        p.setParcelStatus(ParcelStatus.shipping);
        return ParcelMapper.INSTANCE.toDTO(parcelRepository.save(p));
    }

    @Override
    public ParcelDTO updateParcelToFinish(Integer parcelId) throws LogisticException {
        Optional<Parcel> parcel = parcelRepository.findParcelById(parcelId);
        if (!parcel.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");
        Parcel p = parcel.get();
        p.setParcelStatus(ParcelStatus.finish);
        return ParcelMapper.INSTANCE.toDTO(parcelRepository.save(p));
    }

    @Override
    public List<ParcelDTO> findAll() throws LogisticException {
        List<Parcel> parcels = parcelRepository.findAllByDeletedIsFalseOrderByModifiedAt();
        return parcels.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
    }

    @Override
    public List<ParcelDTO> findAllWaiting() throws LogisticException {
        List<Parcel> parcels = parcelRepository.findAllByParcelStatus(ParcelStatus.waiting.toString());
        return parcels.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
    }

    @Override
    public List<ParcelDTO> findAllProblem() throws LogisticException {
        List<Parcel> parcels = parcelRepository.findAllByParcelStatus(ParcelStatus.problem.toString());
        return parcels.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
    }

    @Override
    public List<ParcelDTO> findAllVerify() throws LogisticException {
        List<Parcel> parcels = parcelRepository.findAllByParcelStatus(ParcelStatus.verify.toString());
        return parcels.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
    }

    @Override
    public List<ParcelDTO> findAllShipping() throws LogisticException {
        List<Parcel> parcels = parcelRepository.findAllByParcelStatus(ParcelStatus.shipping.toString());
        return parcels.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
    }

    @Override
    public List<ParcelDTO> findAllFinish() throws LogisticException {
        List<Parcel> parcels = parcelRepository.findAllByParcelStatus(ParcelStatus.finish.toString());
        return parcels.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> statisticsData(Integer userId) throws LogisticException {
        Map<String, Integer> map = new HashMap<>();
        map.put(ParcelStatus.waiting.toString(), parcelRepository.findAllByParcelStatusAndUserId(ParcelStatus.waiting.toString(), userId).size());
        map.put(ParcelStatus.problem.toString(), parcelRepository.findAllByParcelStatusAndUserId(ParcelStatus.problem.toString(), userId).size());
        map.put(ParcelStatus.verify.toString(), parcelRepository.findAllByParcelStatusAndUserId(ParcelStatus.verify.toString(), userId).size());
        map.put(ParcelStatus.shipping.toString(), parcelRepository.findAllByParcelStatusAndUserId(ParcelStatus.shipping.toString(), userId).size());
        map.put(ParcelStatus.finish.toString(), parcelRepository.findAllByParcelStatusAndUserId(ParcelStatus.finish.toString(), userId).size());
        return map;
    }

}
