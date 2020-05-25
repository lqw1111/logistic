package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.UserOrderRepository;
import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.entity.OrderStatus;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.UserOrderMapper;
import com.logistic.project.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Override
    public UserOrder createOrder(UserOrderDTO orderDTO) throws LogisticException {
        UserOrder userOrder = UserOrderMapper.INSTANCE.entity(orderDTO);

        userOrder.setStatusId(OrderStatus.NEW);

        UserOrder order = userOrderRepository.save(userOrder);

        return order;
    }

    @Override
    public UserOrder updateOrder(UserOrderDTO orderDTO) throws LogisticException {
        Optional<UserOrder> userOrder = userOrderRepository.findById(orderDTO.getId());
        if (!userOrder.isPresent())
            throw new LogisticException("UserOrder Doesn't Exist");

        UserOrder modifiedOrder = UserOrderMapper.INSTANCE.entity(orderDTO);
        UserOrder o = userOrderRepository.save(modifiedOrder);
        return o;
    }

    @Override
    public List<UserOrder> findAllByUserId(Integer userId) throws LogisticException {
        return userOrderRepository.findByUserId(userId);
    }

    @Override
    public void deleteOrderById(Integer id) throws LogisticException {
        if (!userOrderRepository.findById(id).isPresent())
            throw new LogisticException("UserOrder Doesn't Exist");
        userOrderRepository.deleteById(id);
    }

    @Override
    public UserOrder approve(Integer userId, Integer userOrderId) throws LogisticException {
        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.NEW))
            throw new LogisticException("Order Status Exception");
        userOrder.setStatusId(OrderStatus.APPROVED);
        return userOrderRepository.save(userOrder);
    }

}
