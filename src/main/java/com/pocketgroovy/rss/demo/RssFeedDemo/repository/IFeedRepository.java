package com.pocketgroovy.rss.demo.RssFeedDemo.repository;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedRepository  extends JpaRepository<Feed, Long> {
//@Query("select f from Feed f where f.pubId = ")
Page<Feed> findAllFeedsByPubId(String pubId, Pageable pageable);
}
