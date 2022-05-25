package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.ResponseDTO;
import com.BridgeLabz.BookstoreApp.dto.UserDTO;
import com.BridgeLabz.BookstoreApp.dto.UserLoginDTO;
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
                +newUser.getFirstName()+"Please Click here to get data-> "
                +"http://localhost:8082/user/getBy/"+token);
        return userDTO;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> getUsers= userRepository.findAll();
        return getUsers;
    }

    @Override
    public Object getUserByToken(String token) {
        int id=util.decodeToken(token);
        Optional<User> getUser=userRepository.findById(id);
        if(getUser.isPresent()){
            mailService.sendEmail("nilofarmujawar1118@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +getUser.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8082/user/getBy/"+token);
            return getUser;

        }
        else {
            throw new BookException("Record for provided userId is not found");
        }
    }

    @Override
    public ResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        Optional<User> login = userRepository.findByEmailid(userLoginDTO.getEmail());
        if(login.isPresent()){
            String pass = login.get().getPassword();
            if(login.get().getPassword().equals(userLoginDTO.getPassword())){
                dto.setMessage("login successful ");
                dto.setData(login.get());
                return dto;
            }

            else {
                dto.setMessage("Sorry! login is unsuccessful");
                dto.setData("Wrong password");
                return dto;
            }
        }
        return new ResponseDTO("User not found!","Wrong email");
    }

    @Override
    public String forgotPassword(String email, String password) {
        Optional<User> isUserPresent = userRepository.findByEmailid(email);

        if(!isUserPresent.isPresent()) {
            throw new BookException("Book record does not found");
        }
        else {
            User user = isUserPresent.get();
            user.setPassword(password);
            userRepository.save(user);
            return "Password updated successfully";
        }

    }

    @Override
    public Object getUserByEmailId(String emailId) {

        return userRepository.findByEmailid(emailId);
    }

    @Override
    public User updateUser(String email_id, UserDTO userDTO) {
        Optional<User> updateUser = userRepository.findByEmailid(email_id);
        if(updateUser.isPresent()) {
            User newBook = new User((int) updateUser.get().getId(),userDTO);
            userRepository.save(newBook);
            String token = util.createToken((int) newBook.getId());
            mailService.sendEmail(newBook.getEmail(),"Welcome "+newBook.getFirstName(),"Click here \n http://localhost:8082/user/getBy/"+token);
            return newBook;

        }
        throw new BookException("Book Details for email not found");
    }

    @Override
    public String getToken(String email) {
        Optional<User> userRegistration=userRepository.findByEmailid(email);
        String token=util.createToken((int) userRegistration.get().getId());
        mailService.sendEmail(userRegistration.get().getEmail(),"Welcome"+userRegistration.get().getFirstName(),"Token for changing password is :"+token);
        return token;
    }

    @Override
    public List<User> getAllUserDataByToken(String token) {
        int id=util.decodeToken(token);
        Optional<User> isContactPresent=userRepository.findById(id);
        if(isContactPresent.isPresent()) {
            List<User> listOfUsers=userRepository.findAll();
            mailService.sendEmail("korep95@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +isContactPresent.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8082/user/getAll/"+token);
            return listOfUsers;
        }else {
            System.out.println("Exception ...Token not found!");
            return null;	}
    }


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
