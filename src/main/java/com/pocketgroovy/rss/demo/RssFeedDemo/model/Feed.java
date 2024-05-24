package com.pocketgroovy.rss.demo.RssFeedDemo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Stores an RSS feed
 */
@Entity
@Table(name = "feed")
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    @Column(name = "language")
    private String language;

    @Column(name = "copyright")
    private String copyright;

    @Column(name = "pubDate")
    private String pubDate;

    @OneToMany(mappedBy = "feed")
    private List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public Feed() {
        this.title = "";
        this.link = "";
        this.description = "";
        this.language = "";
        this.copyright = "";
        this.pubDate = "";
    }


    public Feed(String title, String link, String description, String language,
                String copyright, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
    }

    public List<FeedMessage> getMessages() {
        return entries;
    }

    public void setMessages(FeedMessage feedMessage){
        if( entries == null){
            entries = new ArrayList<FeedMessage>();
        }
        feedMessage.setFeed(this);
        entries.add(feedMessage);
    }
//    public void setFeedMessages(List<FeedMessage> entries) {
//        this.entries = entries;
//    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getPubDate() {
        return pubDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feed feed = (Feed) o;
        return Objects.equals(id, feed.id) && Objects.equals(getTitle(), feed.getTitle()) && Objects.equals(getLink(), feed.getLink()) && Objects.equals(getDescription(), feed.getDescription()) && Objects.equals(getLanguage(), feed.getLanguage()) && Objects.equals(getCopyright(), feed.getCopyright()) && Objects.equals(getPubDate(), feed.getPubDate()) && Objects.equals(getMessages(), feed.getMessages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getTitle(), getLink(), getDescription(), getLanguage(), getCopyright(), getPubDate(), getMessages());
    }

    @Override
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }

}