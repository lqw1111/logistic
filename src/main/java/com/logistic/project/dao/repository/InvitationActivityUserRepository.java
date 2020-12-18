package com.logistic.project.dao.repository;

import com.logistic.project.entity.InvitationActivityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationActivityUserRepository extends JpaRepository<InvitationActivityUser, Integer> {

    @Query("SELECT iu FROM InvitationActivityUser iu WHERE iu.userId = :userId")
    InvitationActivityUser findByUserId(@Param("userId") Integer userId);
}
