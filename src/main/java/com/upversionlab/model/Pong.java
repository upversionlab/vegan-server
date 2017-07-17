package com.upversionlab.model;

import java.util.Date;

public class Pong {

    private final Date timestamp;

    public Pong(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
