package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.UserDTO;
import com.BridgeLabz.BookstoreApp.entity.User;
import com.BridgeLabz.BookstoreApp.exception.BookException;
import com.BridgeLabz.BookstoreApp.repository.UserRepository;
import com.BridgeLabz.BookstoreApp.util.EmailSenderService;
import com.BridgeLabz.BookstoreApp.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtility util;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User newUser= new User(userDTO);
        userRepository.save(newUser);
        String token = util.createToken((int) newUser.getId());
        mailService.sendEmail(newUser.getEmail(), "Test Email", "Registered SuccessFully, hii: "
                +newUser.getFirstname()+"Please Click here to get data-> "
                +"http://localhost:8081/user/getBy/"+token);
        return userDTO;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> getUsers= userRepository.findAll();
        return getUsers;
    }

//    @Override
//    public User updateRecordById(Integer id, UserDTO userDTO) {
//        return null;
//    }

    @Override
    public User updateRecordById(Integer id, UserDTO userDTO) {
//        Integer id= util.decodeToken(token);
        Optional<User> addressBook = userRepository.findById(Math.toIntExact(id));
        if(addressBook.isPresent()) {
            User newBook = new User(Math.toIntExact(id),userDTO);
            userRepository.save(newBook);

            return newBook;

        }
        throw new BookException("User Details for id not found");
    }

}
