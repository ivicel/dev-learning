package com.mmall.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public final class CacheUtils {

    private static final LoadingCache<String, String> cache;

    static {
        cache = CacheBuilder.newBuilder().initialCapacity(1000)
                .maximumSize(10000).expireAfterWrite(24, TimeUnit.HOURS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return "null";
                    }
                });
    }

    public static void put(String key, String value) {
        cache.put(key, value);
    }

    public static String get(String key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void remove(String key) {
        cache.invalidate(key);
    }
}
