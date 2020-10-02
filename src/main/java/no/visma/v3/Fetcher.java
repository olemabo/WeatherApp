package no.visma.v3;

import lombok.RequiredArgsConstructor;
import no.visma.domain.WeatherResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Component // dont need to use config file
@RequiredArgsConstructor // plukker opp alle private final
public class Fetcher {
    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    @PostConstruct
    public void onBoot(){
        WeatherResponse weatherResponse = getCurrent();
        List<Weather> weatherList = mapToWeather(weatherResponse);
        weatherRepository.saveAll(weatherList);
    }


    private WeatherResponse getCurrent() {
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=51.5&lon=0"))
                .header(USER_AGENT, UUID.randomUUID().toString()).build();
        // Ctrl Alt v -> variable nave og type
        ResponseEntity<WeatherResponse> result = restTemplate.exchange(requestEntity, WeatherResponse.class);// kall til andre url-er
        return result.getBody();
    }

    // ctr shift pil opp og ned til å flytte funksjoner
    private List<Weather> mapToWeather(WeatherResponse weatherResponse) {
        return weatherResponse.getProperties().getTimeseries().stream()
                .map(t -> Weather.builder()
                .time(t.getTime())
                .temperatur(t.getData().getInstant().getDetails().getAirTemperature().toString())
                .vindhastighet(t.getData().getInstant().getDetails().getWindSpeed().toString())
                .build()
                ).collect(Collectors.toList());
    }

}
