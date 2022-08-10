package com.example.taskcrudoperation.controller;

import com.example.taskcrudoperation.Service.UserService;
import com.example.taskcrudoperation.model.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController
{
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @GetMapping("/getAllUser")
    public ResponseEntity<Map<String, Object>> getAllUser(@RequestHeader("Authorization") String authToken) {
        try {
            logger.info("inside  getAllUser() ");
            return new ResponseEntity<>(userService.getAllUser(authToken),HttpStatus.OK);
        }
        catch (Exception e)
        {
            logger.error("error occur while getAllUser() " +e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @GetMapping("/getById")
    public ResponseEntity<Map<String, Object>> getById(@RequestParam("id") int id) {
        try {
            logger.info("inside  getById() ");
            return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
        }
        catch (Exception e)
        {
            logger.error("error occur while getById() " +e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody UserEntity userEntity) {
        try {
            logger.info("inside  update() ");
            return new ResponseEntity<>(userService.update(userEntity),HttpStatus.OK);
        }
        catch (Exception e)
        {
            logger.error("error occur while update() " +e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("id") int id) {
        try {
            logger.info("inside  delete() ");
            return new ResponseEntity<>(userService.delete(id),HttpStatus.OK);
        }
        catch (Exception e)
        {
            logger.error("error occur while delete() " +e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @PostMapping("/resetPasswordfromOldPassword")
    public ResponseEntity<Map<String, Object>> resetPasswordfromOldPassword(@RequestParam("id") Integer id, @RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword) {
        try {
            logger.info("resetUser : ");
            return new ResponseEntity<>(userService.resetPasswordfromOldPassword(id, oldpassword, newpassword), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while resetPasswordfromOldPassword {} :Reason :{}",e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
