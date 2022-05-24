package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.BookDTO;
import com.BridgeLabz.BookstoreApp.entity.Book;
import com.BridgeLabz.BookstoreApp.exception.BookException;
import com.BridgeLabz.BookstoreApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    @Override
    public Book getBookDataById(Integer BookId) {
        Optional<Book> getBook=bookRepository.findById(BookId);
        if(getBook.isPresent()){
            return getBook.get();

        }
        throw new BookException("Book Store Details for id not found");

    }

    @Override
    public Book updateRecordById(Integer bookId, BookDTO bookDTO) {
        Optional<Book> updateBook = bookRepository.findById(bookId);
        if (updateBook.isPresent()) {
            Book updateUser = new Book(bookId,bookDTO);
            bookRepository.save(updateUser);
            return updateUser;
        } else {
            throw new BookException("Book record does not found");
        }
    }

    @Override
    public String deleteRecordById(Integer BookId) {
        Optional<Book> newBook = bookRepository.findById(BookId);
        if (newBook.isPresent()) {
            bookRepository.deleteById(BookId);

        } else {
            throw new BookException("Book record does not found");
        }
        return "data deleted successful";
    }
}
