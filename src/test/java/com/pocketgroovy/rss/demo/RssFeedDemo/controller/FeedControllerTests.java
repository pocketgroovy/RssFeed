package com.pocketgroovy.rss.demo.RssFeedDemo.controller;

import com.pocketgroovy.rss.demo.RssFeedDemo.dto.FeedDTO;
import com.pocketgroovy.rss.demo.RssFeedDemo.dto.FeedMessageDTO;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedMessageService;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FeedControllerTests {
    private static final Logger log = LoggerFactory.getLogger(FeedControllerTests.class);
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private FeedService feedService;
    @Autowired
    private FeedMessageService feedMessageService;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    void contextLoadsFeedController() {
        assertThat(modelMapper).isNotNull();
        assertTrue(feedService.isHealthy());
        assertTrue(feedMessageService.isHealthy());
    }

    @BeforeEach
    public void requestAddFeedsThenStatus200() throws Exception {
        final ResponseEntity<String> response = this.testRestTemplate.getForEntity(String.format("http://localhost:%d/api/v1/rss/add_feeds", port),
                String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void requestGetFeedByFeedIdThenReceiveFeed() throws Exception {
        int feedId = 1;
        final FeedDTO response = this.testRestTemplate.getForObject(String.format("http://localhost:%d/api/v1/rss/get_feed/%d", port, feedId),
                FeedDTO.class);
        assertNotNull(response);
        assertEquals(feedId, response.getId());
    }

    @Test
    void requestGetAllFeeds() throws Exception {
        final ResponseEntity<FeedDTO[]> response = this.testRestTemplate.getForEntity(String.format("http://localhost:%d/api/v1/rss/get_all_feeds", port),
                FeedDTO[].class);
        assertEquals(5, Objects.requireNonNull(response.getBody()).length);
    }

    @Test
    void requestGetFeedMessageByMessageIdThenReceiveFeedMessage() throws Exception {
        int messageId = 1;
        final FeedMessageDTO response = this.testRestTemplate.getForObject(String.format("http://localhost:%d/api/v1/rss/get_message/%d", port, messageId),
                FeedMessageDTO.class);
        assertNotNull(response);
        assertEquals(messageId, response.getId());
    }

    @Test
    void requestGetAllMessageByFeedIdThenReceiveAllFiveMessages() throws Exception {
        int feedId = 1;
        final ResponseEntity<FeedMessageDTO[]> response = this.testRestTemplate.getForEntity(String.format("http://localhost:%d/api/v1/rss/get_all_messages_by_feedId/%d", port, feedId),
                FeedMessageDTO[].class);

        assertEquals(5, Objects.requireNonNull(response.getBody()).length);
    }

    @Test
    void requestGetMostRecentFeedByPubIdThenReceiveFeed() throws Exception {
        String pubId = "1";
        final FeedDTO response = this.testRestTemplate.getForObject(String.format("http://localhost:%d/api/v1/rss/get_most_recent_feed_by_pubId/%s", port, pubId),
                FeedDTO.class);
        assertNotNull(response);
        assertEquals(pubId, response.getPubId());
    }

    @Test
    void requestGetAllFeedsByPubIdThenReceiveAllFiveFeeds() throws Exception {
        String pubId = "2";
        final ResponseEntity<FeedDTO[]> response = this.testRestTemplate.getForEntity(String.format("http://localhost:%d/api/v1/rss/get_all_feeds_by_pubId/%s", port, pubId),
                FeedDTO[].class);
        assertEquals(5, Objects.requireNonNull(response.getBody()).length);
        for( FeedDTO feed : Objects.requireNonNull(response.getBody())) {
            assertEquals(pubId, feed.getPubId());
        }
    }
}
