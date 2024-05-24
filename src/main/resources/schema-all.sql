--SET foreign_key_checks = 0;
--
--DROP TABLE IF EXISTS feed;

CREATE TABLE IF NOT EXISTS feed
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    link VARCHAR(255),
    description LONGTEXT,
    copyright VARCHAR(255),
    pubDate VARCHAR(255),
    language VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
--    CONSTRAINT messageId FOREIGN KEY (id) REFERENCES feedMessages(id)
);

--DROP TABLE IF EXISTS feedmessage;
--
CREATE TABLE IF NOT EXISTS feedmessage
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    description LONGTEXT,
    link VARCHAR(255),
    author VARCHAR(255),
    guid VARCHAR(255),
    feed_id BIGINT,
    FOREIGN KEY (feed_id) REFERENCES feed (id),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

--SET foreign_key_checks = 1;