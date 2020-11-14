package com.logistic.project.controller;

import com.logistic.project.entity.UserInfo;
import com.logistic.project.enumeration.Role;
import com.logistic.project.exception.LogisticException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseController {

    protected String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public static boolean validAccess(UserInfo userInfo, String userName, UserInfo currentUser) throws LogisticException{
        if (userInfo == null){
            throw new LogisticException("User Doesn't Exist");
        }
        if (currentUser.getRole().equals(Role.user) && !userInfo.getUsername().equals(userName)) {
            return false;
        }
        return true;
    }
}
