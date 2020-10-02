package no.visma.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean // hva er en bønne, må lage bønne pga resttemplate er noe vi ikke har lagd
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
