package com.upversionlab.controller;

import com.upversionlab.model.Pong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by rborcat on 12/6/2016.
 */
@RestController
public class PingController {

    @RequestMapping("/ping")
    public Pong ping() {
        return new Pong(new Date());
    }
}