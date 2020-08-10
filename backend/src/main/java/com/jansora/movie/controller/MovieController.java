/*
 * @File movieController.java
 * @Author Jansora
 * @Date 2020-04-30 05:21:15 下午
 */



package com.jansora.movie.controller;

import com.jansora.movie.model.elasticsearch.Movie;
import com.jansora.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@Slf4j
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("findAll")
    public Flux<Movie> findAll() {

        return movieService.findAll();
    }
    @GetMapping("findById/{id}")
    public Mono<Movie> findById(@PathVariable String id) {
        return movieService.findById(id);

    }

    @PostMapping("add")
    public Mono<Movie> add(@RequestBody Movie movie) {

        return movieService.add(movie);
    }

    @PostMapping("update/{id}")
    public Mono<Movie> update(@PathVariable String id, Movie movie) {
        movie.setId(id);
        return movieService.update(movie);
    }

    @DeleteMapping("delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return movieService.delete(id);
    }
}