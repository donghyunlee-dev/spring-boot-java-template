package com.windsoft.starter.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

class RestClientConfigTest {

    @Test
    void createsRestClientWithConfiguredConnectionManager() {
        RestClientProperties properties = new RestClientProperties();
        RestClientConfig config = new RestClientConfig();

        RestClient restClient = config.restClient(config.httpClient(properties));

        assertNotNull(restClient);
    }
}
