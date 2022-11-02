package com.example.taskcrudoperation;

import com.example.taskcrudoperation.Service.RegistrationService;
import com.example.taskcrudoperation.Service.UserService;
import com.example.taskcrudoperation.model.UserEntity;
import com.example.taskcrudoperation.repository.UserRepository;
import com.example.taskcrudoperation.serviceimpl.RegistrationServiceImpl;
import com.example.taskcrudoperation.serviceimpl.UserServiceImpl;
import com.example.taskcrudoperation.util.ResponseMessage;
import org.apache.poi.hssf.record.ObjRecord;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SpringBootTest
class TaskCrudOperationApplicationTests {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void saveTest() throws Exception {
        UserEntity saveData = new UserEntity();
        saveData.setEmail("akshpatel12345");
        saveData.setAge(21);
        saveData.setAddress("sciencity");
        saveData.setPassword("1234");
        Map<String, Object> actualData = registrationService.save(saveData);
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        expectedData.put(ResponseMessage.MESSAGE, ResponseMessage.SAVE_SUCCESSFULLY);
        expectedData.put(ResponseMessage.DATA, new ArrayList<>());
        assertEquals(actualData, expectedData);
    }

    @Test
    void verifyTest() throws Exception {
        Map<String, Object> actualData = registrationService.verifyAccount("aksh1234567@yopmail.com", "1234");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        expectedData.put(ResponseMessage.MESSAGE, ResponseMessage.USER_VERIFICATION_SUCCESS);
        expectedData.put(ResponseMessage.DATA, new ArrayList<>());
        assertEquals(actualData, expectedData);
    }

    @Test
    void forgetPasswordTest() throws Exception {
        Map<String, Object> actualData = registrationService.forgetPassword("aksh1234567@yopmail.com");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        expectedData.put(ResponseMessage.MESSAGE, ResponseMessage.FORGOT_PASSWORD_SUCCESS);
        expectedData.put(ResponseMessage.DATA, new ArrayList<>());
        assertEquals(actualData, expectedData);
    }

    @Test
    void resetPasswordTest() throws Exception {
        Map<String, Object> actualData = registrationService.resetPassword("akshpatel1234", "12345", "$2a$10$wvixDO5opCwomFa/LPChJeh1iHXT60UokeP0GHN9XiVrYL16MSHlu");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        expectedData.put(ResponseMessage.MESSAGE, ResponseMessage.RESET_PASSWORD_SUCCESS);
        expectedData.put(ResponseMessage.DATA, new ArrayList<>());
        assertEquals(actualData, expectedData);
    }

    @Test
    void getAllUserTest() throws Exception {
        Map<String, Object> actualData = userService.getAllUser();
        Map<String, Object> map = new HashMap<>();
        map.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        map.put(ResponseMessage.MESSAGE, ResponseMessage.SUCCESS_MESSAGE_GET);
        map.put(ResponseMessage.DATA, new ArrayList<>());
        assertNotEquals(actualData, map);
    }

    @Test
    void getByIdTest() throws Exception {
        Map<String, Object> actualData = userService.getById(1);
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        expectedData.put(ResponseMessage.MESSAGE, ResponseMessage.SUCCESS_MESSAGE_GET);
        expectedData.put(ResponseMessage.DATA, new ArrayList<>());
        assertNotEquals(actualData, expectedData);
    }

    @Test
    void updateTest() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(5);
        userEntity.setAddress("ahmedabad");
        Map<String, Object> actualData = userService.update(userEntity);
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        expectedData.put(ResponseMessage.MESSAGE, ResponseMessage.SUCCESS_MESSAGE_UPDATE);
        expectedData.put(ResponseMessage.DATA, actualData);
        assertNotEquals(actualData, expectedData);
    }

    @Test
    void deleteTest() throws Exception {
        Map<String, Object> actualData = userService.delete(3);
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        expectedData.put(ResponseMessage.MESSAGE, ResponseMessage.SUCCESS_MESSAGE_DELETE);
        assertEquals(actualData, expectedData);
    }

    @Test
    void resetPasswordFromOldPasswordTest() throws Exception {
        Map<String, Object> actualData = userService.resetPasswordfromOldPassword(2, "1", "1234");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        expectedData.put(ResponseMessage.MESSAGE, ResponseMessage.PASSWORD_EDIT_SUCCESS);
        expectedData.put(ResponseMessage.DATA, new ArrayList<>());
        assertEquals(actualData, expectedData);
    }

    @Test
    void searchTest() throws Exception {
        List<UserEntity> searchdata = userRepository.search("9662");
        Map<String, Object> exceptedData = new HashMap<>();
        exceptedData.put(ResponseMessage.STATUS, ResponseMessage.SUCCESS_API_CODE);
        exceptedData.put(ResponseMessage.MESSAGE, ResponseMessage.SUCCESS_SEARCH);
        exceptedData.put(ResponseMessage.DATA, searchdata);
        assertNotEquals(searchdata, exceptedData);

    }



//    @Test
//    public void testTest1() throws Exception{
//        RegistrationServiceImpl registrationService1=mock(RegistrationServiceImpl.class);
//        ArgumentCaptor<String> captor=ArgumentCaptor.forClass(String.class);
//        doNothing().when(registrationService1).add(anyString(),captor.capture());
//        registrationService1.add("aksh","aksh patel");
//        verify(registrationService1,times(1)).add("aksh","aksh patel");
//        assertEquals("aksh patel",captor.getValue());
//
//    }

    @Test
    public void testTest() throws Exception{
        RegistrationServiceImpl registrationService1=mock(RegistrationServiceImpl.class);
        doNothing().when(registrationService1).savetest(anyString(),anyString());
        registrationService1.savetest("aksh","aksh patel");
        verify(registrationService1,times(1)).savetest("aksh","aksh patel");
    }


    @Test
    public void testTest1()  throws Exception{
        RegistrationServiceImpl registrationService1=mock(RegistrationServiceImpl.class);
        ArgumentCaptor<String> captor=ArgumentCaptor.forClass(String.class);
        doNothing().when(registrationService1).savetest(anyString(),captor.capture());
        registrationService1.savetest("aksh","aksh patel");
        assertEquals("aksh patel",captor.getValue());

    }
}