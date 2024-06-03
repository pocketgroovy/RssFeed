package com.pocketgroovy.rss.demo.RssFeedDemo.service;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.repository.IFeedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Feed> getAllFeeds(int pageNo, int pageSize, String sortDir, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortBy);
        return feedRepository.findAll(pageable).getContent();
    }

    public List<Feed> getAllFeedsByPubId(String pubId, int page, int size, String sortDir, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        Page<Feed> feed = feedRepository.findAllFeedsByPubId(pubId, pageable);
        return feed.getContent();
    }

}