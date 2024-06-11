package com.pocketgroovy.rss.demo.RssFeedDemo;

import com.pocketgroovy.rss.demo.RssFeedDemo.controller.FeedController;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedMessageService;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class RssFeedDemoApplicationTests {

	@Autowired
	private FeedController controller;

	@Autowired
	private FeedService feedService;

	@Autowired
	private FeedMessageService feedMessageService;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
		assertTrue(feedService.isHealthy());
		assertTrue(feedMessageService.isHealthy());
	}

}
