package com.pocketgroovy.rss.demo.RssFeedDemo.exception;

public class FeedNotFoundException extends RuntimeException {
    public FeedNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
