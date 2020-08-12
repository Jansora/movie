package com.jansora.movie.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;


/*
 * 〈一句话功能简述〉<br>
 * @file ElasticSearchClient.java
 * @description Neo4jClient
 *
 * @author Jansora
 * @date 2020-08-10 17:40
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ElasticSearchClient {

    private static final RestClientBuilder client = RestClient.builder(
            new HttpHost("deepin.jansora.com", 19272, "http"));
    private static final RestHighLevelClient restHighLevelClient = new RestHighLevelClient(client);
    public RestHighLevelClient getClient() {
        return restHighLevelClient;
    }

}