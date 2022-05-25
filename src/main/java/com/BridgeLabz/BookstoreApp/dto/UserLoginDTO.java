package com.BridgeLabz.BookstoreApp.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserLoginDTO {

    @Email
    private String email;
    private String password;
    public UserLoginDTO(String email,String password){
        this.email=email;
        this.password=password;
    }
}
