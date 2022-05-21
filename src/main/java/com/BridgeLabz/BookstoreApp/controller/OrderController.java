package com.BridgeLabz.BookstoreApp.controller;

import com.BridgeLabz.BookstoreApp.dto.OrderDTO;
import com.BridgeLabz.BookstoreApp.dto.ResponseDTO;
import com.BridgeLabz.BookstoreApp.entity.Order;
import com.BridgeLabz.BookstoreApp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertOrder(@RequestBody OrderDTO orderdto){
        Order newOrder = orderService.insertOrder(orderdto);
        ResponseDTO dto = new ResponseDTO("Order placed successfully !",newOrder);
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    @GetMapping("/retrieveAllOrders")
    public ResponseEntity<ResponseDTO> getAllOrderRecords(){
        List<Order> newOrder = orderService.getAllOrderRecords();
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !",newOrder);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/retrieveOrder/{id}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id){
        Order newOrder = orderService.getOrderRecord(id);
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",newOrder);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/cancelOrder/{id}")
    public ResponseEntity<ResponseDTO> getCancelOrder(@PathVariable Integer id){
        Order deletedOrder = orderService.cancelOrder(id);
        ResponseDTO dto = new ResponseDTO("Cancel order successfully !",deletedOrder);
        return new ResponseEntity(dto,HttpStatus.OK);
    }
}
