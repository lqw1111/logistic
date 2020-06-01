package com.logistic.project.dao.repository;

import com.logistic.project.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    @Query("SELECT user FROM UserInfo user WHERE user.username = :username")
    UserInfo findByUsername(@Param("username") String username);
}
