package com.jansora.movie.service;

import com.jansora.movie.model.elasticsearch.Movie;
import com.jansora.movie.repo.elaticsearch.MovieRepository;
import com.jansora.movie.utils.ElasticSearchClient;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * @File movieService.java
 * @Author Jansora
 * @Date 2020-04-30 04:52:05 下午
 */

@Service
public class MovieService {


    private RestClient restClient = ElasticSearchClient.restClient;
    private RestHighLevelClient restHighLevelClient = ElasticSearchClient.restHighLevelClient;

    private MovieRepository movieRepository;


    public Mono<Movie> add(Movie movie) {
        return movieRepository.save(movie);
    }

    public Mono<Movie> update(Movie movie) {
        return movieRepository.save(movie);
    }

    public Mono<Void> delete(String id) {
        return movieRepository.deleteById(id);
    }

    public Mono<Movie> findById(String id) {
        return movieRepository.findById(id);
    }
    public Flux<Movie> findAll() {
        return movieRepository.findAll();
    }

}