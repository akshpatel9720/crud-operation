package com.example.taskcrudoperation.repository;

import com.example.taskcrudoperation.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT email FROM UserEntity WHERE email = :email")
    String findbyEmail(String email);

    Optional<UserEntity> findOneByEmailIgnoreCase(String username);

    @Query("SELECT u FROM UserEntity u where " + "u.mobileno LIKE CONCAT('%',:Text,'%')" + "Or u.email LIKE CONCAT('%',:Text,'%')" + "Or u.createdDateTime LIKE CONCAT('%',:Text,'%')")
    List<UserEntity> search(String Text);

    @Query("SELECT u from UserEntity u WHERE isVerified = false")
    List<UserEntity> getInActiveUser();
}
