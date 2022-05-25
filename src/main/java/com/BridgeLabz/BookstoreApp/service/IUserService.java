package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.ResponseDTO;
import com.BridgeLabz.BookstoreApp.dto.UserDTO;
import com.BridgeLabz.BookstoreApp.dto.UserLoginDTO;
import com.BridgeLabz.BookstoreApp.entity.User;

import java.util.List;

public interface IUserService {

    UserDTO addUser(UserDTO userDTO);

    List<User> getAllUsers();

    Object getUserByToken(String token);

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    String forgotPassword(String email, String password);

    Object getUserByEmailId(String emailId);

    User updateUser(String email_id, UserDTO userDTO);

    String getToken(String email);

    List<User> getAllUserDataByToken(String token);

    User updateRecordById(Integer id, UserDTO userDTO);

}
