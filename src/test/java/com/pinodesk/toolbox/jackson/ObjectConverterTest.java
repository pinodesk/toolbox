package com.pinodesk.toolbox.jackson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinodesk.toolbox.data.MapBuilder;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ObjectConverterTest {

    private final ObjectConverter converter = new ObjectConverter(new ObjectMapper());

    private final TestObject testObject = new TestObject("a", "b");

    @Test
    void testConvertObject_shouldSucceed() {
        OtherObject result = converter.convertObject(testObject, OtherObject.class);
        assertThat(result, notNullValue());
        assertThat(result.getName(), equalTo(testObject.getName()));
        assertThat(result.getValue(), equalTo(testObject.getValue()));
    }

    @Test
    void testConvertObject_usingMap_shouldSucceed() {
        Map<String, String> map = new MapBuilder<String, String>().put("name", "a").put("value", "b").build();
        OtherObject result = converter.convertObject(map, OtherObject.class);
        assertThat(result, notNullValue());
        assertThat(result.getName(), equalTo(map.get("name")));
        assertThat(result.getValue(), equalTo(map.get("value")));
    }

    @Test
    void testConverOptional_empty_shouldReturnNull() {
        TestObject result = converter.convertOptional(Optional.ofNullable(null), TestObject.class);
        assertThat(result, nullValue());
    }

    @Test
    void testConvertOptional_notEmpty_shouldSucceed() {
        OtherObject result = converter.convertOptional(Optional.of(testObject), OtherObject.class);
        assertThat(result, notNullValue());
        assertThat(result.getName(), equalTo(testObject.getName()));
        assertThat(result.getValue(), equalTo(testObject.getValue()));
    }

    @Test
    void testConverOptionalOrThrow_empty_shouldThrowException() {
        final Optional<TestObject> optional = Optional.empty();
        final Exception ex = new Exception("error");
        Exception result = assertThrows(
                Exception.class,
                () -> converter.convertOptionalOrThrow(optional, TestObject.class, ex));
        assertThat(result.getMessage(), equalTo("error"));
    }

    @Test
    void testConverOptionalOrThrow_notEmpty_shouldSucceed() {
        OtherObject result = converter
                .convertOptionalOrThrow(Optional.of(testObject), OtherObject.class, new NullPointerException("error"));
        assertThat(result, notNullValue());
        assertThat(result.getName(), equalTo(testObject.getName()));
        assertThat(result.getValue(), equalTo(testObject.getValue()));
    }

    @Test
    void testConvertList_shouldSucceed() {
        List<OtherObject> result = converter.convertList(Collections.singletonList(testObject), OtherObject.class);
        assertThat(result, notNullValue());
        assertThat(result, hasSize(1));
        assertThat(result, hasItem(new OtherObject("a", "b")));
    }

    @Test
    void testConvertToMap_shouldSucceed() {
        Map<String, String> result = converter.convertToMap(testObject);
        assertThat(result, notNullValue());
        assertThat(result, allOf(hasEntry("name", "a"), hasEntry("value", "b")));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class TestObject {
        private String name;
        private String value;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class OtherObject {
        private String name;
        private String value;
    }

}
