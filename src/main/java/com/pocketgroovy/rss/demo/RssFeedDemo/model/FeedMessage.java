package com.pocketgroovy.rss.demo.RssFeedDemo.model;

import jakarta.persistence.*;

import java.util.Objects;

/*
 * RSS message model
 */
@Entity
@Table(name = "feedmessage")
public class FeedMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    @Column(name = "author")
    private String author;

    @Column(name = "guid")
    private String guid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    public void setFeed(Feed feed){
        this.feed = feed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedMessage that = (FeedMessage) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getLink(), that.getLink()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getGuid(), that.getGuid()) && Objects.equals(id, that.id) && Objects.equals(feed, that.feed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getLink(), getAuthor(), getGuid(), id, feed);
    }

    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", link=" + link + ", author=" + author + ", guid=" + guid
                + "]";
    }

}