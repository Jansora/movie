package com.jansora.movie.repository;

import com.jansora.movie.model.User;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

import java.util.List;


public interface UserRepository extends ReactiveElasticsearchRepository<User, Long> {

    List<User> findByUsername(String username);

}
