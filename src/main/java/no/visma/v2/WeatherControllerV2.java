package no.visma.v2;

import no.visma.domain.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// snakke med app fra pcen
@RestController
@RequestMapping("v2") // siden vi har v1 og v2, så må vi skille de, endre litt på path på en måte
public class WeatherControllerV2 {
    @GetMapping("now")
    public String get() {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.getProperties();
        return null;
    }
}
