SET foreign_key_checks = 0;

DROP TABLE IF EXISTS FeedMessages;

CREATE TABLE IF NOT EXISTS FeedMessages
(
    id   SERIAL PRIMARY KEY,
    title VARCHAR(20),
    description VARCHAR(20),
    link VARCHAR(100),
    author VARCHAR(20),
    guid VARCHAR(20)
);

DROP TABLE IF EXISTS Feeds;

CREATE TABLE Feeds
(
    id   SERIAL PRIMARY KEY,
    title VARCHAR(20),
    link VARCHAR(100),
    description VARCHAR(20),
    copyright VARCHAR(100),
    pubDate TIMESTAMP,
    language VARCHAR(20)
--    CONSTRAINT messageId FOREIGN KEY (id) REFERENCES feedMessages(id)
);

SET foreign_key_checks = 1;