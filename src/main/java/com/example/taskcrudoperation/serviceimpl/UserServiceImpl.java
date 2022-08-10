package com.example.taskcrudoperation.serviceimpl;

import com.example.taskcrudoperation.Service.UserService;
import com.example.taskcrudoperation.model.UserEntity;
import com.example.taskcrudoperation.repository.UserRepository;
import com.example.taskcrudoperation.util.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public Map<String, Object> getAllUser(String authToken) {

        Map<String, Object> map = new HashMap<>();
        List<UserEntity> userEntities = userRepository.findAll();

        map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        map.put(ResponseMessage.MESSAGE, ResponseMessage.SUCCESS_MESSAGE_GET);
        map.put(ResponseMessage.DATA, userEntities);

        return map;
    }

    public Map<String, Object> getById(int id) {
        Map<String, Object> map = new HashMap<>();
        Optional<UserEntity> user1 = userRepository.findById(id);

        if (user1.isPresent()) {

            map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
            map.put(ResponseMessage.MESSAGE, ResponseMessage.SUCCESS_MESSAGE_GET);
            map.put(ResponseMessage.DATA, user1);
        } else {
            map.put(ResponseMessage.STATUS, ResponseMessage.FAIL_API_CODE);
            map.put(ResponseMessage.MESSAGE, ResponseMessage.FAIL_MESSAGE_GET);
            map.put(ResponseMessage.DATA, new ArrayList<>());
        }
        return map;
    }

    public Map<String, Object> update(UserEntity userEntity) {
        Map<String, Object> map = new HashMap<>();
        UserEntity existingUserEntity = userRepository.findById(userEntity.getId()).get();
        if (existingUserEntity != null) {
            existingUserEntity.setMobileno(userEntity.mobileno);

            UserEntity userEntity1 = userRepository.save(existingUserEntity);
            map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
            map.put(ResponseMessage.MESSAGE, ResponseMessage.SUCCESS_MESSAGE_UPDATE);
            map.put(ResponseMessage.DATA, userEntity1);

        } else {
            userRepository.save(userEntity);
            map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
            map.put(ResponseMessage.MESSAGE, ResponseMessage.SUCCESS_MESSAGE);

        }
        return map;
    }

    public Map<String, Object> delete(int id) {
        Map<String, Object> map = new HashMap<>();
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
            map.put(ResponseMessage.MESSAGE, ResponseMessage.SUCCESS_MESSAGE_DELETE);
            userRepository.deleteById(id);
        } else {
            map.put(ResponseMessage.STATUS, ResponseMessage.FAIL_API_CODE);
            map.put(ResponseMessage.MESSAGE, ResponseMessage.FAIL_MESSAGE_DELETE);
        }
        return map;
    }

    @Override
    public Map<String, Object> resetPasswordfromOldPassword(Integer id, String oldpassword, String newpassword) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        Map<String, Object> map = new HashMap<>();
        try {
            if (userEntity!=null)
            {
                UserEntity user = userEntity.get();
                BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
                boolean isPasswordMatches = bcrypt.matches(oldpassword, user.getPassword());
                if (isPasswordMatches)
                {
                    user.setPassword(passwordEncoder.encode(newpassword));
                    userRepository.save(user);
                    map.put(ResponseMessage.STATUS,ResponseMessage.SUCCESS_API_CODE);
                    map.put(ResponseMessage.MESSAGE,ResponseMessage.PASSWORD_EDIT_SUCCESS);
                    map.put(ResponseMessage.DATA,new ArrayList<>());
                }
                else {
                    map.put(ResponseMessage.STATUS,ResponseMessage.FAIL_API_CODE);
                    map.put(ResponseMessage.MESSAGE,ResponseMessage.OLD_PASSWORD_DOES_NOT_MATCHED);
                    map.put(ResponseMessage.DATA,new ArrayList<>());
                }
            }
        }catch (Exception e)
        {
            logger.error("Problem occured while resetPasswordfromOldPassword , Please check logs : " + e.getMessage());
            map.put(ResponseMessage.STATUS,ResponseMessage.FAIL_API_CODE);
            map.put(ResponseMessage.MESSAGE,ResponseMessage.SOMETING_WENT_WRONG);
            map.put(ResponseMessage.DATA,new ArrayList<>());
        }
        return map;
    }
}
