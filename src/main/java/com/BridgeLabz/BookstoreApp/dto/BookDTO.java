package com.BridgeLabz.BookstoreApp.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class BookDTO {
    private int bookId;
    @NotNull
    private String bookDetails;
    @NotNull
    private String authorName;
    @NotNull
    private String bookName;
    @NotNull
    private int price;
    @NotNull
    private int noOfBooks;
}
