package no.visma.v2;

import lombok.RequiredArgsConstructor;
import no.visma.domain.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Locale;

import static java.util.Comparator.comparing;

// snakke med app fra pcen
@RestController
@RequestMapping("v2") // siden vi har v1 og v2, så må vi skille de, endre litt på path på en måte
@RequiredArgsConstructor
public class WeatherControllerV2 {
    private final WeatherFetcher weatherFetcher;

    @GetMapping("now")
    public String get() {
        WeatherResponse weatherResponse = weatherFetcher.getCurrent();
        WeatherResponse.Properties.TimeSerie timeSerie = weatherResponse.getProperties().getTimeseries().stream().filter(t -> t.getTime().isAfter(LocalDateTime.now()))
                .min(comparing(t -> t.getTime()))
                .orElseThrow(RuntimeException::new);

        String format = formatResponse(timeSerie);
        return format;
        //return timeSerie.toString(); // stream er litt som liste, filter ut data som ikke passerer alpha
    }
    // Alt enter, lage ny metoder
    private String formatResponse(WeatherResponse.Properties.TimeSerie timeSerie) {
        WeatherResponse.Properties.TimeSerie.WeatherData.Instant.Details details = timeSerie.getData().getInstant().getDetails();
        return String.format("temperatur: %s C, vindfart: %s m/s, skyarealbrøk %s",
                details.getAirTemperature(),
                details.getWindSpeed(),
                details.getCloudAreaFraction());

    }
}
