package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.OrderHistoryRepository;
import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dao.repository.UserOrderRepository;
import com.logistic.project.dto.OrderHistoryDTO;
import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.entity.OrderHistory;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.OrderHistoryMapper;
import com.logistic.project.service.OrderHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Override
    public OrderHistoryDTO createOrderHistory(OrderHistoryDTO orderHistoryDTO) throws LogisticException {
        OrderHistory orderHistory = OrderHistoryMapper.INSTANCE.entity(orderHistoryDTO);
        Optional<UserOrder> userOrderOptional = userOrderRepository.findById(orderHistory.getUserOrderId());
        if (!userOrderOptional.isPresent())
            throw new LogisticException("User Order Doesn't exist");
        UserOrder userOrder = userOrderOptional.get();

        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(orderHistory.getUserId());
        if (!userInfoOptional.isPresent())
            throw new LogisticException("User Doesn't exist");

        UserInfo userInfo = userInfoOptional.get();

        if (userOrder.getUserId() != userInfo.getUid())
            throw new LogisticException("Invalid Opeation");

        OrderHistory o = orderHistoryRepository.save(orderHistory);
        return OrderHistoryMapper.INSTANCE.toDTO(o);
    }
}
