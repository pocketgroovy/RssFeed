package com.pocketgroovy.rss.demo.RssFeedDemo.service;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import com.pocketgroovy.rss.demo.RssFeedDemo.repository.IFeedMessageRepository;
import com.pocketgroovy.rss.demo.RssFeedDemo.repository.IFeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedMessageService {
    private final IFeedMessageRepository feedMessageRepository;

    public FeedMessageService(IFeedMessageRepository feedMessageRepository) {
        this.feedMessageRepository = feedMessageRepository;
    }

    public boolean isHealthy() {
        return feedMessageRepository.count() >= 0;
    }

    public FeedMessage addFeedMessage(FeedMessage feedMessage){
        return feedMessageRepository.save(feedMessage);
    }

    public void addAllFeedMessages(List<FeedMessage> feedMessages){
        feedMessageRepository.saveAll(feedMessages);
    }

    public Optional<FeedMessage> getFeedMessage(long id) {
        return feedMessageRepository.findById(id);
    }

    public List<FeedMessage> getFeedMessagesByFeedId(long id) {
        return feedMessageRepository.findAllFeedMessageByFeedId(id);
    }
}
