package com.pocketgroovy.rss.demo.RssFeedDemo;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import com.pocketgroovy.rss.demo.RssFeedDemo.read.RSSFeedParser;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedMessageService;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedService;
import com.pocketgroovy.rss.demo.RssFeedDemo.write.RSSFeedWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class RssFeedDemoApplication {
//	@Autowired
//	private static FeedService feedService;
//
//	@Autowired
//	private static FeedMessageService feedMessageService;


	public static void main(String[] args)  {
		SpringApplication.run(RssFeedDemoApplication.class, args);
//		Player player = new Player();
//		player.start();
//		RSSFeedParser parser = new RSSFeedParser(
//				"https://rss.nytimes.com/services/xml/rss/nyt/World.xml");
//		Feed feed = parser.readFeed();
//		feedService.addFeed(feed);
//
//		for(FeedMessage message : feed.getMessages()) {
//			System.out.println(message);
//			feedMessageService.addFeedMessage(message);
//		}
//		RSSFeedWriter writer = new RSSFeedWriter(feed, "articles.xml");
//		try {
//			writer.write();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
