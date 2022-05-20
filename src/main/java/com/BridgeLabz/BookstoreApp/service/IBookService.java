package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.BookDTO;
import com.BridgeLabz.BookstoreApp.entity.Book;

import java.util.List;

public interface IBookService {

    Book createBook(BookDTO bookDTO);

    List<Book> getAllBookData();
}
