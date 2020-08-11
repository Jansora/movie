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

        StringBuilder result = new StringBuilder();
        StringBuilder movie = new StringBuilder();
        StringBuilder fields = new StringBuilder();
        StringBuilder properties = new StringBuilder("{ ");
        StringBuilder relationShips = new StringBuilder("");

        Arrays.stream(this.getClass().getDeclaredFields())
            .forEach( field -> {
                try {
                    String curName = field.getName();
                    String value = field.get(this).toString();
                    properties.append(System.out.printf(" %s: '%s', ", curName, value));

                    if(!"name".equals(curName)) {
                        fields.append(System.out.printf(" MERGE (:%s {name: '%s' }); ", curName, value));

                        relationShips.append(System.out.printf(" MATCH (m:movie {name: '%s' }) " +
                                "MATCH (a:%s {name: '%s'}) " +
                                "MERGE (a)-[:%sOf]->(m) MERGE (m)-[:%sIs]->(a) ;",
                                this.getName(), curName, value, curName, curName));
                    }


                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        );
        properties.append(" } ");
        movie.append(System.out.printf("MERGE (m:movie { %s }) ;", properties));





        StringBuilder area = new StringBuilder();
        area.append(System.out.printf("MERGE (:area {name: '%s' });", area));
        area.append(System.out.printf(" MATCH (m:movie {name: '%s' }) " +
                "MATCH (a:area {name: '%s'}) " +
                "MERGE (a)-[:areaOf]->(m) MERGE (m)-[:areaWith]->(a)\n", name, area));

        session.run(movie.toString());
        session.run(fields.toString());
        session.run(relationShips.toString());


    }




    public String buildCQL(Session session) {
        StringBuilder result = new StringBuilder();
        result.append(System.out.printf("MERGE (m:movie { %s }) ",
                buildProperty()));

        return result.toString();
    }

    private String buildTypeCQL() {
        StringBuilder area = new StringBuilder();
        area.append(System.out.printf("MERGE (:type {name: '%s' })", area));
        return area.toString();
    }

    private String buildAreaCQL() {
        StringBuilder area = new StringBuilder();
        area.append(System.out.printf("MERGE (:area {name: '%s' });", area));
        area.append(System.out.printf(" MATCH (m:movie {name: '%s' }) " +
                "MATCH (a:area {name: '%s'}) " +
                "MERGE (a)-[:areaOf]->(m) MERGE (m)-[:areaWith]->(a)\n", name, area));
        return area.toString();
    }

    private String buildRelationShip() {
        StringBuilder relationShip = new StringBuilder();
        relationShip.append(System.out.printf("type: '%s', ", type));
        relationShip.append(System.out.printf("area: '%s', ", area));
        relationShip.append(System.out.printf("actor: '%s', ", actor.toString()));
        relationShip.append(System.out.printf("director: '%s', ", director.toString()));
        relationShip.append(System.out.printf("name: '%s', ", name));
        relationShip.append(System.out.printf("feature: '%s', ", feature));
        relationShip.append(System.out.printf("score: '%s', ", score));

        return relationShip.toString();
    }
    private String buildProperty() {
        StringBuilder property = new StringBuilder();
        property.append(System.out.printf("type: '%s', ", type));
        property.append(System.out.printf("area: '%s', ", area));
        property.append(System.out.printf("actor: '%s', ", actor.toString()));
        property.append(System.out.printf("director: '%s', ", director.toString()));
        property.append(System.out.printf("name: '%s', ", name));
        property.append(System.out.printf("feature: '%s', ", feature));
        property.append(System.out.printf("score: '%s', ", score));

        return property.toString();
    }
}