package com.BridgeLabz.BookstoreApp.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CartDTO {
    private Long id;
    private Integer bookId;
    @NonNull
    private Integer quantity;

    public Integer getUserId() {
        return getUserId();
    }
}
