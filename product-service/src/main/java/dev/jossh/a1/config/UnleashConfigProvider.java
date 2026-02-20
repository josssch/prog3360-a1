package dev.jossh.a1.config;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnleashConfigProvider {

    @Bean
    public Unleash unleash() {
        UnleashConfig config = UnleashConfig.builder()
                .appName("product-service")
                .instanceId("local")
                .unleashAPI(System.getenv().getOrDefault("UNLEASH_API_URL", "http://localhost:4242/api/"))
                .apiKey(System.getenv().getOrDefault("UNLEASH_API_TOKEN", "default-token"))
                .build();

        return new DefaultUnleash(config);
    }
}