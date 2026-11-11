package com.windsoft.starter.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RestClientProperties.class)
public class RestClientConfig {

    @Bean(destroyMethod = "close")
    public CloseableHttpClient httpClient(RestClientProperties properties) {
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setConnectTimeout(Timeout.ofMilliseconds(properties.connectTimeout().toMillis()))
                .setSocketTimeout(Timeout.ofMilliseconds(properties.readTimeout().toMillis()))
                .setTimeToLive(TimeValue.ofMilliseconds(properties.connectionTtl().toMillis()))
                .setValidateAfterInactivity(TimeValue.ofMilliseconds(properties.validateAfterInactivity().toMillis()))
                .build();

        PoolingHttpClientConnectionManager connectionManager =
                PoolingHttpClientConnectionManagerBuilder.create()
                        .setDefaultConnectionConfig(connectionConfig)
                        .setMaxConnTotal(properties.maxConnections())
                        .setMaxConnPerRoute(properties.maxConnectionsPerRoute())
                        .build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(properties.connectionRequestTimeout().toMillis()))
                .setResponseTimeout(Timeout.ofMilliseconds(properties.readTimeout().toMillis()))
                .build();

        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .evictExpiredConnections()
                .evictIdleConnections(TimeValue.ofMilliseconds(properties.idleTimeout().toMillis()))
                .build();
    }

    @Bean
    public RestClient restClient(CloseableHttpClient httpClient) {
        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory(httpClient))
                .build();
    }
}
