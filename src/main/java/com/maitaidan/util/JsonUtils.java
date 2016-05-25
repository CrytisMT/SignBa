package com.maitaidan.util;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static JsonFactory jsonFactory = objectMapper.getFactory();

    public static String toJSONString(Object obj) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(out, JsonEncoding.UTF8);
            jsonGenerator.writeObject(obj);
            String jsonStr = new String(out.toByteArray(), "UTF-8");
            jsonGenerator.close();
            return jsonStr;
        } catch (Exception e) {
            logger.error("parse json string error", e);
            return StringUtils.EMPTY;
        }
    }

    public static <T> T parse(String jsonStr, Class<T> clazz) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//兼容pojo增加字段的版本升级
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parseList(String jsonStr, Class<T> clazz) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//兼容pojo增加字段的版本升级
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
