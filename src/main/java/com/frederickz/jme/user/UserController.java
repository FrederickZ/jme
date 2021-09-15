package com.frederickz.jme.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll() {
        StringBuilder sb = new StringBuilder();
        userService.findAll().forEach(user -> sb.append(user).append("/n"));
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
