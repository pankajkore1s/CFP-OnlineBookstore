package com.BridgeLabz.BookstoreApp.repository;

import com.BridgeLabz.BookstoreApp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value="select * from cart where book_id =:bookId",nativeQuery =true)
    Optional<Cart> findByBookId(Integer bookId);
}
