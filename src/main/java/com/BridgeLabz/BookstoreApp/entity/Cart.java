package com.BridgeLabz.BookstoreApp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue
    private long cartId;
    @ManyToOne
    @JoinColumn(name="id")
    private User user;
    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;
    private long quantity;

    public Cart(long cartId, User user, Book book, long quantity) {
        super();
        this.cartId = cartId;
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }

    public Cart(User user, Book book, long quantity) {
        super();
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }

    public Cart(){
        super();
    }
}
