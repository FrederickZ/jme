package com.frederickz.jme.controller;

import com.frederickz.jme.model.Order;
import com.frederickz.jme.model.User;
import com.frederickz.jme.service.OrderService;
import com.frederickz.jme.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
//@Validated
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String findAll() {
        StringBuilder sb = new StringBuilder();
        orderService.findAll().forEach(sb::append);
        return sb.toString();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return orderService.findById(id).toString();
    }

    @PostMapping
    public String save(@RequestBody @Valid Order order) {
        User user = userService.findById(order.getUser().getId());
        if (user == null) {
            return "User not found";
        }
        order.setUser(user);
        try {
            Order savedOrder = orderService.save(order);
            return savedOrder.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
