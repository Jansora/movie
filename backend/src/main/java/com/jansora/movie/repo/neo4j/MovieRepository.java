package com.jansora.movie.repo.neo4j;

import com.jansora.movie.model.elasticsearch.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;


public interface MovieRepository extends Neo4jRepository<Movie, String> {

    List<Movie> findFirstByName(String username);

}
