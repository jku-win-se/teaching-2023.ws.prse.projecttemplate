package at.jku.se.prse.model;

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
    private LocalTime timeStood = LocalTime.MIN;

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
}