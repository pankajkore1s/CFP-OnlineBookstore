package com.BridgeLabz.BookstoreApp.controller;


import com.BridgeLabz.BookstoreApp.dto.ResponseDTO;
import com.BridgeLabz.BookstoreApp.dto.UserDTO;
import com.BridgeLabz.BookstoreApp.entity.User;
import com.BridgeLabz.BookstoreApp.repository.UserRepository;
import com.BridgeLabz.BookstoreApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookstore")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping(value = "/adduser")
    public ResponseEntity<ResponseDTO> addUserDetails(@RequestBody UserDTO userDTO) {
        UserDTO addData = userService.addUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added newUser Details", addData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

//    @GetMapping(value = "/getAll")
//    public ResponseEntity<String> getAllUser() {
//        List<User> listOfUsers = userService.getAllUsers();
//        ResponseDTO dto = new ResponseDTO("User retrieved successfully (:",listOfUsers);
//        return new ResponseEntity(dto,HttpStatus.OK);
//    }

    @GetMapping("/getuser")
    public List<User> getAllContacts(){

        return userRepository.findAll();
    }
}
