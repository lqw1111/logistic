package com.logistic.project.controller;

import com.logistic.project.entity.InvitationActivity;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.InvitationActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/invitationactivity")
public class InvitationActivityController {

    @Autowired
    private InvitationActivityService invitationActivityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<InvitationActivity> findAll() {
        return invitationActivityService.findAll();
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public List<InvitationActivity> findByUserId(@PathVariable("userId") Integer userId) throws LogisticException {
        return invitationActivityService.findByUserId(userId);
    }

    /**
     * if not exist will return `{}`
     * @param orderId
     * @return
     * @throws LogisticException
     */
    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public Object findByOrderId(@PathVariable("orderId") Integer orderId) throws LogisticException {
        return invitationActivityService.findByOrderId(orderId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public InvitationActivity createActivity(@RequestBody InvitationActivity invitationActivity) throws LogisticException {
        return invitationActivityService.createActivity(invitationActivity);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public InvitationActivity updateActivity(@RequestBody InvitationActivity invitationActivity) throws LogisticException {
        return invitationActivityService.updateActivity(invitationActivity);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public void deleteActivity(@PathVariable("id") Integer id) {
        invitationActivityService.deleteActivityById(id);
    }
}
