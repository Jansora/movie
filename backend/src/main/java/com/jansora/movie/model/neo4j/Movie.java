package com.jansora.movie.model.neo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

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
@Document(indexName = "movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    @Field
    private String type;
    @Field
    private String score;
    @Field
    private String actor;

    @Field
    private String director;
    @Field
    private String area;
    @Field
    private String feature;


}