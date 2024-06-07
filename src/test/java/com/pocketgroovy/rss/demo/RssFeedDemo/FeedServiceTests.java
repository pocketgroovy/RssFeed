package com.pocketgroovy.rss.demo.RssFeedDemo;

import com.pocketgroovy.rss.demo.RssFeedDemo.service.FeedService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FeedServiceTests {
    @Autowired
    private FeedService feedService;
    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        Boolean isHealthy = feedService.isHealthy();

        Assert.assertTrue(isHealthy);
    }
}
