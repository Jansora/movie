package com.jansora.movie;

import com.jansora.movie.client.Neo4jClient;
import com.jansora.movie.model.neo4j.Movie;
import org.junit.jupiter.api.Test;

import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootTest
class Neo4jApplicationTests {



    @Test
    void contextLoads() {
    }



    @Test
    void addMovies() {

        Neo4jClient client = new Neo4jClient();
        Session session = client.getSession();

        List<List<String>> records = new ArrayList<>();
        String[] headers = new String[]{"type","actor","area","director","feature","score","name"};

        System.out.println(Paths.get(""));
        Path path = Paths.get(System.getProperty("user.dir"), "src", "test", "movie.csv");

        System.out.println("Working Directory = " + path.toString());

        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            AtomicInteger i = new AtomicInteger(0);
            while ((line = br.readLine()) != null ) {
                if(0 == i.getAndIncrement()) continue;
                Movie movie =  new Movie(line.split(","));
                records.add(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    void addMovie() {

        Neo4jClient client = new Neo4jClient();
        Session session = client.getSession();
        Result result = session.run(
                "CREATE (baeldung:Company {name:\"Baeldung\"}) " +
                        "-[:owns]-> (tesla:Car {make: 'tesla', model: 'modelX'})" +
                        "RETURN baeldung, tesla");
        List<Record> rs = result.list();
        System.out.println(rs);

    }




}
