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

    @RequestMapping(name = "", method = RequestMethod.GET)
    public List<InvitationActivity> findAll() {
        return invitationActivityService.findAll();
    }

    @RequestMapping(name = "", method = RequestMethod.POST)
    public InvitationActivity createActivity(@RequestBody InvitationActivity invitationActivity) throws LogisticException {
        return invitationActivityService.createActivity(invitationActivity);
    }

    @RequestMapping(name = "", method = RequestMethod.PUT)
    public InvitationActivity updateActivity(@RequestBody InvitationActivity invitationActivity) throws LogisticException {
        return invitationActivityService.updateActivity(invitationActivity);
    }

    @RequestMapping(name = "/id/{id}", method = RequestMethod.DELETE)
    public void deleteActivity(@PathVariable("id") Integer id) {
        invitationActivityService.deleteActivityById(id);
    }
}
