package at.jku.se.prse.model;

import at.jku.se.prse.enums.Status;
import at.jku.se.prse.enums.Wiederholung;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Integer mileage;    //Tachostand

    @Getter
    @Setter
    private LocalTime timeStood = LocalTime.MIN;

    //Issue #23
    @Getter
    @Setter
    private Double averageSpeed;

    //Issue #6
    @Getter
    @Setter
    private Wiederholung repetition;

    //Issue #6
    @Getter
    @Setter
    private Integer numberOfRepetitions;

    @Getter
    @Setter
    @ManyToMany(targetEntity = Kategorie.class, fetch = FetchType.EAGER)
    private Set<Kategorie> categories = new HashSet<>();

    public String getCategoriesAsString() {
        return categories.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(id, ((Fahrt) o).getId());
    }

    @Override
    public int hashCode() {
        return id;
    }

    //#7
    public Status getStatus() {
        if(this.date == null || this.depTime == null || this.arrTime == null) return Status.NICHT_DEFINIERT;
        int dateComp = date.compareTo(LocalDate.now());
        if(dateComp > 0 || dateComp == 0 && depTime.compareTo(LocalTime.now()) > 0) return Status.ZUKUENFTIG;                                //zukuenftig falls Datum hoeher als heutiges Datum, oder heutiges Datum und Abfahrtszeit liegt in der Zukunft
        if(dateComp == 0 && depTime.compareTo(LocalTime.now()) <= 0 && arrTime.compareTo(LocalTime.now()) > 0) return Status.AUF_FAHRT;   //Auf Fahrt falls heutiges Datum, Abfahrtszeit kleiner gleich jetziger Zeit und Ankunftszeit liegt in der Zukunft
        if(dateComp < 0 || dateComp == 0 && arrTime.compareTo(LocalTime.now()) < 0) return Status.ABSOLVIERT;                             //absolviert falls Datum in der Vergangenheit, oder heutiges Datum und Ankunftszeit liegt in der Vergangenheit

        return Status.NICHT_DEFINIERT;
    }
}