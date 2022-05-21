package com.BridgeLabz.BookstoreApp.controller;

import com.BridgeLabz.BookstoreApp.dto.BookDTO;
import com.BridgeLabz.BookstoreApp.dto.ResponseDTO;
import com.BridgeLabz.BookstoreApp.entity.Book;
import com.BridgeLabz.BookstoreApp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService bookService;

    @PostMapping("/insert")
    public ResponseEntity<String> addBookToRepository(@RequestBody BookDTO bookDTO){
        Book newBook= bookService.createBook(bookDTO);
        ResponseDTO responseDTO=new ResponseDTO("New Book Added in Book Store",newBook);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<String> getAllBookData() {
        List<Book> listOfBooks = bookService.getAllBookData();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{BookId}")
    public ResponseEntity<String> getBookDataById(@PathVariable Integer BookId) {
        Book Book = bookService.getBookDataById(BookId);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully by id (:",Book);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{BookId}")
    public ResponseEntity<String> deleteRecordById(@PathVariable Integer BookId){
        ResponseDTO dto = new ResponseDTO("Book Record deleted successfully", bookService.deleteRecordById(BookId));
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @PutMapping("/updateBookById/{BookId}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer BookId,@RequestBody BookDTO bookDTO){
        Book updateRecord = bookService.updateRecordById(BookId,bookDTO);
        ResponseDTO dto = new ResponseDTO(" Book Record updated successfully by Id",updateRecord);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
}
