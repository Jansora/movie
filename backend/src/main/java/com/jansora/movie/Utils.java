package com.jansora.movie;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/*
 * 〈一句话功能简述〉<br>
 * @file Utils.java
 * @description Utils
 *
 * @author 18044846 张洋源
 * @date 2020-08-11 10:14
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Utils {

    public static void AssertArgsIsNotNull(Object o, Class clazz) throws IllegalArgumentException {


    Field[] fields = clazz.getDeclaredFields();

    Arrays.stream(fields).forEach(field -> {
        field.setAccessible(true);
        try {
            if (null == field.get(o)) {
                throw new IllegalArgumentException(field.toString() + "is null !");
            }
        } catch (IllegalAccessException ignored) {

        }

    });
    }


    public static String GetCurDate() {
        return java.time.LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace("T", "");

    }

}