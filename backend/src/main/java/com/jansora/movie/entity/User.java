package com.jansora.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "user-index", type = "user")
public class User {
    @Id
    private Long id;

    private String score;

    private String username;

    @Field(type = FieldType.Date)
    private String commentDate;

    private String movie;

    private String type;


}