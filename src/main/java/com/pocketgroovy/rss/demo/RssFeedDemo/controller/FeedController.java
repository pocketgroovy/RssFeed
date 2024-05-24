package com.pocketgroovy.rss.demo.RssFeedDemo.controller;

import com.pocketgroovy.rss.demo.RssFeedDemo.exception.FeedNotFoundException;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import com.pocketgroovy.rss.demo.RssFeedDemo.read.RSSFeedParser;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedMessageService;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/feed")
public class FeedController {

    private final FeedService feedService;
    private final FeedMessageService feedMessageService;

    public FeedController(FeedService feedService, FeedMessageService feedMessageService){
        this.feedService = feedService;
        this.feedMessageService = feedMessageService;
    }

    @GetMapping("/add_feeds")
    public void addFeed(){
        RSSFeedParser parser = new RSSFeedParser(
                "https://rss.nytimes.com/services/xml/rss/nyt/World.xml");
        Feed feed = parser.readFeed();
        feedService.addFeed(feed);
        feedMessageService.addAllFeedMessages(feed.getMessages());
    }

    @GetMapping("/get_feed/{id}")
    public Feed getFeed(@PathVariable long id){
        return feedService.getFeed(id).orElseThrow(() -> new FeedNotFoundException("the feed was not found"));
    }

    @GetMapping("/get_message/{messageId}")
    public Optional<FeedMessage> getFeedMessage(@PathVariable long messageId) {
        return feedMessageService.getFeedMessage(messageId);
    }

    @GetMapping("/get_all_messages_by_id/{feedId}")
    public List<FeedMessage> getFeedMessages(@PathVariable long feedId) {
        return feedMessageService.getFeedMessagesByFeedId(feedId);
    }

}
