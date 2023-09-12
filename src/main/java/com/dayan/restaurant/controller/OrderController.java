package com.dayan.restaurant.controller;

import com.dayan.restaurant.model.Order;
import com.dayan.restaurant.service.OrderService;
import com.dayan.restaurant.view.OrderView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    @JsonView(OrderView.Index.class)
    public Iterable<Order> getOrders(){
        return orderService.getOrders();
    }
}
