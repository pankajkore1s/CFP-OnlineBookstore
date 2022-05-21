package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.CartDTO;
import com.BridgeLabz.BookstoreApp.dto.ResponseDTO;
import com.BridgeLabz.BookstoreApp.entity.Cart;

public interface ICartService {

    static abstract Cart insertItems(CartDTO cartdto);

    ResponseDTO getCartDetails();

      //Cart getCartDetailsById(Integer cartId);
}
