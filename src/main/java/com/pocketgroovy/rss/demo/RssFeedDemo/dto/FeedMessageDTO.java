package com.pocketgroovy.rss.demo.RssFeedDemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedMessageDTO {
    private Long id;

    private String pubId;

    private String title;

    private String description;

    private String link;

    private String author;

    private String guid;

    private String pubDate;
}
