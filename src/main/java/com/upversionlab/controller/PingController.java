package com.upversionlab.controller;

import com.upversionlab.model.Pong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PingController {

    @RequestMapping("/ping")
    public Pong ping() {
        return new Pong(new Date());
    }
}