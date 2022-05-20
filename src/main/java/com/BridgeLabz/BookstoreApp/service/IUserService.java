package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.UserDTO;
import com.BridgeLabz.BookstoreApp.entity.User;

import java.util.List;

public interface IUserService {

    UserDTO addUser(UserDTO userDTO);

    List<User> getAllUsers();

}
