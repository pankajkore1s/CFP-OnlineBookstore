package com.BridgeLabz.BookstoreApp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
public class UserDTO {

    private long id;
    private String firstname;
    private String lastname;
    private String kyc;
    private LocalDate dob;
    private LocalDate registeredDate;
    private LocalDate updatedDate;
    @Email
    private String email;
    private String password;
}
