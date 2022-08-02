package com.example.taskcrudoperation.controller;

import com.example.taskcrudoperation.Service.UserService;
import com.example.taskcrudoperation.model.User;
import com.example.taskcrudoperation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/getById/{id}")
    public User getById(@PathVariable("id") int id) {
        return userService.getById(id);
    }

    @PostMapping("/save")
    public String save(@RequestBody User user) {
        return userService.save(user);

    }

    @PutMapping("/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

}
