package com.windsoft.starter.config;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rest-client")
public record RestClientProperties(
        int maxConnections,
        int maxConnectionsPerRoute,
        Duration connectionRequestTimeout,
        Duration connectTimeout,
        Duration readTimeout,
        Duration connectionTtl,
        Duration idleTimeout,
        Duration validateAfterInactivity) {

    public RestClientProperties() {
        this(100, 50, Duration.ofSeconds(5), Duration.ofSeconds(3),
                Duration.ofSeconds(10), Duration.ofMinutes(5), Duration.ofSeconds(20),
                Duration.ofSeconds(5));
    }
}
