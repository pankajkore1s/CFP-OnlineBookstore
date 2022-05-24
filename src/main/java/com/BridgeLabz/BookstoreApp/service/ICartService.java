package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.CartDTO;
import com.BridgeLabz.BookstoreApp.dto.ResponseDTO;
import com.BridgeLabz.BookstoreApp.entity.Cart;

public interface ICartService {

    Cart insertItems(CartDTO cartdto);

    ResponseDTO getCartDetails();

    Cart getCartDetailsById(Integer cartId);

    Cart updateRecordById(Integer cartId, CartDTO cartDTO);

    Cart getCartRecordByBookId(Integer bookID);

    Cart deleteCartItemById(Integer cartId);

}
