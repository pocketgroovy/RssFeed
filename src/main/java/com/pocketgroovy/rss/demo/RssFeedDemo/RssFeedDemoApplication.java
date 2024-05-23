package com.pocketgroovy.rss.demo.RssFeedDemo;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import com.pocketgroovy.rss.demo.RssFeedDemo.read.RSSFeedParser;
import com.pocketgroovy.rss.demo.RssFeedDemo.write.RSSFeedWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RssFeedDemoApplication {

	public static void main(String[] args)  {
		SpringApplication.run(RssFeedDemoApplication.class, args);
		RSSFeedParser parser = new RSSFeedParser(
				"https://rss.nytimes.com/services/xml/rss/nyt/World.xml");
		Feed feed = parser.readFeed();
        System.out.println(feed);
		for(FeedMessage message : feed.getMessages()) {
			System.out.println(message);
		}
		RSSFeedWriter writer = new RSSFeedWriter(feed, "articles.xml");
		try {
			writer.write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
