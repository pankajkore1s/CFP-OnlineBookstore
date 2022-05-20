package com.BridgeLabz.BookstoreApp.repository;

import com.BridgeLabz.BookstoreApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "SELECT * from book WHERE book_id= :bookId", nativeQuery = true)
    Optional<Book> findByBookId(Integer bookId);
}
