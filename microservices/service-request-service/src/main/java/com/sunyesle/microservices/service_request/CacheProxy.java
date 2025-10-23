package com.sunyesle.microservices.service_request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheProxy<T> {
    private final Map<String, T> cache = new ConcurrentHashMap<>();

    public T get(String key) {
        return cache.get(key);
    }

    public void put(String key, T value) {
        cache.put(key, value);
    }

    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }
}
