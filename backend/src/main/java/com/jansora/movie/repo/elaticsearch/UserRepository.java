package com.jansora.movie.repo.elaticsearch;

import com.jansora.movie.model.elasticsearch.User;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

import java.util.List;


public interface UserRepository extends ReactiveElasticsearchRepository<User, String> {

    List<User> findByUsername(String username);

}
