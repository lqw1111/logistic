package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.ParcelRepository;
import com.logistic.project.dao.repository.UserOrderRepository;
import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.dto.UserOrderWithParcelDTO;
import com.logistic.project.entity.OrderHistory;
import com.logistic.project.entity.OrderStatus;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.ParcelMapper;
import com.logistic.project.mapper.UserOrderMapper;
import com.logistic.project.service.OrderHistoryService;
import com.logistic.project.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private ParcelRepository parcelRepository;

    @Override
    public UserOrderDTO createOrder(UserOrderDTO orderDTO) throws LogisticException {
        UserOrder userOrder = UserOrderMapper.INSTANCE.entity(orderDTO);

        userOrder.setStatusId(OrderStatus.NEW);

        UserOrder order = userOrderRepository.save(userOrder);

        return UserOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    public UserOrderDTO updateOrder(UserOrderDTO orderDTO) throws LogisticException {
        Optional<UserOrder> userOrder = userOrderRepository.findById(orderDTO.getId());
        if (!userOrder.isPresent())
            throw new LogisticException("UserOrder Doesn't Exist");

        UserOrder modifiedOrder = UserOrderMapper.INSTANCE.entity(orderDTO);
        if (!modifiedOrder.getStatusId().equals(OrderStatus.NEW))
            throw new LogisticException("Can't update Order Because admin already processing it, please contact with admin");
        UserOrder o = userOrderRepository.save(modifiedOrder);
        return UserOrderMapper.INSTANCE.toDTO(o);
    }

    @Override
    public List<UserOrderDTO> findAllByUserId(Integer userId) throws LogisticException {
        List<UserOrder> userOrders = userOrderRepository.findByUserId(userId);
        List<UserOrderDTO> res = userOrders.stream().map(userOrder -> UserOrderMapper.INSTANCE.toDTO(userOrder)).collect(Collectors.toList());
        return res;
    }

    @Override
    public void deleteOrderById(Integer OrderId) throws LogisticException {
        if (!userOrderRepository.findById(OrderId).isPresent())
            throw new LogisticException("UserOrder Doesn't Exist");
        userOrderRepository.deleteById(OrderId);
    }

    @Override
    public UserOrderDTO approveOrder(Integer userId, Integer userOrderId) throws LogisticException {
        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.NEW))
            throw new LogisticException("Order Status Exception");
        userOrder.setStatusId(OrderStatus.APPROVED);
        UserOrder order = userOrderRepository.save(userOrder);
        return UserOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    public UserOrderDTO closeUserOrder(Integer userId, Integer userOrderId) throws LogisticException {
        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.NEW))
            throw new LogisticException("Order Status Exception");
        userOrder.setStatusId(OrderStatus.CLOSED);
        UserOrder order = userOrderRepository.save(userOrder);
        return UserOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    public UserOrderDTO processingOrder(Integer userId, Integer userOrderId) throws LogisticException {
        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.APPROVED))
            throw new LogisticException("Order Status Exception");
        userOrder.setStatusId(OrderStatus.PROCESSING);
        UserOrder order = userOrderRepository.save(userOrder);
        return UserOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    public UserOrderDTO finishOrder(Integer userId, Integer userOrderId) throws LogisticException {
        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.PROCESSING))
            throw new LogisticException("Order Status Exception");
        userOrder.setStatusId(OrderStatus.FINISH);

        //TODO: 创造order history，发送邮件提醒

        UserOrder order = userOrderRepository.save(userOrder);
        return UserOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    public List<UserOrderWithParcelDTO> findUserOrderWithParcel(Integer userId) throws LogisticException {
        List<UserOrderDTO> userOrders = userOrderRepository.findByUserId(userId).stream()
                .map(userOrder -> UserOrderMapper.INSTANCE.toDTO(userOrder)).collect(Collectors.toList());
        List<UserOrderWithParcelDTO> res = userOrders.stream().map(userOrderDTO -> {
            List<ParcelDTO> parcels = parcelRepository.findParcelsByUserOrderId(userOrderDTO.getId()).stream()
                    .map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
            UserOrderWithParcelDTO userOrderWithParcelDTO = new UserOrderWithParcelDTO(userOrderDTO, parcels);
            return userOrderWithParcelDTO;
        }).collect(Collectors.toList());
        return res;
    }


}
