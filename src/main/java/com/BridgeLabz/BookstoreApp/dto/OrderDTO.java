package com.BridgeLabz.BookstoreApp.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Integer quantity;
    private Integer price;
    private String address;
    private boolean cancel;
    private Integer bookId;
    private Long id;

    public Integer getUserId() {
        return null;
    }
}
