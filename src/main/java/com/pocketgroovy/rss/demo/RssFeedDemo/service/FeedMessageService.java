package com.pocketgroovy.rss.demo.RssFeedDemo.service;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import com.pocketgroovy.rss.demo.RssFeedDemo.repository.IFeedMessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public FeedMessage addFeedMessage(FeedMessage feedMessage) {
        return feedMessageRepository.save(feedMessage);
    }

    public void addAllFeedMessages(List<FeedMessage> feedMessages) {
        feedMessageRepository.saveAll(feedMessages);
    }

    public Optional<FeedMessage> getFeedMessage(long id) {
        return feedMessageRepository.findById(id);
    }

    public List<FeedMessage> getFeedMessagesListByFeedId(long id, int page, int size, String sortDir, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        Page<FeedMessage> feedMessages = feedMessageRepository.findAllFeedMessageByFeedId(id, pageable);
        return feedMessages.getContent();
    }
}
