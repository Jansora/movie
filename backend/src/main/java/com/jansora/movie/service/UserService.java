package com.jansora.movie.service;

import com.jansora.movie.entity.User;
import com.jansora.movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/*
 * 〈一句话功能简述〉<br>
 * @file UserService.java
 * @description UserService
 *
 * @author Jansora
 * @date 2020-04-29 17:19
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> add(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(User user) {
        return userRepository.save(user);
    }

    public Mono<Void> delete(Long id) {
        return userRepository.deleteById(id);
    }

    public Mono<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

}