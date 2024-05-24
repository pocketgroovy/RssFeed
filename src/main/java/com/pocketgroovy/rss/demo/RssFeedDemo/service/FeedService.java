package com.pocketgroovy.rss.demo.RssFeedDemo.service;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import com.pocketgroovy.rss.demo.RssFeedDemo.repository.IFeedMessageRepository;
import com.pocketgroovy.rss.demo.RssFeedDemo.repository.IFeedRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
//    public List<FeedMessage> getFeedMessagesByFeedId(long feedId) {
//        return feedRepository.findAllFeedMessageFeedId(feedId);
//    }

//    @Transactional
//    public Optional<Feed> addFeedMessagesToFeed(long feedId, List<Long> feedMessageIds) {
//        return feedRepository.findById(feedId)
//                .map(feed -> {
//                    feed.setFeedMessages(feedMessageRepository.findAllById(feedMessageIds));
//                    return feedRepository.save(feed);
//                });
//    }
}
