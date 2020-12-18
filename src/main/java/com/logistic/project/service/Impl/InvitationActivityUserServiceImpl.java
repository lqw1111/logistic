package com.logistic.project.service.Impl;

import com.logistic.project.dao.repository.InvitationActivityUserRepository;
import com.logistic.project.service.InvitationActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationActivityUserServiceImpl implements InvitationActivityUserService {

    @Autowired
    private InvitationActivityUserRepository invitationActivityUserRepository;
}
