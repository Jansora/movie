package com.jansora.movie.repo.elaticsearch;

import com.jansora.movie.model.elasticsearch.Movie;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

import java.util.List;


public interface MovieRepository extends ReactiveElasticsearchRepository<Movie, String> {

    List<Movie> findFirstByName(String username);

}
