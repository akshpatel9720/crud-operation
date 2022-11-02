package com.example.taskcrudoperation.Service;

import com.example.taskcrudoperation.model.UserEntity;

import java.util.List;
import java.util.Map;


public interface UserService {
    Map<String,Object> getAllUser() ;

    Map<String, Object> getById(int id);
    Map<String,Object> update(UserEntity userEntity);

    Map<String,Object> delete(int id);

    public Map<String, Object> resetPasswordfromOldPassword(Integer id, String oldpassword, String newpassword);

    Map<String,Object> search(String Text);

    List<UserEntity> listAll();

}
