package com.pocketgroovy.rss.demo.RssFeedDemo.service;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.repository.IFeedRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedService {

    private final IFeedRepository feedRepository;

    public FeedService(IFeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public boolean isHealthy() {
        return feedRepository.count() >= 0;
    }

    public void addFeed(Feed feed) {
        feedRepository.save(feed);
    }

    public Optional<Feed> getFeed(final long id) {
        return feedRepository.findById(id);
    }

}