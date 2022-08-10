package com.example.taskcrudoperation.Service;

import com.example.taskcrudoperation.model.UserEntity;

import java.util.Map;


public interface RegistrationService {

    Map<String, Object> save(UserEntity userEntity);

    public Map<String, Object> verifyAccount(String email, String password);

    public Map<String, Object> forgetPassword(String email);

    public Map<String, Object> resetPassword(String email, String password, String oldpassword);

}
