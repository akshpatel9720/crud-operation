package com.example.taskcrudoperation.serviceimpl;


import com.example.taskcrudoperation.Service.UserService;
import com.example.taskcrudoperation.model.User;
import com.example.taskcrudoperation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    public User getById(int id) {
        return userRepository.findById(id).get();
    }


    public String save(User user) {
        String email = userRepository.findbyEmail(user.getEmail());
        if (email != null && email.equalsIgnoreCase(user.getEmail())) {
            return "email exist";
        } else {
            userRepository.save(user);
            return "saved successfully";
        }

    }


    public void update(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        if (existingUser != null && existingUser.getEmail().equalsIgnoreCase(user.getEmail())) {
            existingUser.setMobileno(user.mobileno);
            userRepository.save(existingUser);
        } else {
            userRepository.save(user);
        }
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
