package no.visma.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// snakke med app fra pcen
@RestController
public class WeatherControllerV1 {
    @GetMapping("now")
    public String getNow() {
        return "pent";
    }
}
