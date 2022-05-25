package com.BridgeLabz.BookstoreApp.service;

import com.BridgeLabz.BookstoreApp.dto.OrderDTO;
import com.BridgeLabz.BookstoreApp.entity.Order;

import java.util.List;

public interface IOrderService {

    public Order insertOrder(OrderDTO orderDTO);

    public List<Order> getAllOrderRecords();

    public Order getOrderRecord(Integer id);

    public Order updateOrderRecord(Long id, OrderDTO dto);

    public Order deleteOrderRecord(Long id);

    public Order cancelOrder(Integer id);

}
