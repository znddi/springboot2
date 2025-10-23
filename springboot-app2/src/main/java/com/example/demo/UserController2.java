package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.StringRedisTemplate;

@RestController
public class UserController2 {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   //—> get요청(/로 요청한 경우 Spring Boot START PAGE글자가 화면에 보이게함.)
    @GetMapping("/")
    public String hello() {
        return "Spring Boot! START PAGE";
    }

   //→ get요청(/mysql로 요청한 경우 select now() 실행한 결과가 화면에 보이게함.)
   @GetMapping("/mysql")
    public String mysql() {
        try {

            String sql = "SELECT now()";
            String result = jdbcTemplate.queryForObject(sql, String.class);
            return "Database test successful. now() : " + result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Database connection failed! Error: " + e.getMessage();
        }
    }

@Autowired
    private StringRedisTemplate redis;

    @GetMapping("/redis-set")
    public String redisSet() {
        try {
            redis.opsForValue().set("key", "100");
            return "Redis SET OK. key=key, value=100";
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis SET failed! Error: " + e.getMessage();
        }
    }

    @GetMapping("/redis-get")
    public String redisGet() {
        try {
            String value = redis.opsForValue().get("key");
            return "Redis GET OK. key=key >> " + value;
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis GET failed! Error: " + e.getMessage();
        }
    }

}
