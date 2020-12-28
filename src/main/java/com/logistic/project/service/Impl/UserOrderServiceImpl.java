package com.logistic.project.service.Impl;

import com.logistic.project.controller.BaseController;
import com.logistic.project.dao.repository.ParcelRepository;
import com.logistic.project.dao.repository.UserInfoRepository;
import com.logistic.project.dao.repository.UserOrderRepository;
import com.logistic.project.dto.IssueMessageDTO;
import com.logistic.project.dto.ParcelDTO;
import com.logistic.project.dto.UserOrderDTO;
import com.logistic.project.dto.UserOrderWithParcelDTO;
import com.logistic.project.entity.OrderStatus;
import com.logistic.project.entity.Parcel;
import com.logistic.project.entity.UserOrder;
import com.logistic.project.enumeration.ParcelStatus;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.mapper.ParcelMapper;
import com.logistic.project.mapper.UserOrderMapper;
import com.logistic.project.service.ParcelService;
import com.logistic.project.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    @Autowired
    private ParcelService parcelService;

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
    public UserOrderDTO updateOrder(UserOrderDTO orderDTO, String username) throws LogisticException {
        Optional<UserOrder> userOrder = userOrderRepository.findUserOrderById(orderDTO.getId());
        if (!userOrder.isPresent())
            throw new LogisticException("UserOrder Doesn't Exist");

        UserOrder updateUserOrder = userOrder.get();
        if (!BaseController.validAccess(userInfoRepository.findById(updateUserOrder.getUserId()).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }

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
        updateUserOrder.setDeliveryType(orderDTO.getDeliveryType());
        updateUserOrder.setShippingMethod(orderDTO.getShippingMethod());
        updateUserOrder.setPaymentInfo(orderDTO.getPaymentInfo());
        updateUserOrder.setIssueMessage(orderDTO.getIssueMessage());
        //update user time
        userInfoRepository.updateUserLastActiveTime(updateUserOrder.getUserId());

        UserOrder o = userOrderRepository.save(updateUserOrder);
        return UserOrderMapper.INSTANCE.toDTO(o);
    }

    @Override
    public List<UserOrderWithParcelDTO> findAllByUserId(Integer userId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }
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
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrderById(Integer OrderId) throws LogisticException {
        if (!userOrderRepository.findUserOrderById(OrderId).isPresent())
            throw new LogisticException("UserOrder Doesn't Exist");
        userOrderRepository.deleteUserOrderById(OrderId);

        List<Parcel> parcels = parcelRepository.findParcelsByUserOrderId(OrderId);
        if (parcels.size() != 0) {
            List<Integer> parcelIds = parcels.stream().map(parcel -> parcel.getId()).collect(Collectors.toList());
            parcelService.deleteParcelFromUserOrder(parcelIds, OrderId);
        }
    }

    @Override
    public UserOrderDTO submitOrder(Integer userId, Integer userOrderId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }
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
    @Transactional(rollbackFor = Exception.class)
    public UserOrderDTO closeUserOrder(Integer userId, Integer userOrderId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }
        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.NEW)
                && !userOrder.getStatusId().equals(OrderStatus.SUBMIT)
                && !userOrder.getStatusId().equals(OrderStatus.APPROVED))
            throw new LogisticException("Order Status Exception");

        //TODO : 用户关闭之后，如果订单中有包裹，把包裹移出，所属订单变为-1即可
        List<Parcel> parcels = parcelRepository.findParcelsByUserOrderId(userOrder.getId());

        if (parcels.size() != 0) {
            List<Integer> parcelIds = parcels.stream().map(parcel -> parcel.getId()).collect(Collectors.toList());
            parcelService.deleteParcelFromUserOrder(parcelIds, userOrder.getId());
        }

        userOrder.setStatusId(OrderStatus.CLOSED);
        UserOrder order = userOrderRepository.save(userOrder);
        return UserOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserOrderDTO processingOrder(Integer userId, Integer userOrderId) throws LogisticException {
        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.APPROVED))
            throw new LogisticException("Order Status Exception");
        userOrder.setStatusId(OrderStatus.PROCESSING);
        UserOrder order = userOrderRepository.save(userOrder);

        // 更改order下相应的包裹状态为processing
        List<Parcel> parcels = parcelRepository.findParcelsByUserOrderId(userOrderId);
        for (Parcel p : parcels) {
            p.setParcelStatus(ParcelStatus.shipping);
        }
        parcelRepository.saveAll(parcels);
        return UserOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserOrderDTO finishOrder(Integer userId, Integer userOrderId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }

        UserOrder userOrder = userOrderRepository.findByUserIdAndOrderId(userId, userOrderId);
        if (userOrder == null)
            throw new LogisticException("UserOrder Doesn't Exist");
        if (!userOrder.getStatusId().equals(OrderStatus.PROCESSING) && !userOrder.getStatusId().equals(OrderStatus.ISSUE))
            throw new LogisticException("Order Status Exception");
        userOrder.setStatusId(OrderStatus.FINISH);

        //TODO: 创造order history，发送邮件提醒

        //完成订单后，订单下面的包裹更新为finish
        List<Parcel> parcels = parcelRepository.findParcelsByUserOrderId(userOrderId);
        for (Parcel p : parcels) {
            p.setParcelStatus(ParcelStatus.finish);
        }
        parcelRepository.saveAll(parcels);

        UserOrder order = userOrderRepository.save(userOrder);
        return UserOrderMapper.INSTANCE.toDTO(order);
    }

    @Override
    public UserOrderDTO issueOrder(Integer userId, Integer userOrderId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }

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
    public List<UserOrderWithParcelDTO> findUserOrderWithParcel(Integer userId, String username) throws LogisticException {
        if (!BaseController.validAccess(userInfoRepository.findById(userId).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }
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
    public UserOrderWithParcelDTO findById(Integer orderId, String username) throws LogisticException{
        Optional<UserOrder> userOrderOptional = userOrderRepository.findUserOrderById(orderId);
        if (!userOrderOptional.isPresent()) {
            throw new LogisticException("Order Doesn't Exist");
        }
        UserOrder userOrder = userOrderOptional.get();

        if (!BaseController.validAccess(userInfoRepository.findById(userOrder.getUserId()).orElse(null), username, userInfoRepository.findByUsername(username))){
            throw new LogisticException("User can not access other user's info");
        }
        UserOrderDTO userOrderDTO = UserOrderMapper.INSTANCE.toDTO(userOrder);
        List<ParcelDTO> parcels = parcelRepository.findParcelsByUserOrderId(userOrderDTO.getId()).stream()
                .map(parcel -> ParcelMapper.INSTANCE.toDTO(parcel)).collect(Collectors.toList());
        UserOrderWithParcelDTO userOrderWithParcelDTO = new UserOrderWithParcelDTO(userOrderDTO, parcels);
        return userOrderWithParcelDTO;
    }

    @Override
    public List<UserOrderWithParcelDTO> findAll() throws LogisticException {
        List<UserOrderDTO> userOrders = userOrderRepository.findAllByDeletedIsFalseOrderByModifiedAt().stream()
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
    public List<UserOrderWithParcelDTO> findAllByStatusId(Integer statusId) throws LogisticException {
        List<UserOrderDTO> userOrders = userOrderRepository.findAllByStatusIdAndDeletedIsFalseOrderByModifiedAt(statusId).stream()
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
    public Map<String, Integer> statisticsData(Integer userId) throws LogisticException {
        Map<String, Integer> map = new HashMap<>();
        map.put("NEW", userOrderRepository.findAllByStatusIdAndUserIdAndDeletedIsFalseOrderByModifiedAt(OrderStatus.NEW, userId).size());
        map.put("SUBMIT", userOrderRepository.findAllByStatusIdAndUserIdAndDeletedIsFalseOrderByModifiedAt(OrderStatus.SUBMIT, userId).size());
        map.put("APPROVED", userOrderRepository.findAllByStatusIdAndUserIdAndDeletedIsFalseOrderByModifiedAt(OrderStatus.APPROVED, userId).size());
        map.put("CLOSED", userOrderRepository.findAllByStatusIdAndUserIdAndDeletedIsFalseOrderByModifiedAt(OrderStatus.CLOSED, userId).size());
        map.put("PROCESSING", userOrderRepository.findAllByStatusIdAndUserIdAndDeletedIsFalseOrderByModifiedAt(OrderStatus.PROCESSING, userId).size());
        map.put("FINISH", userOrderRepository.findAllByStatusIdAndUserIdAndDeletedIsFalseOrderByModifiedAt(OrderStatus.FINISH, userId).size());
        map.put("ISSUE", userOrderRepository.findAllByStatusIdAndUserIdAndDeletedIsFalseOrderByModifiedAt(OrderStatus.ISSUE, userId).size());
        return map;
    }


    @Override
    public UserOrderDTO updateIssueMessage(IssueMessageDTO issueMessageDTO) throws LogisticException {
        Optional<UserOrder> userOrder = userOrderRepository.findUserOrderById(issueMessageDTO.getId());
        if (!userOrder.isPresent())
            throw new LogisticException("UserOrder Doesn't Exist");

        UserOrder updateUserOrder = userOrder.get();
        updateUserOrder.setIssueMessage(issueMessageDTO.getIssueMessage());
        userInfoRepository.updateUserLastActiveTime(updateUserOrder.getUserId());

        UserOrder o = userOrderRepository.save(updateUserOrder);
        return UserOrderMapper.INSTANCE.toDTO(o);
    }
}
