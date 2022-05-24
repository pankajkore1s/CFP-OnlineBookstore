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
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart insertItems(CartDTO cartdto) {
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
        ResponseDTO dto= new ResponseDTO();
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

    @Override
    public Cart getCartDetailsById(Integer cartId) {
        Optional<Cart> getCartData=cartRepository.findById(cartId);
        if (getCartData.isPresent()){
            return getCartData.get();
        }
        else {
            throw new BookException(" Didn't find any record for this particular cartId");
        }
    }

    public Cart getCartRecordByBookId(Integer bookId) {
        Optional<Cart> cart = cartRepository.findByBookId(bookId);
        if(cart.isPresent()) {
            log.info("Cart record retrieved successfully for book id "+bookId);
            return cart.get();

        }
        else {
            return null;
            //throw new BookStoreException("Cart Record doesn't exists");
        }
    }

    @Override
    public Cart deleteCartItemById(Integer cartId) {
        Optional<Cart> deleteData=cartRepository.findById(cartId);
        if (deleteData.isPresent()){
            cartRepository.deleteById(cartId);
            return deleteData.get();
        }
        else {
            throw new BookException(" Did not get any cart for specific cart id ");
        }

    }

    @Override
    public Cart updateRecordById(Integer cartId, CartDTO cartDTO) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        Optional<Book>  book = bookRepository.findById(cartDTO.getBookId());
        Optional<User> user = userRepository.findById(cartDTO.getUserId());
        if(cart.isPresent()) {
            if(book.isPresent() && user.isPresent()) {
                Cart newCart = new Cart(cartId, user.get(),book.get(), cartDTO.getQuantity());
                cartRepository.save(newCart);
                log.info("Cart record updated successfully for id "+cartId);
                return newCart;
            }
            else {
                throw new BookException("Book or User doesn't exists");
            }
        }
        else {
            throw new BookException("Cart Record doesn't exists");
        }
    }

}
