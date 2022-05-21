package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.CartDTO;
import com.BridgeLabz.BookstoreApp.dto.ResponseDTO;
import com.BridgeLabz.BookstoreApp.entity.Book;
import com.BridgeLabz.BookstoreApp.entity.Cart;
import com.BridgeLabz.BookstoreApp.entity.User;
import com.BridgeLabz.BookstoreApp.exception.BookException;
import com.BridgeLabz.BookstoreApp.repository.BookRepository;
import com.BridgeLabz.BookstoreApp.repository.CartRepository;
import com.BridgeLabz.BookstoreApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService {
    @Autowired
    static
    BookRepository bookRepository;

    @Autowired
    static
    UserRepository userRepository;

    @Autowired
    static
    CartRepository cartRepository;

    @Override
    public static Cart insertItems(CartDTO cartdto) {
        Optional<Book> book = bookRepository.findById(cartdto.getBookId());
        Optional<User> user= userRepository.findById(cartdto.getUserId());
        if (book.isPresent() && user.isPresent()) {
            Cart newCart = new Cart(user.get(), book.get(), cartdto.getQuantity());
            cartRepository.save(newCart);
            return newCart;
        } else {
            throw new BookException("Book or User does not exists");
        }
    }


    @Override
    public ResponseDTO getCartDetails() {
        List<Cart> getCartDetails=cartRepository.findAll();
        ResponseDTO dto= new ResponseDTO(dto);
        if (getCartDetails.isEmpty()){
            String message=" Not found Any Cart details ";
            dto.setMessage(message);
            dto.setData(0);
            return dto;
        }
        else {
            dto.setMessage("the list of cart items is sucussfully retrived");
            dto.setData(getCartDetails);
            return dto;
        }
    }

}
