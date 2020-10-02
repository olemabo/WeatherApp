package no.visma.v3;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

// mulig å snakke med databasen
public interface WeatherRepository extends CrudRepository<Weather, Long> {
    Weather findByTime(LocalDateTime time); // lager en setning av dette. Find By Time (en parameter i query)
}
