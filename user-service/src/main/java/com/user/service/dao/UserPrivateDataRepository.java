package com.user.service.dao;

import com.user.service.dto.user.UserResponse;
import com.user.service.entities.UserPrivateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserPrivateDataRepository extends JpaRepository<UserPrivateData, Long> {
    @Modifying
    @Query("delete from UserPrivateData up where up.id=:user")
    void deleteByUserId(@Param("user") Long userId);

    @Query("SELECT upd.creationTime FROM UserPrivateData upd WHERE upd.id = :id")
    Date getDateById(@Param("id") Long id);

	@Query("SELECT upd FROM UserPrivateData upd WHERE upd.email = :login AND upd.password = :password")
    Optional<UserResponse> findByLoginAndPassword(String login, String password);
}
