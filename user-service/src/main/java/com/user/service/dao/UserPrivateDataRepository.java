package com.user.service.dao;

import com.user.service.entities.UserPrivateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserPrivateDataRepository extends JpaRepository<UserPrivateData, Long> {

    @Query("SELECT upd.creationTime FROM UserPrivateData upd WHERE upd.id = :id")
    Date getDateById(@Param("id") Long id);
}
