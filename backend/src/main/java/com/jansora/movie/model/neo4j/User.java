package com.jansora.movie.model.neo4j;

import com.jansora.movie.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.neo4j.driver.Session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

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

public class User implements Serializable {

    private String userScore;

    private String name;

    private String commentDate;

    private String movie;



    public User(String ...args){
        if(null != args && args.length == 6) {
            userScore = args[0];
            name = args[1];
            commentDate = args[2];
//            userId = args[3]; 字段无意义, username相同的用户视为同一用户
            movie = args[4];
//            type = args[5]; //与movie字段重复
        } else {
            throw new IllegalArgumentException("args is null or args length is error");
        }
        ObjectUtils.AssertArgsIsNotNull(this, this.getClass());
    }

    public void insertToNeo4j(Session session) {

        StringBuilder user = new StringBuilder();
        ArrayList<String> fields = new ArrayList<>();
        StringBuilder properties = new StringBuilder();
        ArrayList<String> relationShips = new ArrayList<>();
        Arrays.stream(this.getClass().getDeclaredFields())
                .forEach( field -> {
                            try {
                                String curName = field.getName();
                                String value = field.get(this).toString();

                                properties.append(String.format(" u.%s=\"%s\" , ", curName, value));

                                if("movie".equals(curName)) {
                                    fields.add(String.format(" MERGE (m:%s {name: \"%s\" })" +
                                                    "ON CREATE SET m.name =  \"%s\" , m.created = \"%s\" , m.updated = \"%s\" " +
                                                    "ON MATCH  SET m.name =  \"%s\" , m.updated = \"%s\" ",
                                            curName, value, value, ObjectUtils.GetCurDate(), ObjectUtils.GetCurDate(), value, ObjectUtils.GetCurDate()));


                                    relationShips.add(String.format(" MATCH (u:user {name: \"%s\" }) " +
                                                    "MATCH (a:%s {name: \"%s\"}) " +
                                                    "MERGE (a)-[:%sOf]->(u) MERGE (u)-[:%sIs]->(a) ;",
                                            this.getName(), curName, value, curName, curName));
                                }


                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }

                );


        user.append(String.format(" MERGE (u:user { name:\"%s\" }) " +
                        " ON CREATE SET %s  u.created = \"%s\", u.updated = \"%s\" " +
                        " ON MATCH  SET %s  u.updated = \"%s\" " ,
                getName(),
                properties, ObjectUtils.GetCurDate(), ObjectUtils.GetCurDate(),
                properties, ObjectUtils.GetCurDate()
        ));

        session.run(user.toString());
        fields.forEach(session::run);
        relationShips.forEach(session::run);

    }
}