package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.BookDTO;
import com.BridgeLabz.BookstoreApp.entity.Book;

import java.util.List;

public interface IBookService {

    Book createBook(BookDTO bookDTO);

    List<Book> getAllBookData();

    Book getBookDataById(Integer bookId);

    Object deleteRecordById(Integer bookId);

    Book updateRecordById(Integer bookId, BookDTO bookDTO);

}
