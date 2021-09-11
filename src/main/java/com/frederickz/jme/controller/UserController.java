package com.frederickz.jme.controller;

import com.frederickz.jme.model.User;
import com.frederickz.jme.service.UserService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/users")
    public String findAll() {
        StringBuilder sb = new StringBuilder();
        userService.findAll().forEach(sb::append);
        return sb.toString();
    }

    @GetMapping("/users/{id}")
    public String findById(@PathVariable("id") Long id) {
        return userService.findById(id).toString();
    }

    @PostMapping("/users")
    public String save(@RequestBody User user) {
        try {
            User savedUser = userService.save(user);
            return savedUser.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
