package com.example.taskcrudoperation.controller;

import com.example.taskcrudoperation.Service.RegistrationService;
import com.example.taskcrudoperation.exception.UserException;
import com.example.taskcrudoperation.model.UserEntity;
import com.example.taskcrudoperation.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    public static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    public RegistrationService registrationService;


    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    @GetMapping("/verifyAccount")
    public ResponseEntity<Map<String, Object>> verifyAccount(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            logger.info("Inside verifyAccount() : " + email);
            return new ResponseEntity<>(registrationService.verifyAccount(email, password), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error occured while registering user {} :Reason :{}");
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            throw new UserException.VerifyAccountHandler("verify account is not exist");
        }
    }


    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody UserEntity userEntity) {
        try {
            logger.info(" Inside Save() " + userEntity);
            return new ResponseEntity<>(registrationService.save(userEntity), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(" Error occured while save user ");
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            throw new UserException.HandleException("email already exist!");
        }
    }

    @GetMapping("/forgetPassword")
    public ResponseEntity<Map<String, Object>> forgetPassword(@RequestParam("email") String email) {
        try {
            logger.info("inside forgetPassword() " + email);
            return new ResponseEntity<>(registrationService.forgetPassword(email), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while registering user {} :Reason :{}");
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            throw new UserException.ForgetPasswordHandler("password does not exist");
        }
    }

    @GetMapping("/resetPassword")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("oldPassword") String oldpassword) {
        try {
            return new ResponseEntity(registrationService.resetPassword(email, password, oldpassword), HttpStatus.OK);
        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            throw new UserException.ResetPasswordHandler("password does not exist");
        }
    }
}

