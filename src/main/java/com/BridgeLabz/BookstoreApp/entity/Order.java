package com.BridgeLabz.BookstoreApp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="OrderDetails")
public class Order {

    @Id
    @GeneratedValue
    private Integer orderID;
    private LocalDate orderDate = LocalDate.now();
    private Integer price;
    private Integer quantity;
    private String address;
    @ManyToOne
    @JoinColumn(name="id")
    private User user;
    @JoinColumn(name="bookId")
    private Book book;
    private boolean cancel;

    public Order(Integer orderID, Integer price, Integer quantity, String address, User user, Book book, boolean cancel) {
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.user = user;
        this.book = book;
        this.cancel = cancel;
    }
    public Order(){
        super();
    }

    public Order(Integer price, Integer quantity, String address, User user, Book book, boolean cancel) {
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.user = user;
        this.book = book;
        this.cancel = cancel;
    }
}
