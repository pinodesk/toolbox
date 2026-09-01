package com.pinodesk.toolbox.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class StringNumberUtilsTest {

    @Test
    void testToStringOrDefault_forBigDecimal() {
        String result = StringNumberUtils.toStringOrDefault(new BigDecimal("2"), "");
        assertThat(result, equalTo("2"));
        result = StringNumberUtils.toStringOrDefault((BigDecimal) null, "test");
        assertThat(result, equalTo("test"));
    }

    @Test
    void testToStringOrNull_forBigDecimal() {
        String result = StringNumberUtils.toStringOrNull((BigDecimal) null);
        assertThat(result, nullValue());
    }

    @Test
    void testToStringOrEmpty_forBigDecimal() {
        String result = StringNumberUtils.toStringOrEmpty((BigDecimal) null);
        assertThat(result, emptyString());
    }

    @Test
    void testToStringOrDefault_forInteger() {
        String result = StringNumberUtils.toStringOrDefault(2, "");
        assertThat(result, equalTo("2"));
        result = StringNumberUtils.toStringOrDefault((Integer) null, "test");
        assertThat(result, equalTo("test"));
    }

    @Test
    void testToStringOrNull_forInteger() {
        String result = StringNumberUtils.toStringOrNull((Integer) null);
        assertThat(result, nullValue());
    }

    @Test
    void testToStringOrEmpty_forInteger() {
        String result = StringNumberUtils.toStringOrEmpty((Integer) null);
        assertThat(result, emptyString());
    }

    @Test
    void testToBigDecimalOrDefault() {
        BigDecimal result = StringNumberUtils.toBigDecimalOrDefault("2", BigDecimal.ZERO);
        assertThat(result, equalTo(new BigDecimal("2.00")));
        result = StringNumberUtils.toBigDecimalOrDefault(null, BigDecimal.ZERO);
        assertThat(result, equalTo(BigDecimal.ZERO));
    }

    @Test
    void testToBigDecimalOrNull() {
        BigDecimal result = StringNumberUtils.toBigDecimalOrNull(null);
        assertThat(result, nullValue());
    }

    @Test
    void testToIntegerOrDefault() {
        Integer result = StringNumberUtils.toIntegerOrDefault("2", 0);
        assertThat(result, equalTo(2));
        result = StringNumberUtils.toIntegerOrDefault(null, 0);
        assertThat(result, equalTo(0));
        result = StringNumberUtils.toIntegerOrDefault("2.37894", 0);
        assertThat(result, equalTo(2));
    }

    @Test
    void testToIntegerOrNull() {
        Integer result = StringNumberUtils.toIntegerOrNull(null);
        assertThat(result, nullValue());
    }

    @Test
    void testToDoubleOrDefault() {
        Double result = StringNumberUtils.toDoubleOrDefault("2", 0d);
        assertThat(result, equalTo(2d));
        result = StringNumberUtils.toDoubleOrDefault(null, 10d);
        assertThat(result, equalTo(10d));
        result = StringNumberUtils.toDoubleOrDefault("2.37894", 10d);
        assertThat(result, equalTo(2.37894));
    }

}
