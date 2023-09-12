package com.dayan.restaurant.service;

import com.dayan.restaurant.model.Order;
import com.dayan.restaurant.repository.OrderRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> getOrder(final Long id){
        return orderRepository.findById(id);
    }

    public Iterable<Order> getOrders(){
        return orderRepository.findAll();
    }

    public void deleteOrder(final Long id) {
        orderRepository.deleteById(id);
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

}
