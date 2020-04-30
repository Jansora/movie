package com.jansora.movie.controller;

import com.jansora.movie.model.User;
import com.jansora.movie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * 〈一句话功能简述〉<br>
 * @file TestController.java
 * @description TestController
 *
 * @author Jansora
 * @date 2020-04-29 15:13
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("findAll")
    public Flux<User> findAll() {
        Flux<User> user = userService.findAll();
        return user;
    }
    @GetMapping("findById/{id}")
    public Mono<User> findById(@PathVariable Long id) {
        return userService.findById(id)
                .doOnSubscribe((val)-> log.info("findById user: id={} ", id))
                .doOnSuccess((val) -> log.info("found user: {}", val));
    }

    @PostMapping("add")
    public Mono<User> add(User user) {
        return userService.add(user);
    }

    @PostMapping("update/{id}")
    public Mono<User> update(@PathVariable Long id, User user) {
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping("delete/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}