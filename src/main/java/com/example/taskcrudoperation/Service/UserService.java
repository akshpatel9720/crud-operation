package com.example.taskcrudoperation.Service;

import com.example.taskcrudoperation.model.UserEntity;

import java.util.Map;


public interface UserService {
    Map<String,Object> getAllUser(String authToken) ;

    Map<String, Object> getById(int id);
    Map<String,Object> update(UserEntity userEntity);

    Map<String,Object> delete(int id);

    public Map<String, Object> resetPasswordfromOldPassword(Integer id, String oldpassword, String newpassword);
}
