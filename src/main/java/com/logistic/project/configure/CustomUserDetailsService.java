package com.logistic.project.configure;

import com.logistic.project.entity.UserInfo;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.UserInfoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoService.findByUsername(username);
        if (userInfo == null){
            throw new UsernameNotFoundException("not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + userInfo.getRole().name()));

        User userDetails = new User(userInfo.getUsername(), userInfo.getPassword(), authorities);

        return userDetails;
    }
}
