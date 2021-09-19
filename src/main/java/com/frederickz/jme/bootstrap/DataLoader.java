package com.frederickz.jme.bootstrap;

import com.frederickz.jme.model.User;
import com.frederickz.jme.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        userService.save(user1);
        User user2 = new User();
        userService.save(user2);
        User user3 = new User();
        userService.save(user3);
        System.out.printf("%d users loaded.%n", userService.count());
    }

}
