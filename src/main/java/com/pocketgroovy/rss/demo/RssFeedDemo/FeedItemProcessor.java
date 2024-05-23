package com.pocketgroovy.rss.demo.RssFeedDemo;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;


public class FeedItemProcessor implements ItemProcessor<Feed, Feed> {
    private static final Logger log = LoggerFactory.getLogger(FeedItemProcessor.class);

    @Override
    public Feed process(@NonNull Feed feed) throws Exception {
        log.info("Feed through process: {}", String.valueOf(feed));
        return feed;
    }
}
