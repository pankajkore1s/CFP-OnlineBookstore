package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.OrderDTO;
import com.BridgeLabz.BookstoreApp.entity.Book;
import com.BridgeLabz.BookstoreApp.entity.Order;
import com.BridgeLabz.BookstoreApp.entity.User;
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

    public Order insertOrder(OrderDTO orderDTO) {
        Optional<Book> book = bookRepository.findById(orderDTO.getBookId());
        Optional<User> user = userRepository.findById(Math.toIntExact(orderDTO.getId()));
        if (book.isPresent() && user.isPresent()) {
            if (orderDTO.getQuantity()<= book.get().getNoOfBooks()) {
                int quantity = book.get().getNoOfBooks()-orderDTO.getQuantity();
                book.get().setNoOfBooks(quantity);
                bookRepository.save(book.get());
                Order newOrder = new Order(book.get().getPrice(), orderDTO.getQuantity(), orderDTO.getAddress(), user.get(), book.get(), orderDTO.isCancel());
                orderRepository.save(newOrder);
                log.info("Order record inserted successfully");
                return newOrder;
            } else {
                throw new BookException("Requested quantity is out of stock");
            }
        } else {
            throw new BookException("Book or User doesn't exists");
        }
    }

    public List<Order> getAllOrderRecords() {
        List<Order> orderList = orderRepository.findAll();
        log.info("ALL order records retrieved successfully");
        return orderList;
    }

    public Order getOrderRecord(Integer id) {
        Optional<Order> order = orderRepository.findById(Long.valueOf(id));
        if (order.isPresent()) {
            log.info("Order record retrieved successfully for id " + id);
            return order.get();

        } else {
            throw new BookException("Order Record doesn't exists");
        }
    }

    public Order updateOrderRecord(Long id, OrderDTO orderDTO) {
        Optional<Order> order = orderRepository.findById(id);
        Optional<Book> book = bookRepository.findById(orderDTO.getBookId());
        Optional<User> user = userRepository.findById(Math.toIntExact(orderDTO.getId()));
        if (order.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                if (orderDTO.getQuantity() <= book.get().getNoOfBooks()) {
                    int quantity = book.get().getNoOfBooks()-orderDTO.getQuantity();
                    book.get().setNoOfBooks(quantity);
                    bookRepository.save(book.get());
                    Order newOrder = new Order(id, book.get().getPrice(), orderDTO.getQuantity(), orderDTO.getAddress(), book.get(), user.get(), orderDTO.isCancel());
                    orderRepository.save(newOrder);
                    log.info("Order record updated successfully for id " + id);
                    return newOrder;
                } else {
                    throw new BookException("Requested quantity is not available");
                }
            } else {
                throw new BookException("Book or User doesn't exists");

            }

        } else {
            throw new BookException("Order Record doesn't exists");
        }
    }

    public Order deleteOrderRecord(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
            log.info("Order record deleted successfully for id " + id);
            return order.get();

        } else {
            throw new BookException("Order Record doesn't exists");
        }
    }


    public Order cancelOrder(Integer id) {
        Optional<Order> order = orderRepository.findById(Long.valueOf(id));
        if (order.isPresent()) {
            order.get().setCancel(true);
            Book book = order.get().getBook();
            book.setNoOfBooks(book.getNoOfBooks() + order.get().getQuantity());
            bookRepository.save(book);
            orderRepository.deleteById(Long.valueOf(id));
            log.info("Order record cancel successfully for id " + id);
            return order.get();

        } else {
            throw new BookException("Order Record doesn't exists");
        }
    }

}
