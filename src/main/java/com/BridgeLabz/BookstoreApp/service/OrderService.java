package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.entity.Book;
import com.BridgeLabz.BookstoreApp.entity.Order;
import com.BridgeLabz.BookstoreApp.exception.BookException;
import com.BridgeLabz.BookstoreApp.repository.BookRepository;
import com.BridgeLabz.BookstoreApp.repository.OrderRepository;
import com.BridgeLabz.BookstoreApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> getAllOrderRecords() {
        List<Order> orderList = orderRepository.findAll();
        log.info("ALL order records retrieved successfully");
        return orderList;
    }

    @Override
    public Order getOrderRecord(Integer id) {
        return null;
    }

    @Override
    public Order cancelOrder(Integer id) {
        return null;
    }

//    public Order insertOrder(OrderDTO orderdto) {
//        Optional<Book> book = bookRepository.findById(orderdto.getBookId());
//        Optional<User> user = userRepository.findById(orderdto.getUserId());
//        if (book.isPresent() && user.isPresent()) {
//            if (orderdto.getQuantity()<= book.get().getQuantity()) {
//                int quantity = book.get().getQuantity()-orderdto.getQuantity();
//                book.get().setQuantity(quantity);
//                bookRepo.save(book.get());
//                Order newOrder = new Order(book.get().getPrice(), orderdto.getQuantity(), orderdto.getAddress(), book.get(), user.get(), orderdto.isCancel());
//                orderRepo.save(newOrder);
//                log.info("Order record inserted successfully");
//                return newOrder;
//            } else {
//                throw new BookStoreException("Requested quantity is out of stock");
//            }
//        } else {
//            throw new BookStoreException("Book or User doesn't exists");
//        }
//    }

    public Order getOrderRecord(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            log.info("Order record retrieved successfully for id " + id);
            return order.get();

        } else {
            throw new BookException("Order Record doesn't exists");
        }
    }

    public Order cancelOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            order.get().setCancel(true);
            Book book = order.get().getBook();
            book.setQuantity(book.getQuantity() + order.get().getQuantity());
            bookRepository.save(book);
            orderRepository.deleteById(id);
            log.info("Order record cancel successfully for id " + id);
            return order.get();

        } else {
            throw new BookException("Order Record doesn't exists");
        }
    }
}
