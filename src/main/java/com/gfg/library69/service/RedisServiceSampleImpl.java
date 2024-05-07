package com.gfg.library69.service;

import com.gfg.library69.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceSampleImpl {


    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    public void addBook(Book book)
    {

    }




}
