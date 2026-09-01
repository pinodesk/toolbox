package com.pinodesk.toolbox.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ListBuilderTest {

    @Test
    void testBuild_shouldSucceed() {
        List<String> result = new ListBuilder<String>().add("1").add("2").build();
        assertThat(result, notNullValue());
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(is("1"), is("2")));
    }

}
