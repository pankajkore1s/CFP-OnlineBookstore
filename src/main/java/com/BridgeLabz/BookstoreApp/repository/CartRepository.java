package com.BridgeLabz.BookstoreApp.repository;

import com.BridgeLabz.BookstoreApp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {

}
