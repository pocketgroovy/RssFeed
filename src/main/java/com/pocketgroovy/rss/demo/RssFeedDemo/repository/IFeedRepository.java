package com.pocketgroovy.rss.demo.RssFeedDemo.repository;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedRepository  extends JpaRepository<Feed, Long> {
}
