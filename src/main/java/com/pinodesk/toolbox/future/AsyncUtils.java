package com.pinodesk.toolbox.future;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.pinodesk.toolbox.exception.Exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class AsyncUtils {

    private AsyncUtils() {
    }

    public static <T> CompletableFuture<T> supply(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier).exceptionally(ex -> {
            if (log.isErrorEnabled()) {
                log.error("Supply async error: " + Exceptions.getMessage(ex.getCause()), ex);
            }
            return null;
        });
    }

    public static CompletableFuture<Void> run(Runnable runnable) {
        return CompletableFuture.runAsync(runnable).exceptionally(ex -> {
            if (log.isErrorEnabled()) {
                log.error("Run async error: " + Exceptions.getMessage(ex.getCause()), ex);
            }
            return null;
        });
    }

}
