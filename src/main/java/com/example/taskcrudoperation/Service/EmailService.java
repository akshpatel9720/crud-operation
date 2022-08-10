package com.example.taskcrudoperation.Service;

import com.example.taskcrudoperation.model.UserEntity;

public interface EmailService {
    public void sendWelcomeMailToUser(UserEntity userEntity);

    public void sendPasswordResetMailToUser(UserEntity userEntity);
}
