package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class TestController {
    @GetMapping("get1")
    public String get(){
        log.info("[get1]");
        return "get1---"+System.currentTimeMillis();
    }

    @PostMapping("post1")
    public String post1(@RequestBody UserDto userDto){
        log.info("[post1][{}]", JSONObject.toJSONString(userDto));
        return "ok---"+System.currentTimeMillis();
    }
}
