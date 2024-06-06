package com.pocketgroovy.rss.demo.RssFeedDemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedDTO {
    private Long id;

    private String pubId;

    private String title;

    private String link;

    private String description;

    private String language;

    private String copyright;

    private String pubDate;
}