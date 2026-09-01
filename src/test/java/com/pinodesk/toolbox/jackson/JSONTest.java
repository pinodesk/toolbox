package com.pinodesk.toolbox.jackson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.Test;

import lombok.Data;

public class JSONTest {

    private static final String NEWLINE = System.lineSeparator();
    private static final String JSON_NO_INDENT = "{\"firstName\":\"Anita\",\"lastName\":\"Saraswati\"}";
    private static final String JSON_WITH_INDENT = "{\n  \"firstName\" : \"Anita\",\n  \"lastName\" : \"Saraswati\"\n}"
            .replaceAll("\n", NEWLINE);

    @Test
    void testParse_acceptClass_shouldReturnObject() {
        Person result = JSON.parse(JSON_NO_INDENT, Person.class);
        assertThat(result, notNullValue());
        assertThat(result.getFirstName(), is("Anita"));
        assertThat(result.getLastName(), is("Saraswati"));
    }

    @Test
    void testParse_acceptClass_withJsonNull_shouldReturnNull() {
        Person result = JSON.parse(null, Person.class);
        assertThat(result, is(nullValue()));
    }

    @Test
    void testParse_acceptClass_parseFailed_shouldReturnNull() {
        Person result = JSON.parse("test", Person.class);
        assertThat(result, is(nullValue()));
    }

    @Test
    void testParse_acceptTypeReference_shouldReturnMap() {
        Map<String, String> result = JSON.parse(JSON_NO_INDENT, new TypeReference<Map<String, String>>() {
        });
        assertThat(result, notNullValue());
        assertThat(result, hasEntry("firstName", "Anita"));
        assertThat(result, hasEntry("lastName", "Saraswati"));
    }

    @Test
    void testParse_acceptTypeReference_withJsonNull_shouldReturnNull() {
        Map<String, String> result = JSON.parse(null, new TypeReference<Map<String, String>>() {
        });
        assertThat(result, is(nullValue()));
    }

    @Test
    void testParse_acceptTypeReference_parseFailed_shouldReturnNull() {
        Map<String, String> result = JSON.parse("test", new TypeReference<Map<String, String>>() {
        });
        assertThat(result, is(nullValue()));
    }

    @Test
    void testStringify_acceptPrettyPrint_shouldReturnJsonString() {
        Person person = new Person();
        person.setFirstName("Anita");
        person.setLastName("Saraswati");
        String result = JSON.stringify(person, true);
        assertThat(result, notNullValue());
        assertThat(result, is(JSON_WITH_INDENT));
    }

    @Test
    void testStringify_acceptPrettyPrint_withNullObject_shouldReturnNull() {
        String result = JSON.stringify(null, true);
        assertThat(result, is(nullValue()));
    }

    @Test
    void testStringify_acceptPrettyPrint_failedToStringify_shouldReturnNull() {
        String result = JSON.stringify(new Object(), true);
        assertThat(result, is(nullValue()));
    }

    @Test
    void testStringify_shouldReturnJsonString() {
        Person person = new Person();
        person.setFirstName("Anita");
        person.setLastName("Saraswati");
        String result = JSON.stringify(person);
        assertThat(result, notNullValue());
        assertThat(result, is(JSON_NO_INDENT));
    }

    @Test
    void testStringify_withNullObject_shouldReturnNull() {
        String result = JSON.stringify(null);
        assertThat(result, is(nullValue()));
    }

    @Test
    void testStringify_failedToStringify_shouldReturnNull() {
        String result = JSON.stringify(new Object());
        assertThat(result, is(nullValue()));
    }

    @Test
    void testValidate_shouldReturnTrue() {
        boolean result = JSON.validate(JSON_WITH_INDENT);
        assertThat(result, equalTo(true));
    }

    @Test
    void testValidate_shouldReturnFalse() {
        boolean result = JSON.validate("test");
        assertThat(result, equalTo(false));
    }

    @Data
    private static class Person {
        private String firstName;
        private String lastName;
    }

}
