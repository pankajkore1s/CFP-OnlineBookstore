package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.UserDTO;
import com.BridgeLabz.BookstoreApp.entity.User;
import com.BridgeLabz.BookstoreApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User newUser= new User(userDTO);
        userRepository.save(newUser);
//        String token = util.createToken(newUser.getUserId());
//        mailService.sendEmail(newUser.getEmail(), "Test Email", "Registered SuccessFully, hii: "
//                +newUser.getFirstName()+"Please Click here to get data-> "
//                +"http://localhost:8081/user/getBy/"+token);
        return userDTO;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> getUsers= userRepository.findAll();
        return getUsers;
    }
}
