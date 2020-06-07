package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.ParcelRepository;
import com.logistic.project.dao.repository.UserOrderRepository;
import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.entity.Parcel;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.enumeration.ParcelStatus;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.ParcelMapper;
import com.logistic.project.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Override
    public ParcelDTO createParcel(ParcelDTO parcelDTO) throws LogisticException {
        Parcel parcel = ParcelMapper.INSTANCE.entity(parcelDTO);
        parcel.setParcelStatus(ParcelStatus.waiting);
        parcel.setUserOrderId(-1);

        Optional<UserOrder> userOrder = userOrderRepository.findById(parcelDTO.getUserOrderId());
        if (! userOrder.isPresent())
            throw new LogisticException("User Order Doesn't Exist");

        Parcel p = parcelRepository.save(parcel);
        ParcelDTO res = ParcelMapper.INSTANCE.toDTO(p);
        return res;
    }

    @Override
    public void deleteParcel(Integer parcelId, Integer parcelUserOrderId) throws LogisticException {
        Optional<Parcel> parcelOptional = parcelRepository.findById(parcelId);
        if (!parcelOptional.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");
        Parcel parcel = parcelOptional.get();
        if (parcel.getUserOrderId() != parcelUserOrderId)
            throw new LogisticException("User Order Id Doesn't Match");
        parcelRepository.delete(parcel);
    }

    @Override
    public ParcelDTO deleteParcelFromUserOrder(Integer parcelId, Integer parcelUserOrderId) throws LogisticException {
        Optional<Parcel> parcelOptional = parcelRepository.findById(parcelId);
        if (!parcelOptional.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");
        Parcel parcel = parcelOptional.get();
        if (parcel.getUserOrderId() != parcelUserOrderId)
            throw new LogisticException("User Order Id Doesn't Match");
        //-1 不属于任何包裹
        parcel.setUserOrderId(-1);
        Parcel res = parcelRepository.saveAndFlush(parcel);
        return ParcelMapper.INSTANCE.toDTO(res);
    }

    @Override
    public List<ParcelDTO> findAllParcelByUserOrderId(Integer userOrderId) throws LogisticException {
        List<Parcel> parcels = parcelRepository.findParcelsByUserId(userOrderId);
        List<ParcelDTO> res = new ArrayList<>();
        parcels.forEach(parcel -> {res.add(ParcelMapper.INSTANCE.toDTO(parcel));});

        return res;
    }

    @Override
    public ParcelDTO updateParcelInformation(ParcelDTO parcelDTO) throws LogisticException {
        Optional<Parcel> p = parcelRepository.findById(parcelDTO.getId());
        if (!p.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");

        Parcel parcel = parcelRepository.save(ParcelMapper.INSTANCE.entity(parcelDTO));
        ParcelDTO res = ParcelMapper.INSTANCE.toDTO(parcel);
        return res;
    }

    @Override
    public List<ParcelDTO> moveParcelForUserOrder(List<Integer> parcelIds, Integer newUserOrderId, Integer orginUserOrderId) throws LogisticException {
        Optional<UserOrder> oldOrder = userOrderRepository.findById(orginUserOrderId);
        if (!oldOrder.isPresent())
            throw new LogisticException("Old Order Doesn't Exist");

        Optional<UserOrder> newUserOrder = userOrderRepository.findById(newUserOrderId);
        if (!newUserOrder.isPresent())
            throw new LogisticException("New Order Doesn't Exist");

        Optional<List<Parcel>> parcelOptional = Optional.ofNullable(parcelRepository.findByIds(parcelIds));
        if (!parcelOptional.isPresent())
            throw new LogisticException("Parcel Doesn't Exist");

        List<Parcel> updateParcels = parcelOptional.get();
        List<Integer> parcelUserId = updateParcels.stream().map(parcel -> parcel.getUserId()).distinct().collect(Collectors.toList());
        List<Integer> parcelId = updateParcels.stream().map(parcel -> parcel.getId()).distinct().collect(Collectors.toList());
        if (parcelUserId.size() != 1)
            throw new LogisticException("Find Multiple User Id");
        if (parcelId.size() != 1 || parcelIds.get(0) != orginUserOrderId)
            throw new LogisticException("Can not Change due to the different User Order Id");

        updateParcels.forEach(parcel -> parcel.setUserOrderId(newUserOrderId));
        List<Parcel> save = parcelRepository.saveAll(updateParcels);
        List<ParcelDTO> res = save.stream().map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
        return res;
    }
}
