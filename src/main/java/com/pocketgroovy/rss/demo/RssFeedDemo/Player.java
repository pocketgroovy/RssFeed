//package com.pocketgroovy.rss.demo.RssFeedDemo;
//
//import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
//import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
//import com.pocketgroovy.rss.demo.RssFeedDemo.read.RSSFeedParser;
//import com.pocketgroovy.rss.demo.RssFeedDemo.repository.IFeedMessageRepository;
//import com.pocketgroovy.rss.demo.RssFeedDemo.repository.IFeedRepository;
//import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedMessageService;
//import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedService;
//import com.pocketgroovy.rss.demo.RssFeedDemo.write.RSSFeedWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//
//@Component
//public class Player {
////    @Autowired
////    private FeedService feedService;
////
////    @Autowired
////    private FeedMessageService feedMessageService;
//    @Autowired
//    IFeedRepository iFeedRepository;
//
//    @Autowired
//    IFeedMessageRepository iFeedMessageRepository;
//
//    public void start() {
//        RSSFeedParser parser = new RSSFeedParser(
//                "https://rss.nytimes.com/services/xml/rss/nyt/World.xml");
//        Feed feed = parser.readFeed();
//        System.out.println(feed);
//        FeedService feedService = new FeedService(iFeedRepository);
//        feedService.addFeed(feed);
//        FeedMessageService feedMessageService = new FeedMessageService(iFeedMessageRepository);
//
//        for (
//                FeedMessage message : feed.getMessages()) {
//            System.out.println(message);
//            feedMessageService.addFeedMessage(message);
//        }
//        RSSFeedWriter writer = new RSSFeedWriter(feed, "articles.xml");
//        try {
//            writer.write();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
