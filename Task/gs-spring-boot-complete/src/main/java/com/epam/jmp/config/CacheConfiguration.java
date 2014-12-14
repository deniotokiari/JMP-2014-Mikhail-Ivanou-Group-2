package com.epam.jmp.config;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public SimpleCacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(messagesCache().getObject());
        caches.add(booksCache().getObject());
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    @Bean
    public ConcurrentMapCacheFactoryBean messagesCache(){
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("messagesCache");
        return cacheFactoryBean;
    }
    
    @Bean
    public ConcurrentMapCacheFactoryBean booksCache(){
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("booksCache");
        return cacheFactoryBean;
    }

}
