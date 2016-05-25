package com.maitaidan.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * Created by Crytis on 2016/3/15.
 * Just test.
 */
@Service
public class CacheService {
    private static LoadingCache<String, String> cahce = CacheBuilder.newBuilder().expireAfterAccess(15, TimeUnit.DAYS).build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            return null;
        }
    });
}
