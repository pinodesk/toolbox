package com.pinodesk.toolbox.data;

import java.util.ArrayDeque;
import java.util.Deque;

public final class SingletonStack {

    Deque<Object> stack = new ArrayDeque<>();

    public static final SingletonStack INSTANCE = new SingletonStack();

    private SingletonStack() {
    }

    public <T> void push(T data) {
        if (data == null) {
            return;
        }
        stack.push(data);
    }

    @SuppressWarnings("unchecked")
    public <T> T pop() {
        if (stack.isEmpty()) {
            return null;
        }
        return (T) stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void clear() {
        stack.clear();
    }

    public int size() {
        return stack.size();
    }

}
