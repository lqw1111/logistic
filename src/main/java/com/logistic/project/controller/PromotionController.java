package com.logistic.project.controller;

import com.logistic.project.entity.Promotion;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/promotion")
public class PromotionController extends BaseController {

    @Autowired
    private PromotionService promotionService;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public List<Promotion> findAllPromotionByUser(@PathVariable("userId") Integer userId) throws LogisticException {
        return promotionService.findAllPromotionByUser(userId, getPrincipal());
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = {"/discount/user/{userId}/discount/{discount}", "/discount/user/{userId}/discount/{discount}/expire/{monthNum}"}, method = RequestMethod.POST)
    public Promotion createDiscountPromotion(@PathVariable("userId") Integer userId,
                                     @PathVariable("discount") Integer discount,
                                     @PathVariable(value = "monthNum", required = false) Integer monthNum) throws LogisticException {
        return promotionService.createDiscountPromotion(userId, discount, monthNum);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping(value = {"/price/user/{userId}/price/{price}", "/price/user/{userId}/price/{price}/expire/{monthNum}"}, method = RequestMethod.POST)
    public Promotion createPricePromotion(@PathVariable("userId") Integer userId,
                                          @PathVariable("price") Integer price,
                                          @PathVariable(value = "monthNum", required = false) Integer monthNum) throws LogisticException {
        return promotionService.createPricePromotion(userId, price, monthNum);
    }
}
