package com.jansora.movie;

import com.jansora.movie.model.User;
import com.jansora.movie.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;


@SpringBootTest
class UserTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testAddUser() {
        User user = User.builder().id(1L).score("").type("").username("heheda").movie("2442").build();

//        Mono<User> user1 = userService.AddUser(user);
//
//        System.out.println(user);

    }
}
