package com.pocketgroovy.rss.demo.RssFeedDemo.repository;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//import org.springframework.stereotype.Repository;

//@Repository
public interface IFeedRepository  extends JpaRepository<Feed, Long> {
//    @Query("select f from FeedMessage f cross join Feed m where m.id = ?1")
//    List<FeedMessage> findAllFeedMessageFeedId(long feedId);
}
