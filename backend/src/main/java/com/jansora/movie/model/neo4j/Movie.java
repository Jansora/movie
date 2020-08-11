package com.jansora.movie.model.neo4j;

import com.jansora.movie.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.neo4j.driver.Session;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 〈一句话功能简述〉<br>
 * @file User.java
 * @description User
 *
 * @author Jansora
 * @date 2020-04-29 15:26
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Data
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

public class Movie implements Serializable {


    private String name;

    private String type;

    private String score;

    private List<String> actor;

    private List<String> director;

    private String area;

    private String feature;


    public Movie(String ...args){
        if(null != args && args.length == 7) {
            type = args[0];
            actor = Arrays.asList(args[1].split("\\|"));
            area = args[2];
            director = Arrays.asList(args[3].split("\\|"));
            feature = args[4];
            score = args[5];
            name = args[6];
        } else {
            throw new IllegalArgumentException("args is null or args length is error");
        }
        Utils.AssertArgsIsNotNull(this, this.getClass());
    }


    public void insertToNeo4j(Session session) {

        StringBuilder movie = new StringBuilder();
        ArrayList<String> fields = new ArrayList<>();
        StringBuilder properties = new StringBuilder();
        ArrayList<String> relationShips = new ArrayList<>();
        Arrays.stream(this.getClass().getDeclaredFields())
            .forEach( field -> {
                try {
                    String curName = field.getName();
                    String value = field.get(this).toString()
                            .replace("[", "").replace("]", "");
                    if(!"".equals(properties.toString())) {
                        properties.append(",");
                    }
                    properties.append(String.format(" %s: \"%s\" ", curName, value));

                    if(!"name".equals(curName)) {
                        if("actor".equals(curName) || "director".equals(curName)) {

                            List<String> persons = new ArrayList<>();
                            if("actor".equals(curName)) persons = this.getActor();
                            if("director".equals(curName)) persons = this.getDirector();

                            persons.forEach(person -> {
                                fields.add(String.format(" MERGE (:%s {name: \"%s\" }); ",
                                        curName, person));
                                relationShips.add(String.format(" MATCH (m:movie {name: \"%s\" }) " +
                                                "MATCH (a:%s {name: \"%s\"}) " +
                                                "MERGE (a)-[:%sOf]->(m) MERGE (m)-[:%sIs]->(a) ;",
                                        this.getName(), curName, person, curName, curName));
                            });
                            return;
                        }

                        fields.add(String.format(" MERGE (:%s {name: \"%s\" }); ", curName, value));

                        relationShips.add(String.format(" MATCH (m:movie {name: \"%s\" }) " +
                                "MATCH (a:%s {name: \"%s\"}) " +
                                "MERGE (a)-[:%sOf]->(m) MERGE (m)-[:%sIs]->(a) ;",
                                this.getName(), curName, value, curName, curName));
                    }


                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        );
        movie.append(String.format("MERGE (m:movie { %s }) ;", properties));

        session.run(movie.toString());
        fields.forEach(session::run);
        relationShips.forEach(session::run);

    }


}