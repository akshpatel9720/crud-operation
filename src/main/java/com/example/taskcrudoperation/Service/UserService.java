package com.example.taskcrudoperation.Service;

import com.example.taskcrudoperation.model.User;

import java.util.List;


public interface UserService
{

   List<User> getAllUser();

    User getById(int id);

    String save(User user);

    void update(User user);

    void delete(int id);


}
