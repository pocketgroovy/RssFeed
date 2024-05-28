package com.pocketgroovy.rss.demo.RssFeedDemo.controller;

import com.pocketgroovy.rss.demo.RssFeedDemo.dto.FeedDTO;
import com.pocketgroovy.rss.demo.RssFeedDemo.dto.FeedMessageDTO;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import com.pocketgroovy.rss.demo.RssFeedDemo.read.RSSFeedParser;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedMessageService;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rss")
public class FeedController {

    @Autowired
    private FeedService feedService;
    @Autowired
    private FeedMessageService feedMessageService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/add_feeds")
    public void addFeed() {
        RSSFeedParser parser = new RSSFeedParser(
                "https://rss.nytimes.com/services/xml/rss/nyt/World.xml");
        Feed feed = parser.readFeed();
        feedService.addFeed(feed);
        feedMessageService.addAllFeedMessages(feed.getMessages());
    }

    @GetMapping("/get_feed/{id}")
    public FeedDTO getFeed(@PathVariable long id) {
        return convertFeedToDTO(feedService.getFeed(id));
    }

    @GetMapping("/get_message/{messageId}")
    public FeedMessageDTO getFeedMessage(@PathVariable long messageId) {
        return convertFeedMessageToDTO(feedMessageService.getFeedMessage(messageId));
    }

    @GetMapping("/get_all_messages_by_id/{feedId}")
    public List<FeedMessage> getFeedMessages(@PathVariable long feedId, @RequestParam(defaultValue = "0") int pageNo,
                                             @RequestParam(defaultValue = "5") int pageSize,
                                             @RequestParam(defaultValue = "DESC") String sortDirection,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        return feedMessageService.getFeedMessagesListByFeedId(feedId, pageNo, pageSize, sortDirection, sortBy);
    }

    private FeedDTO convertFeedToDTO(Optional<Feed> feed) {
        return modelMapper.map(feed, FeedDTO.class);
    }

    private FeedMessageDTO convertFeedMessageToDTO(Optional<FeedMessage> feedMessage) {
        return modelMapper.map(feedMessage, FeedMessageDTO.class);
    }
}
