package com.example.taskcrudoperation.controller;

import com.example.taskcrudoperation.model.JwtRequest;
import com.example.taskcrudoperation.model.UserEntity;
import com.example.taskcrudoperation.repository.UserRepository;
import com.example.taskcrudoperation.util.JwtTokenUtil;
import com.example.taskcrudoperation.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class JwtAuthenticationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    public JwtAuthenticationController(UserDetailsService jwtInMemoryUserDetailsService) {
        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> mapResponse = new HashMap<String, Object>();

        Map<String, Object> mapResponseUserData = new HashMap<String, Object>();
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            mapResponse.put("token", token);

            Optional<UserEntity> userEntity = userRepository.findOneByEmailIgnoreCase(userDetails.getUsername());

            if (userEntity.isPresent()) {
                UserEntity userData = userEntity.get();

                //RefreshToken refreshToken = refreshTokenService.createRefreshToken(userData.getId());
                mapResponseUserData.put("id", userData.getId().toString());
                mapResponseUserData.put("email", userData.getEmail());
                mapResponseUserData.put("token", token);
                map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
                map.put("RESPONSE_MESSAGE", "LOGIN SUCESS!");
                map.put("RESPONSE_DATA", mapResponseUserData);
            }
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("INVALID_CREDENTIALS")) {
                map.put("RESPONSE_STATUS", "400");
                map.put("RESPONSE_MESSAGE", "LOGIN_INVALID_CREDENTIALS");
                map.put("RESPONSE_DATA", new ArrayList<>());
            }
        }
        map.put("RESPONSE_STATUS", "400");
        map.put("RESPONSE_MESSAGE", "LOGIN_INVALID_CREDENTIALS");
        map.put("RESPONSE_DATA", new ArrayList<>());
        return ResponseEntity.ok(map);
    }


    private void authenticate(String name, String password) throws Exception {
//        Objects.requireNonNull(name);
//        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception(ResponseMessage.LOGIN_INVALID_CREDENTIALS, e);
        }

    }
}
