package com.example.taskcrudoperation.repository;

import com.example.taskcrudoperation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{


    @Query("SELECT email FROM User  WHERE email = :email")
    String findbyEmail(String email);
}
