package com.pocketgroovy.rss.demo.RssFeedDemo.repository;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IFeedMessageRepository extends JpaRepository<FeedMessage, Long> {
    @Query("select f from FeedMessage f join f.feed m where m.id = ?1")
    Page<FeedMessage> findAllFeedMessageByFeedId(long feedId, Pageable pageable);
}
