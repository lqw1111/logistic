package com.logistic.project.dao.repository;

import com.logistic.project.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    public UserInfo findByUsername(String username);
}
