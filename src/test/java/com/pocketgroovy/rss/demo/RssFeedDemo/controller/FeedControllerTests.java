package com.pocketgroovy.rss.demo.RssFeedDemo.controller;

import com.pocketgroovy.rss.demo.RssFeedDemo.dto.FeedDTO;
import com.pocketgroovy.rss.demo.RssFeedDemo.dto.FeedMessageDTO;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import com.pocketgroovy.rss.demo.RssFeedDemo.repository.IFeedRepository;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedMessageService;
import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FeedControllerTests {
    private static final Logger log = LoggerFactory.getLogger(FeedControllerTests.class);
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private IFeedRepository mockfeedRepository;
    @MockBean
    private FeedService mockfeedService;
    @MockBean
    private FeedMessageService mockfeedMessageService;


    @Test
    void contextLoadsFeedController() {
        assertThat(mockfeedMessageService).isNotNull();
        assertThat(mockfeedService).isNotNull();
        assertThat(mockfeedRepository).isNotNull();
    }

    @Test
    void requestAddFeedsThenStatus200(){
        ResponseEntity<Void> response = testRestTemplate.exchange(
                "/api/v1/rss/add_feeds",
                HttpMethod.GET,
                null,
                Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // test controller and DTOConverter
    @Test
    void requestGetFeedByFeedIdThenReceiveFeed(){
        Feed mockFeed = new Feed();
        long feedId = 1L;
        mockFeed.setId(feedId);
        Mockito.when(mockfeedService.getFeed(feedId)).thenReturn(Optional.of(mockFeed));

        ResponseEntity<FeedDTO> response = this.testRestTemplate.getForEntity(String.format("/api/v1/rss/get_feed/%d",feedId), FeedDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(FeedDTO.class, response.getBody());
        assertEquals(feedId, response.getBody().getId());
    }

    @Test
    void requestGetAllFeeds() {
        List<Feed> feedList = getFeeds();
        int pageNo = 0;
        int pageSize = 5;
        String sortDirection = "DESC";
        String sortBy="id";
        Mockito.when(mockfeedService.getAllFeeds(pageNo, pageSize, sortDirection, sortBy)).thenReturn(feedList);

        ResponseEntity<List<FeedDTO>> response = testRestTemplate.exchange(
                "/api/v1/rss/get_all_feeds",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FeedDTO>>() {}
        );
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<FeedDTO> foundFeeds = response.getBody();
        assertNotNull(foundFeeds);
        assertFalse(foundFeeds.isEmpty());
        assertEquals(1L, foundFeeds.get(0).getId());

        Mockito.verify(mockfeedService, times(1)).getAllFeeds(pageNo, pageSize, sortDirection, sortBy);
    }

    @Test
    void requestGetFeedMessageByMessageIdThenReceiveFeedMessage() {
        FeedMessage mockFeedMessage = new FeedMessage();
        long messageId = 1L;
        mockFeedMessage.setId(messageId);
        Mockito.when(mockfeedMessageService.getFeedMessage(messageId)).thenReturn(Optional.of(mockFeedMessage));

        ResponseEntity<FeedMessageDTO> response = this.testRestTemplate.getForEntity(String.format("/api/v1/rss/get_message/%d", messageId), FeedMessageDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(FeedMessageDTO.class, response.getBody());
        assertEquals(messageId, response.getBody().getId());
    }

    @Test
    void requestGetAllMessageByFeedIdThenReceiveAllFiveMessages() {
        List<FeedMessage> feedMessageList = getFeedMessages();
        long feedId = 1L;
        int pageNo = 0;
        int pageSize = 5;
        String sortDirection = "DESC";
        String sortBy="id";
        Mockito.when(mockfeedMessageService.getFeedMessagesListByFeedId(feedId, pageNo, pageSize, sortDirection, sortBy)).thenReturn(feedMessageList);

        ResponseEntity<List<FeedMessage>> response = testRestTemplate.exchange(
                String.format("/api/v1/rss/get_all_messages_by_feedId/%d", feedId),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FeedMessage>>() {}
        );
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<FeedMessage> foundFeedMessages = response.getBody();
        assertNotNull(foundFeedMessages);
        assertFalse(foundFeedMessages.isEmpty());
        assertEquals(5, foundFeedMessages.size());
        Mockito.verify(mockfeedMessageService, times(1)).getFeedMessagesListByFeedId(feedId, pageNo, pageSize, sortDirection, sortBy);
    }

    @Test
    void requestGetMostRecentFeedByPubIdThenReceiveFeed() {
        String pubId = "1";
        List<Feed> feedList = getFeeds();
        int pageNo = 0;
        int pageSize = 1;
        String sortDirection = "DESC";
        String sortBy="id";
        Mockito.when(mockfeedService.getAllFeedsByPubId(pubId, pageNo, pageSize, sortDirection, sortBy)).thenReturn(feedList);

        ResponseEntity<FeedDTO> response = this.testRestTemplate.getForEntity(String.format("/api/v1/rss/get_most_recent_feed_by_pubId/%s",pubId), FeedDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(FeedDTO.class, response.getBody());
        assertEquals(pubId, response.getBody().getPubId());
    }

    @Test
    void requestGetAllFeedsByPubIdThenReceiveAllFiveFeeds() {
        List<Feed> feedList = getFeeds();
        String pubId = "1";
        int pageNo = 0;
        int pageSize = 5;
        String sortDirection = "DESC";
        String sortBy="id";
        Mockito.when(mockfeedService.getAllFeedsByPubId(pubId, pageNo, pageSize, sortDirection, sortBy)).thenReturn(feedList);

        ResponseEntity<List<Feed>> response = testRestTemplate.exchange(
                String.format("/api/v1/rss/get_all_feeds_by_pubId/%s", pubId),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Feed>>() {}
        );
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Feed> foundFeeds = response.getBody();
        assertNotNull(foundFeeds);
        assertFalse(foundFeeds.isEmpty());
        assertEquals(5, foundFeeds.size());
        Mockito.verify(mockfeedService, times(1)).getAllFeedsByPubId(pubId, pageNo, pageSize, sortDirection, sortBy);
    }

    private static List<Feed> getFeeds(){
        String pubId = "1";
        Feed mockFeed = new Feed();
        mockFeed.setId(1L);
        mockFeed.setPubId(pubId);
        Feed mockFeed2 = new Feed();
        mockFeed2.setId(2L);
        mockFeed2.setPubId(pubId);
        Feed mockFeed3 = new Feed();
        mockFeed3.setId(3L);
        mockFeed3.setPubId(pubId);
        Feed mockFeed4 = new Feed();
        mockFeed4.setId(4L);
        mockFeed4.setPubId(pubId);
        Feed mockFeed5 = new Feed();
        mockFeed5.setId(5L);
        mockFeed5.setPubId(pubId);
        List<Feed> feedList = new ArrayList<>();
        feedList.add(mockFeed);
        feedList.add(mockFeed2);
        feedList.add(mockFeed3);
        feedList.add(mockFeed4);
        feedList.add(mockFeed5);
        return feedList;
    }

    private static List<FeedMessage> getFeedMessages() {
        long feedId = 1L;
        Feed feed = new Feed();
        feed.setId(feedId);
        FeedMessage mockFeedMessage = new FeedMessage();
        mockFeedMessage.setId(1L);
        mockFeedMessage.setFeed(feed);
        FeedMessage mockFeedMessage2 = new FeedMessage();
        mockFeedMessage2.setId(2L);
        mockFeedMessage2.setFeed(feed);
        FeedMessage mockFeedMessage3 = new FeedMessage();
        mockFeedMessage3.setId(3L);
        mockFeedMessage3.setFeed(feed);
        FeedMessage mockFeedMessage4 = new FeedMessage();
        mockFeedMessage4.setId(4L);
        mockFeedMessage4.setFeed(feed);
        FeedMessage mockFeedMessage5 = new FeedMessage();
        mockFeedMessage5.setId(5L);
        mockFeedMessage5.setFeed(feed);
        List<FeedMessage> feedMessageList = new ArrayList<>();
        feedMessageList.add(mockFeedMessage);
        feedMessageList.add(mockFeedMessage2);
        feedMessageList.add(mockFeedMessage3);
        feedMessageList.add(mockFeedMessage4);
        feedMessageList.add(mockFeedMessage5);
        return feedMessageList;
    }
}
