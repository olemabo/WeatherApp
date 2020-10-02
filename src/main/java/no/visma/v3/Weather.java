package no.visma.v3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static no.visma.v3.Weather.TABLE_NAME;


@Data
@Entity   // denne skal oppdages av spring, kobles automatisk mot database tabell
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME)
public class Weather {
    public static final String TABLE_NAME = "T_WEATHER";
    // hver linje representerer kolonne
    @Id // dette er primærnøkkel som jpa trenger for å orientere seg
    @GeneratedValue(strategy = GenerationType.AUTO) // id must be set to something, this creates id. Hvordan lage id
    private Long id;

    private LocalDateTime time;
    private String temperatur;
    private String vindhastighet;
    private String broek;


}
