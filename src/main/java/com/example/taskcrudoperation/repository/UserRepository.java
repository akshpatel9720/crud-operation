package com.example.taskcrudoperation.repository;

import com.example.taskcrudoperation.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>
{

//    @Query("SELECT email FROM UserEntity WHERE email = :email")
//    String findbyEmail(String email);

    Optional<UserEntity> findOneByEmailIgnoreCase(String username);

}
