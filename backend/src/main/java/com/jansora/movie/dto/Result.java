package com.jansora.movie.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/*
 * 〈一句话功能简述〉<br>
 * @file Result.java
 * @description Result
 *
 * @author 18044846
 * @date 2020-06-03 11:07
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Data
@ToString
@Builder
public class Result<T> implements Serializable {
    private int total;
    private Object data;
    private String message;
    private Boolean status = true;

    public final Result setSuccess(List<T> data) {
        return Result.builder()
                .status(true)
                .data((Object) data).build();
    }
    public final Result setSuccess(T data) {
        return Result.builder()
                .status(true)
                .data((Object) data).build();
    }

    public final Result setFailed (String message) {
        return (Result) Result.builder()
                .status(false)
                .data(data).build();
    }


}