package com.jansora.movie.repo.neo4j;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * 〈一句话功能简述〉<br>
 * @file Neo4jConfiguration.java
 * @description Neo4jConfiguration
 *
 * @author 18044846
 * @date 2020-06-04 15:46
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Configuration
@EnableNeo4jRepositories
@EnableTransactionManagement
@ComponentScan("com.jansora.movie.repo.neo4j")
public class Neo4jConfiguration {

}