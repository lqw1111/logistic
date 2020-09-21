package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.ParcelRepository;
import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dao.repository.UserOrderRepository;
import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.dto.UserOrderWithParcelDTO;
import com.logistic.project.entity.OrderStatus;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.ParcelMapper;
import com.logistic.project.mapper.UserOrderMapper;
import com.logistic.project.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Value("${company.name}")
    private String companyName;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserOrderDTO createOrder(UserOrderDTO orderDTO) throws LogisticException {
        UserOrder userOrder = UserOrderMapper.INSTANCE.entity(orderDTO);

        userOrder.setStatusId(OrderStatus.NEW);

        UserOrder order = userOrderRepository.save(userOrder);
        order.setOrderId(companyName + UUID.randomUUID().toString());

        //update user time
        userInfoRepository.updateUserLastActiveTime(userOrder.getUserId());

        return UserOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    public UserOrderDTO updateOrder(UserOrderDTO orderDTO) throws LogisticException {
        Optional<UserOrder> userOrder = userOrderRepository.findUserOrderById(orderDTO.getId());
        if (!userOrder.isPresent())
            throw new LogisticException("UserOrder Doesn't Exist");

        UserOrder updateUserOrder = userOrder.get();

//        if (updateUserOrder.getStatusId() >= OrderStatus.APPROVED)
//            throw new LogisticException("Can't update Order Because admin already processing it, please contact with admin");

        updateUserOrder.setStatusId(orderDTO.getStatusId());
        updateUserOrder.setPrice(orderDTO.getPrice());
        updateUserOrder.setDescription(orderDTO.getDescription());
        updateUserOrder.setReceiverName(orderDTO.getReceiverName());
        updateUserOrder.setReceiverAddress(orderDTO.getReceiverAddress());
        updateUserOrder.setReceiverPhone(orderDTO.getReceiverPhone());
        updateUserOrder.setReceiverPostCode(orderDTO.getReceiverPostCode());
        updateUserOrder.setOrderId(orderDTO.getOrderId());
        updateUserOrder.setTrackNumber(orderDTO.getTrackNumber());
        updateUserOrder.setSenderName(orderDTO.getSenderName());
        updateUserOrder.setSenderAddress(orderDTO.getSenderAddress());
        updateUserOrder.setSenderPhone(orderDTO.getSenderPhone());
        updateUserOrder.setSenderPostCode(orderDTO.getSenderPostCode());
        updateUserOrder.setOrderComment(orderDTO.getOrderComment());
        updateUserOrder.setPathInfo(orderDTO.getPathInfo());
        updateUserOrder.setWeight(orderDTO.getWeight());
        updateUserOrder.setVolumn(orderDTO.getVolumn());
        updateUserOrder.setExpectDeliveryDate(orderDTO.getExpectDeliveryDate());
        updateUserOrder.setPaymentInfo(orderDTO.getPaymentInfo());

        //update user time
        userInfoRepository.updateUserLastActiveTime(updateUserOrder.getUserId());

        UserOrder o = userOrderRepository.save(updateUserOrder);
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
        if (!userOrderRepository.findUserOrderById(OrderId).isPresent())
            throw new LogisticException("UserOrder Doesn't Exist");
        userOrderRepository.deleteUserOrderById(OrderId);
    }

    @Override
    public UserOrderDTO submitOrder(Integer userId, Integer userOrderId) throws LogisticException {
        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.NEW))
            throw new LogisticException("Order Status Exception");
        userOrder.setStatusId(OrderStatus.SUBMIT);
        UserOrder order = userOrderRepository.save(userOrder);
        return UserOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    public UserOrderDTO approveOrder(Integer userId, Integer userOrderId) throws LogisticException {
        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.SUBMIT))
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
        if (!userOrder.getStatusId().equals(OrderStatus.NEW)
                && !userOrder.getStatusId().equals(OrderStatus.SUBMIT)
                && !userOrder.getStatusId().equals(OrderStatus.APPROVED))
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
    public UserOrderDTO issueOrder(Integer userId, Integer userOrderId) throws LogisticException {
        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.PROCESSING))
            throw new LogisticException("Order Status Exception");
        userOrder.setStatusId(OrderStatus.ISSUE);

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

    @Override
    public UserOrderDTO findById(Integer orderId) throws LogisticException{
        Optional<UserOrder> userOrderOptional = userOrderRepository.findUserOrderById(orderId);
        if (!userOrderOptional.isPresent()) {
            throw new LogisticException("Order Doesn't Exist");
        }
        UserOrderDTO userOrder = UserOrderMapper.INSTANCE.toDTO(userOrderOptional.get());
        return userOrder;
    }

    @Override
    public List<UserOrderDTO> findAll() throws LogisticException {
        List<UserOrder> userOrders = userOrderRepository.findAllByDeletedIsFalseOrderByModifiedAt();
        return userOrders.stream().map(userOrder -> UserOrderMapper.INSTANCE.toDTO(userOrder)).collect(Collectors.toList());
    }

    @Override
    public List<UserOrderDTO> findAllByStatusId(Integer statusId) throws LogisticException {
        List<UserOrder> userOrders = userOrderRepository.findAllByStatusIdAndDeletedIsFalseOrderByModifiedAt(statusId);
        return userOrders.stream().map(userOrder -> UserOrderMapper.INSTANCE.toDTO(userOrder)).collect(Collectors.toList());
    }


}
