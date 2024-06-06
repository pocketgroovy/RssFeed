package com.pocketgroovy.rss.demo.RssFeedDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Stores an RSS feed
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "feed")
public class Feed extends BaseEntity {
    @Column(name = "pubId")
    private String pubId;

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

    @JsonIgnore
    @OneToMany(mappedBy = "feed")
    private List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public Feed(String pubId, String title, String link, String description, String language,
                String copyright, String pubDate) {
        this.pubId = pubId;
        this.copyright = copyright;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feed feed = (Feed) o;
        return Objects.equals(getId(), feed.getId()) && Objects.equals(getPubId(), feed.getPubId()) && Objects.equals(getTitle(), feed.getTitle()) && Objects.equals(getLink(), feed.getLink()) && Objects.equals(getDescription(), feed.getDescription()) && Objects.equals(getLanguage(), feed.getLanguage()) && Objects.equals(getCopyright(), feed.getCopyright()) && Objects.equals(getPubDate(), feed.getPubDate()) && Objects.equals(getMessages(), feed.getMessages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPubId(), getTitle(), getLink(), getDescription(), getLanguage(), getCopyright(), getPubDate(), getMessages());
    }

    @Override
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + ", pubId=" + pubId +"]" ;
    }

}