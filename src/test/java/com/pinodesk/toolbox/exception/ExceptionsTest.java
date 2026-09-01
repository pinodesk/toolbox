package com.pinodesk.toolbox.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class ExceptionsTest {

    @Test
    void testGetMessage_shouldReturnMessage() {
        Exception ex = new Exception("message");
        String message = Exceptions.getMessage(ex);
        assertThat(message, is("message"));
    }

    @Test
    void testGetMessage_withNullMessage_shouldReturnMessage() {
        Exception ex = new Exception();
        String message = Exceptions.getMessage(ex);
        assertThat(message, is(ex.toString()));
    }

    @Test
    void testGetMessageOrDefault_shouldReturnExeptionMessage() {
        Exception ex = new Exception("message");
        String message = Exceptions.getMessageOrDefault(ex, null);
        assertThat(message, is("message"));
    }

    @Test
    void testGetMessageOrDefault_withNullExceptionMessage_shouldReturnDefaultMessage() {
        Exception ex = new Exception();
        String message = Exceptions.getMessageOrDefault(ex, "default message");
        assertThat(message, is("default message"));
    }

}
