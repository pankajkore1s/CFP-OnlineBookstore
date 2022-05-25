package com.BridgeLabz.BookstoreApp.entity;

import com.BridgeLabz.BookstoreApp.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstname;
    private String lastname;
    private String kyc;
    private LocalDate dob;
    private LocalDate registeredDate;
    private LocalDate updatedDate;
    private String email;
    private String password;

    public User(UserDTO userDTO) {
        this.id = getId();
        this.firstname = userDTO.getFirstname();
        this.lastname = userDTO.getLastname();
        this.kyc = userDTO.getKyc();
        this.dob = userDTO.getDob();
        this.registeredDate = userDTO.getRegisteredDate();
        this.updatedDate = userDTO.getUpdatedDate();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
    }

    public User(Integer id, UserDTO userDTO) {
        this.id = id;
        this.firstname = userDTO.getFirstname();
        this.lastname = userDTO.getLastname();
        this.kyc = userDTO.getKyc();
        this.dob = userDTO.getDob();
        this.registeredDate = userDTO.getRegisteredDate();
        this.updatedDate = userDTO.getUpdatedDate();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
    }

    public User(String email_id, UserDTO userDTO) {
        this.email = email_id;
        this.firstname = userDTO.getFirstname();
        this.lastname = userDTO.getLastname();
        this.kyc = userDTO.getKyc();
        this.dob = userDTO.getDob();
        this.registeredDate = userDTO.getRegisteredDate();
        this.updatedDate = userDTO.getUpdatedDate();
        this.password = userDTO.getPassword();
    }

    public void updateUser(UserDTO user) {

        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.kyc = user.getKyc();
        this.dob = user.getDob();
        this.registeredDate = user.getRegisteredDate();
        this.updatedDate = user.getUpdatedDate();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }


    public String getFirstName() {
        return firstname;
    }
}
