package com.pinodesk.toolbox.future;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

public class AsyncUtilsTest {

    @Test
    void testSupply_shouldReturnCompletableFuture() throws InterruptedException, ExecutionException {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1;
            }
        };
        CompletableFuture<Integer> result = AsyncUtils.supply(supplier);
        assertThat(result, not(nullValue()));
        assertThat(result.get(), is(1));
    }

    @Test
    void testSupply_throwsException_shouldReturnCompletableFuture() throws InterruptedException, ExecutionException {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                throw new NullPointerException();
            }
        };
        CompletableFuture<Integer> result = AsyncUtils.supply(supplier);
        assertThat(result, not(nullValue()));
        assertThat(result.get(), is(nullValue()));
    }

    @Test
    void testRun_shouldReturnCompletableFuture() throws InterruptedException, ExecutionException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            }
        };
        CompletableFuture<Void> result = AsyncUtils.run(runnable);
        assertThat(result, not(nullValue()));
        assertThat(result.get(), is(nullValue()));
    }

    @Test
    void testRun_throwsException_shouldReturnCompletableFuture() throws InterruptedException, ExecutionException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                throw new NullPointerException();
            }
        };
        CompletableFuture<Void> result = AsyncUtils.run(runnable);
        assertThat(result, not(nullValue()));
        assertThat(result.get(), is(nullValue()));
    }

}
