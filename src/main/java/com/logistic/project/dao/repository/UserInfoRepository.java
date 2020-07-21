package com.logistic.project.dao.repository;

import com.logistic.project.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    @Query("SELECT user FROM UserInfo user WHERE user.username = :username AND user.deleted = 0")
    UserInfo findByUsername(@Param("username") String username);

    List<UserInfo> findAllByDeletedIsFalse();

    @Modifying
    @Transactional
    @Query("update UserInfo u set u.lastActiveTime = CURRENT_TIMESTAMP where u.uid = :userId")
    int updateUserLastActiveTime(@Param("userId") Integer UserId);
}
