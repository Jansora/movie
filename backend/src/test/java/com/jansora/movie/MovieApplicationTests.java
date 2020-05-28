package com.jansora.movie;

import com.jansora.movie.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import reactor.core.publisher.Mono;

@SpringBootTest
class MovieApplicationTests {

    @Autowired
    private ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void addMovie() {

        reactiveElasticsearchTemplate.save(Movie.builder().name("斗地主5").build())
            .doOnNext(System.out::println)
            .flatMap(movie -> reactiveElasticsearchTemplate.get(movie.getId(), Movie.class))
            .doOnNext(System.out::println)
            .flatMap(movie -> reactiveElasticsearchTemplate.delete(movie))
            .doOnNext(System.out::println)
            .flatMap(id -> reactiveElasticsearchTemplate.count(Movie.class))
            .doOnSubscribe(System.out::println)
            .doOnSuccess(System.out::println)
            .subscribe();

    }


}
