package com.BridgeLabz.BookstoreApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {

    private String message;
    private Object data;

    public ResponseDTO() {

    }
}
