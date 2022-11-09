package com.example.taskcrudoperation.serviceimpl;


import com.example.taskcrudoperation.Service.EmailService;
import com.example.taskcrudoperation.Service.RegistrationService;
import com.example.taskcrudoperation.model.UserEntity;
import com.example.taskcrudoperation.repository.UserRepository;
import com.example.taskcrudoperation.util.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    public static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Autowired
    public UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    public Map<String, Object> save(UserEntity userEntity) {
        Map<String, Object> map = new HashMap<>();

        if (userEntity != null) {
            logger.info("User Entity is not null");
            Optional<UserEntity> user = userRepository.findOneByEmailIgnoreCase(userEntity.getEmail());

            if (!user.isPresent()) {
                logger.info("email id is not present ");
                userEntity.setVerified(false);
                userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                userEntity.setCreatedDateTime(new Date());
                userRepository.save(userEntity);
                emailService.sendWelcomeMailToUser(userEntity);
                map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
                map.put(ResponseMessage.MESSAGE, ResponseMessage.SAVE_SUCCESSFULLY);
                map.put(ResponseMessage.DATA, new ArrayList<>());
            } else {
                logger.info("email is null or present");
                map.put(ResponseMessage.STATUS, ResponseMessage.FAIL_API_CODE);
                map.put(ResponseMessage.MESSAGE, ResponseMessage.EMAIL_IS_NULL_OR_PRESENT);
                map.put(ResponseMessage.DATA, new ArrayList<>());
            }
        } else {
            logger.info("User entity is null ");
            map.put(ResponseMessage.STATUS, ResponseMessage.FAIL_API_CODE);
            map.put(ResponseMessage.MESSAGE, ResponseMessage.USER_ENTITY_NULL);
            map.put(ResponseMessage.DATA, new ArrayList<>());
        }
        return map;
    }


    @Override
    public Map<String, Object> verifyAccount(String email, String password) {
        Map<String, Object> map = new HashMap<>();

        Optional<UserEntity> userEntity = userRepository.findOneByEmailIgnoreCase(email);
        if (userEntity.isPresent()) {
            UserEntity userEntity1 = userEntity.get();
            if (userEntity1.getPassword().equalsIgnoreCase(password)) {
                userEntity1.setVerified(true);
                userRepository.save(userEntity1);
                map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
                map.put(ResponseMessage.MESSAGE, ResponseMessage.USER_VERIFICATION_SUCCESS);
                map.put(ResponseMessage.DATA, new ArrayList<>());
            }
        } else {
            map.put(ResponseMessage.STATUS, ResponseMessage.FAIL_API_CODE);
            map.put(ResponseMessage.MESSAGE, ResponseMessage.USER_VERIFICATION_FAILURE);
            map.put(ResponseMessage.DATA, new ArrayList<>());
        }
        return map;
    }

    @Override
    public Map<String, Object> forgetPassword(String email) {
        Map<String, Object> map = new HashMap<>();
        Optional<UserEntity> userEntity = userRepository.findOneByEmailIgnoreCase(email);
        if (userEntity.isPresent()) {
            UserEntity userEntity1 = userEntity.get();
            if (userEntity1.getVerified().equals(Boolean.TRUE)) {
                emailService.sendPasswordResetMailToUser(userEntity1);
                map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
                map.put(ResponseMessage.MESSAGE, ResponseMessage.FORGOT_PASSWORD_SUCCESS);
                map.put(ResponseMessage.DATA, new ArrayList<>());
            } else {
                map.put(ResponseMessage.STATUS, ResponseMessage.FAIL_API_CODE);
                map.put(ResponseMessage.MESSAGE, ResponseMessage.LOGIN_NOT_VERIFIED);
                map.put(ResponseMessage.DATA, new ArrayList<>());
            }
        } else {
            map.put(ResponseMessage.STATUS, ResponseMessage.FAIL_API_CODE);
            map.put(ResponseMessage.MESSAGE, ResponseMessage.FORGOT_PASSWORD_EMAIL_NOT_EXISTS);
            map.put(ResponseMessage.DATA, new ArrayList<>());
        }
        return map;
    }

    @Override
    public Map<String, Object> resetPassword(String email, String password, String oldpassword) {
        Map<String, Object> map = new HashMap<>();
        Optional<UserEntity> userEntity = userRepository.findOneByEmailIgnoreCase(email);
        if (userEntity.isPresent()) {
            UserEntity userEntity1 = userEntity.get();
            if (userEntity1.getPassword().equalsIgnoreCase(oldpassword)) {
                userEntity1.setPassword(passwordEncoder.encode(password));
                userRepository.save(userEntity1);
                map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
                map.put(ResponseMessage.MESSAGE, ResponseMessage.RESET_PASSWORD_SUCCESS);
                map.put(ResponseMessage.DATA, new ArrayList<>());
            } else {
                map.put(ResponseMessage.STATUS, ResponseMessage.FAIL_API_CODE);
                map.put(ResponseMessage.MESSAGE, ResponseMessage.OLD_PASSWORD_NOT_MATCHED);
                map.put(ResponseMessage.DATA, new ArrayList<>());
            }
        } else {
            map.put(ResponseMessage.STATUS, ResponseMessage.FAIL_API_CODE);
            map.put(ResponseMessage.MESSAGE, ResponseMessage.USER_NOT_FOUND);
            map.put(ResponseMessage.DATA, new ArrayList<>());
        }
        return map;
    }


    public void savetest(String name, String fullname) {
        Map<String, String> saveTest = new HashMap<>();
        saveTest.put(name, fullname);
    }

}
