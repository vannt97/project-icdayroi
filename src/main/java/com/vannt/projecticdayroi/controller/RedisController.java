package com.vannt.projecticdayroi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/redis")
    public ResponseEntity<?> testRedis(){
        redisTemplate.opsForValue().set("redis", "hhhelo world");

        return new ResponseEntity<Object>(redisTemplate.opsForValue().get("redis"), HttpStatus.OK);
    }
}
