package at.jku.se.prse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
public class Fahrt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Column(length = 15)
    @Getter
    @Setter
    private String carPlate;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private LocalTime depTime;

    @Getter
    @Setter
    private LocalTime arrTime;

    @Getter
    @Setter
    private Integer riddenKM;

    @Getter
    @Setter
    private LocalTime timeStood = LocalTime.MIN;
}