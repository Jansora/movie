package com.jansora.movie.utils;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

/*
 * 〈一句话功能简述〉<br>
 * @file Neo4jClient.java
 * @description Neo4jClient
 *
 * @author Jansora
 * @date 2020-08-10 17:40
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Neo4jClient {
    private static final Driver driver = GraphDatabase.driver(
            "bolt://10.243.147.87:7687", AuthTokens.basic("neo4j", "123456"));

    public Session getSession() {
        return driver.session();
    }

}