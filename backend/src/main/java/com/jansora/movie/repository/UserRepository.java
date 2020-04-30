package com.jansora.movie.repository;

import com.jansora.movie.entity.User;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends ReactiveElasticsearchRepository<User, Long> {

    List<User> findByUsername(String username);

}
