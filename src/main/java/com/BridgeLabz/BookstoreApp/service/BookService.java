package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.BookDTO;
import com.BridgeLabz.BookstoreApp.entity.Book;
import com.BridgeLabz.BookstoreApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(BookDTO bookDTO) {
        Book newBook = new Book();
        return  bookRepository.save(newBook);
    }

    @Override
    public List<Book> getAllBookData() {
        List<Book> getBooks=bookRepository.findAll();
        return getBooks;
    }
}
