package com.jansora.movie;

import com.jansora.movie.utils.Neo4jClient;
import com.jansora.movie.model.neo4j.Movie;
import com.jansora.movie.model.neo4j.User;
import org.junit.jupiter.api.Test;

import org.neo4j.driver.Session;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootTest
class Neo4jApplicationTests {



    @Test
    void contextLoads() {
    }



    @Test
    void add() {
        System.out.println("add-movie--start" + System.currentTimeMillis());
//        addMovies();
        System.out.println("add-movie--completed" + System.currentTimeMillis());

        System.out.println("add-user--start" + System.currentTimeMillis());
        addUsers();
        System.out.println("add-user--completed" + System.currentTimeMillis());

    }


    @Test
    void addMovies() {

        Neo4jClient client = new Neo4jClient();
        Session session = client.getSession();

        Path path = Paths.get(System.getProperty("user.dir"), "src", "test", "movie.csv");

        System.out.println("Working Directory = " + path.toString());

        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            AtomicInteger i = new AtomicInteger(0);
            while ((line = br.readLine()) != null ) {
                if(0 == i.getAndIncrement()) continue;
                if(i.get() == 1000) return;
                line = line.replace("'", "").replace("\"", "");
                Movie movie =  new Movie(line.split(","));
                movie.insertToNeo4j(session);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    void addUsers() {

        Neo4jClient client = new Neo4jClient();
        Session session = client.getSession();

        Path path = Paths.get(System.getProperty("user.dir"), "src", "test", "user.csv");

        System.out.println("Working Directory = " + path.toString());

        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            AtomicInteger i = new AtomicInteger(0);
            while ((line = br.readLine()) != null ) {
                if(0 == i.getAndIncrement()) continue;
                if(i.get() == 1000) return;
                line = line.replace("'", "").replace("\"", "");
                if(line.split(",").length == 6) {
                    User user =  new User(line.split(","));
                    user.insertToNeo4j(session);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    void testBug() {

        Neo4jClient client = new Neo4jClient();
        Session session = client.getSession();

        Path path = Paths.get(System.getProperty("user.dir"), "src", "test", "user.csv");

        System.out.println("Working Directory = " + path.toString());

        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            AtomicInteger i = new AtomicInteger(0);
            while ((line = br.readLine()) != null ) {
                if(0 == i.getAndIncrement()) continue;
                if(i.get() == 1000) return;
                line = line.replace("'", "").replace("\"", "");
                System.out.println(i.get() + " " + line.split(",").length);
                if(line.split(",").length == 6) {
                    User user =  new User(line.split(","));
                    user.insertToNeo4j(session);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
