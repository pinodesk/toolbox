package com.pinodesk.toolbox.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JSON {

    private static final ObjectMapper MAPPER;

    private JSON() {
    }

    static {
        MAPPER = new ObjectMapper();
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.findAndRegisterModules();
    }

    public static <T> T parse(String json, Class<T> t) {
        if (json == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, t);
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                log.debug("Unable to parse JSON: {}", e.toString());
            }
            return null;
        }
    }

    public static <T> T parse(String json, TypeReference<T> t) {
        if (json == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, t);
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                log.debug("Unable to parse JSON: {}", e.toString());
            }
            return null;
        }
    }

    public static <T> T parse(String json, JavaType type) {
        if (json == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                log.debug("Unable to parse JSON: {}", e.toString());
            }
            return null;
        }
    }

    public static <T> T parse(String json, Class<?> parametrized, Class<?>... parameterClasses) {
        JavaType type = MAPPER.getTypeFactory().constructParametricType(parametrized, parameterClasses);
        return parse(json, type);
    }

    /**
     * Returns JSON string of the specified object in one line. Always mask
     * sensitive data by default.
     *
     * @param object The object to print as JSON string.
     * 
     * @return JSON string of the specified object.
     */
    public static String stringify(Object object) {
        return stringify(object, false);
    }

    /**
     * Returns JSON string of the specified object.
     * 
     * @param object      The object to print as JSON string.
     * @param prettyPrint An option to print in formatted style.
     * 
     * @return JSON string of the specified object.
     */
    public static String stringify(Object object, boolean prettyPrint) {
        if (object == null) {
            return null;
        }
        try {
            String str = MAPPER.writeValueAsString(object);
            if (prettyPrint) {
                str = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            }
            return str;
        } catch (JsonProcessingException e) {
            if (log.isDebugEnabled()) {
                log.debug("Unable to write object as JSON string: {}", e.toString());
            }
        }
        return null;
    }

    public static boolean validate(String json) {
        try {
            MAPPER.readTree(json);
            return true;
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                log.debug("Not a valid JSON: {}", e.toString());
            }
            return false;
        }
    }

}
