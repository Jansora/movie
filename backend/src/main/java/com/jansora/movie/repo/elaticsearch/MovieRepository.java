package com.jansora.movie.repo.elaticsearch;

import com.jansora.movie.dto.Result;
import com.jansora.movie.model.elasticsearch.Movie;
import com.jansora.movie.utils.ElasticSearchClient;
import com.jansora.movie.utils.ResultUtils;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class MovieRepository {

    Logger logger = LoggerFactory.getLogger(MovieRepository.class);

    private final RestHighLevelClient client = ElasticSearchClient.client;


    public OpenIndexResponse GetIndex(@NotNull String index) {
        OpenIndexRequest request = new OpenIndexRequest(index);

        try {
            OpenIndexResponse openIndexResponse = client.indices().open(
                    request, RequestOptions.DEFAULT
            );
            return openIndexResponse;
        } catch (IOException exception) {
            logger.error("Get Index Error" , exception);
        }
        return null;
    }

    public boolean ExistIndex(@NotNull String index) {

        GetIndexRequest getRequest = new GetIndexRequest(index);
        getRequest.local(false);
        getRequest.humanReadable(true);

        try {
            return client.indices().exists(getRequest, RequestOptions.DEFAULT);;
        } catch (IOException exception) {
            logger.error("ExistIndex Error" , exception);
        }
        return false;
    }
    private class Index {
        String settings;
        String index;
        Object[] properties;

    }

    @Data
    private class Property {

        HashMap<String, Object> data;
        public Property() {
            this.data = new HashMap<String, Object>();
        }
        public Property(String key, Object value) {
            this.data = new HashMap<String, Object>();
            this.data.put(key, value);
        }
        public Property update(String key, Object value) {
            if(null == this.data)
                this.data = new HashMap<String, Object>();
            this.data.put(key, value);
            return this;
        }
    }


    private Settings _Settings(int numberOfShards, int numberOfReplicas) {
        return Settings.builder()
                .put("index.number_of_shards", numberOfShards)
                .put("index.number_of_replicas", numberOfReplicas)
                .build();
    }

    private void placeholder() {

        HashMap<String, Object> keyword = new HashMap<>();
        //设置类型
        keyword.put("type", "keyword");
        HashMap<String, Object> lon = new HashMap<>();
        //设置类型
        lon.put("type", "long");
        HashMap<String, Object> date = new HashMap<>();
        //设置类型
        date.put("type", "date");
        date.put("format", "yyyy-MM-dd HH:mm:ss");

        HashMap<String, Object> jsonMap2 = new HashMap<>();
        Property property = new Property();
        //设置字段message信息
        property.update("uid", new Property("type", "long").getData());
        property.update("phone", lon);
        property.update("msgcode", lon);
        property.update("message", keyword);
        property.update("sendtime", new Property("type", "date").update("format", "yyyy-MM-dd HH:mm:ss").getData());

    }


    private Result createIndex(String index, Settings settings, HashMap<String, Object> properties) throws IOException {

        HashMap<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);

        //如果存在就不创建了
        if(ExistIndex(index)) {
            return ResultUtils.FAILED("索引库已经存在!");
        }

        try {
            // 开始创建库
            CreateIndexRequest request = new CreateIndexRequest(index);
            // 加载数据类型
            request.settings(settings);
            //设置mapping参数
            request.mapping(mapping);
//            //设置别名
//            request.alias(new Alias("pancm_alias"));
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

            return ResultUtils.FormatResult(createIndexResponse.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtils.FAILED("创建异常");

        }

    }

}
