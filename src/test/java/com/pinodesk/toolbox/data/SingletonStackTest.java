package com.pinodesk.toolbox.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

public class SingletonStackTest {

    @Test
    void testAll() {
        assertThat(SingletonStack.INSTANCE.pop(), nullValue());
        SingletonStack.INSTANCE.push(null);
        SingletonStack.INSTANCE.push("Test 1");
        SingletonStack.INSTANCE.push(2);
        assertThat(SingletonStack.INSTANCE.size(), is(2));
        assertThat((int) SingletonStack.INSTANCE.pop(), is(2));
        assertThat((String) SingletonStack.INSTANCE.pop(), is("Test 1"));
        assertThat(SingletonStack.INSTANCE.pop(), nullValue());
        SingletonStack.INSTANCE.push("Test 3");
        SingletonStack.INSTANCE.clear();
        assertThat(SingletonStack.INSTANCE.isEmpty(), is(true));
        assertThat(SingletonStack.INSTANCE.size(), is(0));
    }

}
