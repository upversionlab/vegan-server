package com.upversionlab.model;

import java.util.Date;

/**
 * Created by rborcat on 12/6/2016.
 */
public class Pong {

    private final Date timestamp;

    public Pong(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
