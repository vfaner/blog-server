package com.rgh.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: rgh
 * @description: json字符串转换
 */
public class JacksonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> List<T> jsonToList(@NonNull String jsonString, Class<T> cls) {

        try {
            return mapper.readValue(jsonString, getCollectionType(cls));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String listToString(List<T> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * map转string
     * @param map map集合
     * @return string
     * @param <T> string
     */
    public static <T> String mapToString(@NonNull Map<T,T> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Object对象转List集合
     */
    public static <T> List<T> objectToList(@NonNull Object obj, Class<T> cls) {
        try {
            String s = mapper.writeValueAsString(obj);
            return jsonToList(s, cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Object对象转byte[]
     */

    public static byte[] objectToByte(@NonNull Object obj) {
        try {
            return  mapper.writeValueAsBytes(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Object对象转指定对象
     */
    public static <T> T objectToCls(@NonNull Object obj, Class<T> cls) {
        return  mapper.convertValue(obj,cls);
    }
    /**
     * 获取泛型的Collection Type
     *
     * @param elementClasses 实体bean
     * @return JavaType Java类型
     */
    private static JavaType getCollectionType(Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(List.class, elementClasses);
    }
}
