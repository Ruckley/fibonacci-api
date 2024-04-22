package org.arg.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration to get config properties from the yaml properties file
 */
@Configuration
@ConfigurationProperties(prefix = "api.general")
public class Config {
    private Integer timeoutMillies;

    public Integer getTimeoutMillies() {
        return timeoutMillies;
    }

    public void setTimeoutMillies(Integer timeoutMillies) {
        this.timeoutMillies = timeoutMillies;
    }
}