package org.srempfer.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.contract.wiremock.WireMockConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import ( WireMockConfiguration.class )
public class ConfigServerMockApplication {

    public static void main(String[] args) {
        SpringApplication.run( ConfigServerMockApplication.class, args);
    }

}
