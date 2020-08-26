package com.jansora.movie.utils;

import com.jansora.movie.dto.Result;
import com.sun.istack.internal.NotNull;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;


/*
 * 〈一句话功能简述〉<br>
 * @file ElasticSearchClient.java
 * @description ElasticSearchClient
 *
 * @author Jansora
 * @date 2020-08-10 17:40
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ElasticSearchClient {

    Logger logger = LoggerFactory.getLogger(ElasticSearchClient.class);


    private static final HttpHost httpHost = new HttpHost("10.243.147.88", 9200, "http");

    private static final RestClientBuilder restClientBuilder = RestClient.builder(httpHost);

    public static final RestClient restLowLevelClient = restClientBuilder.build();
    public static final RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);




}