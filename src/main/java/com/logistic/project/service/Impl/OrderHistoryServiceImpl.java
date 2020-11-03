package com.logistic.project.service.Impl;

import com.logistic.project.controller.BaseController;
import com.logistic.project.dao.repository.OrderHistoryRepository;
import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dao.repository.UserOrderRepository;
import com.logistic.project.dto.OrderHistoryDTO;
import com.logistic.project.entity.OrderHistory;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.OrderHistoryMapper;
import com.logistic.project.mapper.UserOrderMapper;
import com.logistic.project.service.OrderHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

        if (!userOrder.getUserId().equals(userInfo.getUid()))
            throw new LogisticException("Invalid Opeation");

        //update user time
        userInfoRepository.updateUserLastActiveTime(orderHistoryDTO.getUserId());

        OrderHistory o = orderHistoryRepository.save(orderHistory);

        return OrderHistoryMapper.INSTANCE.toDTO(o);
    }

    @Override
    public List<Map<String, Object>> findAllSort(String sort, String sortBy) throws LogisticException {
        List<OrderHistory> orderHistories = orderHistoryRepository.findAllByDeletedIsFalse();
        if (sortBy.equals("created")) {
            if (sort.equals("asc")) {
                orderHistories.sort((o1, o2) -> {return o1.getCreateAt().compareTo(o2.getCreateAt());});
            } else if (sort.equals("des")) {
                orderHistories.sort((o1, o2) -> {return o2.getCreateAt().compareTo(o1.getCreateAt());});
            }
        } else if (sortBy.equals("score")) {
            if (sort.equals("asc")) {
                orderHistories.sort((o1, o2) -> {return o1.getScore() - (o2.getScore());});
            } else if (sort.equals("des")) {
                orderHistories.sort((o1, o2) -> {return o2.getScore() - (o1.getScore());});
            }
        }
        List<Map<String, Object>> res = getHistories(orderHistories);
        return res;
    }

    @Override
    public List<Map<String, Object>> findAllWithOrderInfo() throws LogisticException {
        List<OrderHistory> orderHistories = orderHistoryRepository.findAllByDeletedIsFalse();
        List<Map<String, Object>> res = orderHistories.stream()
                .map(orderHistory -> {
                    UserInfo userInfo = userInfoRepository.findById(orderHistory.getUserId()).get();
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", orderHistory.getId());
                    map.put("userName", userInfo.getUsername());
                    map.put("comment", orderHistory.getComment());
                    map.put("score", orderHistory.getScore());
                    map.put("createAt", orderHistory.getCreateAt());
                    return map;
                }).collect(Collectors.toList());
        return res;
    }

    @Override
    public OrderHistoryDTO updateById(Integer orderHistoryId, OrderHistoryDTO orderHistoryDTO, String username) throws LogisticException {
        OrderHistory updateOrderHistory = orderHistoryRepository.findByIdAndDeletedIsFalse(orderHistoryId);
        if (!BaseController.validAccess(userInfoRepository.findById(updateOrderHistory.getUserId()).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }
        if (updateOrderHistory == null){
            throw new LogisticException("Order History Doesn't Exist");
        }
        updateOrderHistory.setComment(orderHistoryDTO.getComment());
        updateOrderHistory.setScore(orderHistoryDTO.getScore());

        //update user time
        userInfoRepository.updateUserLastActiveTime(orderHistoryDTO.getUserId());

        OrderHistoryDTO res = OrderHistoryMapper.INSTANCE.toDTO(orderHistoryRepository.save(updateOrderHistory));
        return res;
    }

    @Override
    public void deleteById(Integer orderHistoryId) throws LogisticException {
        OrderHistory orderHistory = orderHistoryRepository.findByIdAndDeletedIsFalse(orderHistoryId);
        if (orderHistory == null)
            throw new LogisticException("Order History Doesn't Exist");

        orderHistoryRepository.deleteById(orderHistoryId);
    }

    @Override
    public List<Map<String, Object>> findAll() throws LogisticException {
        List<OrderHistory> orderHistories = orderHistoryRepository.findAllByDeletedIsFalse();
        List<Map<String, Object>> res = getHistories(orderHistories);
        return res;
    }

    @Override
    public List<Map<String, Object>> findAllWithOrderInfoByUserId(Integer userId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }

        List<OrderHistory> orderHistories = orderHistoryRepository.findAllByDeletedIsFalse();
        List<Map<String, Object>> res = orderHistories.stream()
                .filter(orderHistory -> orderHistory.getUserId().equals(userId))
                .map(orderHistory -> {
                    return mapToJoin(orderHistory);
                }).collect(Collectors.toList());
        return res;
    }

    private List<Map<String, Object>> getHistories(List<OrderHistory> orderHistories) {
        List<Map<String, Object>> res = orderHistories.stream()
                .map(orderHistory -> {
                    return mapToJoin(orderHistory);
                }).collect(Collectors.toList());
        return res;
    }

    private Map<String, Object> mapToJoin(OrderHistory orderHistory) {
        UserInfo userInfo = userInfoRepository.findById(orderHistory.getUserId()).get();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userInfo.getUsername());
        map.put("order", UserOrderMapper.INSTANCE.toDTO(userOrderRepository.findById(orderHistory.getUserOrderId()).get()));
        map.put("orderHistory", OrderHistoryMapper.INSTANCE.toDTO(orderHistory));
        return map;
    }
}
