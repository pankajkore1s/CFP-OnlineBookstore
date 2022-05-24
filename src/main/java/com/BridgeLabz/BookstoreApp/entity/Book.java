package com.BridgeLabz.BookstoreApp.entity;

import com.BridgeLabz.BookstoreApp.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private String bookDetails;
    private String authorName;
    private String bookName;
    private int price;
    private int noOfBooks;

    public Book(Integer bookId, BookDTO bookDTO) {
        this.bookDetails=bookDTO.getBookDetails();
        this.authorName=bookDTO.getAuthorName();
        this.bookName=bookDTO.getBookName();
        this.price=bookDTO.getPrice();
        this.noOfBooks=bookDTO.getNoOfBooks();
    }
}
