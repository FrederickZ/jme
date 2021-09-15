package com.frederickz.jme.order;

import com.frederickz.jme.user.User;
import com.frederickz.jme.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
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
    public String findById(@PathVariable("id") UUID id) {
        return orderService.findById(id).toString();
    }

    @PostMapping
    public String save(@RequestBody Order order) {
        User user = userService.findById(order.getUser().getId());
        if (user == null) {
            return "User not found";
        }
        order.setUser(user);
        try {
            Order savedOrder = orderService.save(order);
            orderService.produceOrderMessage(savedOrder);
            return savedOrder.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
