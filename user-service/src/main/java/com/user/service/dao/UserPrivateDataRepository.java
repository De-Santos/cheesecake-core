package com.user.service.dao;

import com.user.service.entities.UserPrivateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrivateDataRepository extends JpaRepository<UserPrivateData, Long> {
    @Modifying
    @Query("delete from UserPrivateData up where up.id=:user")
    void deleteByUserId(@Param("user") Long userId);
}
