package com.pocketgroovy.rss.demo.RssFeedDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/*
 * RSS message model
 */
@Getter
@Setter
@Entity
@Table(name = "feedmessage")
public class FeedMessage extends BaseEntity{

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

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedMessage that = (FeedMessage) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getLink(), that.getLink()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getGuid(), that.getGuid()) && Objects.equals(getId(), that.getId()) && Objects.equals(feed, that.feed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getLink(), getAuthor(), getGuid(), getId(), feed);
    }

    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", link=" + link + ", author=" + author + ", guid=" + guid
                + "]";
    }

}