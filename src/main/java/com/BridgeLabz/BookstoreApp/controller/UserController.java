package com.BridgeLabz.BookstoreApp.controller;


import com.BridgeLabz.BookstoreApp.dto.ResponseDTO;
import com.BridgeLabz.BookstoreApp.dto.UserDTO;
import com.BridgeLabz.BookstoreApp.dto.UserLoginDTO;
import com.BridgeLabz.BookstoreApp.entity.User;
import com.BridgeLabz.BookstoreApp.repository.UserRepository;
import com.BridgeLabz.BookstoreApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping(value = "/adduser")
    public ResponseEntity<ResponseDTO> addUserDetails(@RequestBody UserDTO userDTO) {
        UserDTO addData = userService.addUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added newUser Details", addData);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<ResponseDTO>(userService.loginUser(userLoginDTO),HttpStatus.OK);
    }

    @GetMapping(value = "/getall")
    public ResponseEntity<String> getAllUser() {
        List<User> listOfUsers = userService.getAllUsers();
        ResponseDTO dto = new ResponseDTO("User retrieved successfully (:",listOfUsers);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

//    @PostMapping("/register")
//    public ResponseEntity<ResponseDTO> addUserInBookStore(@RequestBody UserDTO userDTO){
//        String newUser= String.valueOf(userService.addUser(userDTO));
//        ResponseDTO responseDTO=new ResponseDTO("User Registered Successfully",newUser);
//        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
//    }

    @GetMapping(value = "/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAllUserDataByToken(@PathVariable String token)
    {
        List<User> listOfUser = userService.getAllUserDataByToken(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfUser);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/getuser")
    public List<User> getAllContacts(){

        return userRepository.findAll();
    }

    @GetMapping("/getByEmailId/{emailId}")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable("emailId") String emailId) {
        return new ResponseEntity<ResponseDTO>( new
                ResponseDTO("Get User Data by Email",
                userService.getUserByEmailId(emailId)), HttpStatus.OK);
    }

    @GetMapping("/getBy/{token}")
    public ResponseEntity<ResponseDTO> getUserByToken(@PathVariable String token) {
        return new ResponseEntity<ResponseDTO>( new
                ResponseDTO("Get User Data By Token",
                userService.getUserByToken(token)), HttpStatus.OK);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestParam String email, @RequestParam String password) {
        String resp = userService.forgotPassword(email,password);
        return new ResponseEntity(resp, HttpStatus.OK);
    }

    @PutMapping("/updateUserByEmail/{email}")
    public ResponseEntity<ResponseDTO> updateUserById(@PathVariable String email,@Valid @RequestBody UserDTO userDTO){
        User updateUser= userService.updateUser(email,userDTO);
        ResponseDTO dto = new ResponseDTO(" User Record updated successfully",updateUser);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer id,@RequestBody UserDTO userDTO){
        User entity = userService.updateRecordById(id,userDTO);
        ResponseDTO dto = new ResponseDTO("User Record updated successfully",entity);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
}
