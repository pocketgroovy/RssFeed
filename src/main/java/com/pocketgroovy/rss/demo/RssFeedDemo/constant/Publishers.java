package com.pocketgroovy.rss.demo.RssFeedDemo.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "publishers")
public class Publishers {
    private Map<String, String> url;
}