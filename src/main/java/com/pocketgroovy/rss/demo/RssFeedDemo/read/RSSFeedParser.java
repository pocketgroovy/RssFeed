package com.pocketgroovy.rss.demo.RssFeedDemo.read;

import com.pocketgroovy.rss.demo.RssFeedDemo.model.Feed;
import com.pocketgroovy.rss.demo.RssFeedDemo.model.FeedMessage;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

//public class RSSFeedParser implements ItemReader<Feed> {
public class RSSFeedParser  {
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "creator";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";

    final URL url;

    public RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

//    public InputStream read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        return readUrl();
//    }
    public Feed readFeed() {
        Feed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values initial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = readUrl();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed(title, link, description, language,
                                        copyright, pubdate);
                            }
//                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData( eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData( eventReader);
                            break;
                        case LINK:
                            if (eventReader.peek() instanceof Characters) {
                                link = getCharacterData( eventReader);
                            }
                            break;
                        case GUID:
                            guid = getCharacterData( eventReader);
                            break;
                        case LANGUAGE:
                            language = getCharacterData( eventReader);
                            break;
                        case AUTHOR:
                            author = getCharacterData( eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(eventReader);
                            break;
                        case COPYRIGHT:
                            copyright = getCharacterData( eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (Objects.equals(event.asEndElement().getName().getLocalPart(), ITEM)) {
                        FeedMessage message = new FeedMessage();
                        message.setAuthor(author);
                        message.setDescription(description);
                        message.setGuid(guid);
                        message.setLink(link);
                        message.setTitle(title);
                        assert feed != null;
                        feed.setMessages(message);
//                        event = eventReader.nextEvent();
//                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    private String getCharacterData(XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        XMLEvent event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream readUrl() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}