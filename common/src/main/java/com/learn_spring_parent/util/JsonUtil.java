package com.learn_spring_parent.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    public static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(JsonUtil.class);

    public static String marshalString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("JSON marshal error: " + object, e);
            return "";
        }
    }

    public static String mustMarshalString(Object object) {
        String result = marshalString(object);
        if ( "".equals(result) ) throw new RuntimeException("Can not marshal object: " + object);
        return result;
    }

    public static <T> T unmarshal(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("JSON unmarshal error: " + json, e);
            return null;
        }
    }

    public static <T> T mustUnmarshal(String json, Class<T> clazz) {
        T result = unmarshal(json, clazz);
        if ( result == null ) throw new RuntimeException("Can not unmarshal json: " + json);
        return result;
    }

    public static <E> List<E> unmarshalAsList(String json, Class<E> clazz) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            logger.error("JSON unmarshal error: " + json, e);
            return null;
        }
    }

    public static <K, V> Map<K, V> unmarshalAsMap(String json, Class<K> kclazz, Class<V> vclazz) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kclazz, vclazz));
        } catch (IOException e) {
            logger.error("JSON unmarshal error: " + json, e);
            return null;
        }
    }
}
