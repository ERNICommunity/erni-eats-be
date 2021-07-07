package erni.betterask.eats.be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.time.Clock;

@Configuration
@EnableWebFlux
@ComponentScan("erni.betterask.eats.be")
public class AppConfig {

    @Bean
    public Clock getClock() {
        return Clock.systemDefaultZone();
    }

}
