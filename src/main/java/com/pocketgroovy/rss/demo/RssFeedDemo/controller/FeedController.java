package com.pocketgroovy.rss.demo.RssFeedDemo.controller;

import com.pocketgroovy.rss.demo.RssFeedDemo.constant.Publishers;
import com.pocketgroovy.rss.demo.RssFeedDemo.dto.FeedDTO;
import com.pocketgroovy.rss.demo.RssFeedDemo.dto.FeedMessageDTO;
import com.pocketgroovy.rss.demo.RssFeedDemo.exception.FeedNotFoundException;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import com.pocketgroovy.rss.demo.RssFeedDemo.read.RSSFeedParser;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedMessageService;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rss")
@Slf4j
public class FeedController {

    @Autowired
    private FeedService feedService;
    @Autowired
    private FeedMessageService feedMessageService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final Publishers publishers;
    private final Map<String, String> publisherUrls;

    @Autowired
    public FeedController(Publishers publishers) {
        this.publishers = publishers;
        publisherUrls = publishers.getUrl();
    }

//    @GetMapping("/add_feeds")
//    public void addFeeds() {
//        publisherUrls.forEach((pubId, pubUrl) -> {
//            RSSFeedParser parser = new RSSFeedParser(pubUrl);
//            Feed feed = parser.readFeed(pubId);
//            feedService.addFeed(feed);
//            feedMessageService.addAllFeedMessages(feed.getMessages());
//        });
//    }
    @GetMapping("/add_feeds")
    public void addFeeds() {
        publisherUrls.forEach((pubId, pubUrl) -> {
            RSSFeedParser parser = new RSSFeedParser(pubUrl);
            Feed feed = parser.readFeed(pubId);
            List<FeedMessage> onlyNewMessages = listUpOnlyNewMessages(feed.getMessages(), pubId);
            if(!onlyNewMessages.isEmpty()) {
                feedService.addFeed(feed);
                feedMessageService.addAllFeedMessages(onlyNewMessages);
            }
            else{
                log.info("no new message found");
            }
        });
    }

    @GetMapping("/get_feed/{id}")
    public FeedDTO getFeed(@PathVariable long id) {
        return convertFeedToDTO(feedService.getFeed(id));
    }

    @GetMapping("/get_all_feeds")
    public List<FeedDTO> getAllFeeds(@RequestParam(defaultValue = "0") int pageNo,
                                     @RequestParam(defaultValue = "5") int pageSize,
                                     @RequestParam(defaultValue = "DESC") String sortDirection,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return feedService.getAllFeeds(pageNo, pageSize, sortDirection, sortBy).stream().map(this::convertAllFeedsToDTO).collect(Collectors.toList());
    }

    @GetMapping("/get_message/{messageId}")
    public FeedMessageDTO getFeedMessage(@PathVariable long messageId) {
        return convertFeedMessageToDTO(feedMessageService.getFeedMessage(messageId));
    }

    @GetMapping("/get_all_messages_by_feedId/{feedId}")
    public List<FeedMessageDTO> getFeedMessages(@PathVariable long feedId, @RequestParam(defaultValue = "0") int pageNo,
                                                @RequestParam(defaultValue = "5") int pageSize,
                                                @RequestParam(defaultValue = "DESC") String sortDirection,
                                                @RequestParam(defaultValue = "id") String sortBy) {
        return feedMessageService.getFeedMessagesListByFeedId(feedId, pageNo, pageSize, sortDirection, sortBy).stream().map(this::convertAllFeedsMessagesToDTO).collect(Collectors.toList());
    }

    @GetMapping("/get_most_recent_feed_by_pubId/{pubId}")
    public FeedDTO getMostRecentFeedByPubId(@PathVariable String pubId, @RequestParam(defaultValue = "0") int pageNo,
                                            @RequestParam(defaultValue = "1") int pageSize,
                                            @RequestParam(defaultValue = "DESC") String sortDirection,
                                            @RequestParam(defaultValue = "id") String sortBy) throws FeedNotFoundException {
        List<FeedDTO> feedDTOList = feedService.getAllFeedsByPubId(pubId, pageNo, pageSize, sortDirection, sortBy).stream().map(this::convertAllFeedsToDTO).toList();
        if (feedDTOList.size() > 1) {
            log.warn("More results for most recent feed");
        } else if (feedDTOList.isEmpty()) {
            log.warn("No feed found");
            throw new FeedNotFoundException("No feed found");
        }
        return feedDTOList.getFirst();
    }

    // currently no use, don't tell me YAGNI
    @GetMapping("/get_all_feeds_by_pubId/{pubId}")
    public List<FeedDTO> getAllFeedsByPubId(@PathVariable String pubId, @RequestParam(defaultValue = "0") int pageNo,
                                            @RequestParam(defaultValue = "5") int pageSize,
                                            @RequestParam(defaultValue = "DESC") String sortDirection,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return feedService.getAllFeedsByPubId(pubId, pageNo, pageSize, sortDirection, sortBy).stream().map(this::convertAllFeedsToDTO).collect(Collectors.toList());
    }

    private List<FeedMessage> listUpOnlyNewMessages(List<FeedMessage> messageList, String pibId){
            List<FeedMessage> feedMessages = feedMessageService.getFeedMessagesListByFeedId(Long.parseLong(pibId), 0, 200,"DESC", "id");
            Set<FeedMessage> allNewElements = new HashSet<>();
            if(!feedMessages.isEmpty()) {
                for (FeedMessage message : messageList) {
                    boolean bool = feedMessages.contains(message);
                    if (!feedMessages.contains(message)) {
                        allNewElements.add(message);
                    }
                }
                return new ArrayList<>(allNewElements);
            }
            return messageList;
    }

    private FeedDTO convertFeedToDTO(Optional<Feed> feed) {
        return modelMapper.map(feed, FeedDTO.class);
    }

    private FeedMessageDTO convertFeedMessageToDTO(Optional<FeedMessage> feedMessage) {
        return modelMapper.map(feedMessage, FeedMessageDTO.class);
    }

    private FeedMessageDTO convertAllFeedsMessagesToDTO(FeedMessage feedMessage) {
        return modelMapper.map(feedMessage, FeedMessageDTO.class);
    }

    private FeedDTO convertAllFeedsToDTO(Feed feed) {
        return modelMapper.map(feed, FeedDTO.class);
    }
}
