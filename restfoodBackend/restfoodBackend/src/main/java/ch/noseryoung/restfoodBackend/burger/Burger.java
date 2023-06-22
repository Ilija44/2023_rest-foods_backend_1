package ch.noseryoung.restfoodBackend.burger;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor

@Table(name = "burger")
public class Burger{

    @Id
    @Column(name = "burger_id")
    private Integer burgerId;


    @Column(name = "name")
    @NotBlank(message = "Name darf nicht leer sein.")
    private String name;

    @Column(name = "beschreibung")
    @NotBlank(message = "Beschreibung darf nicht leer sein.")
    private String beschreibung;


    @Column(name = "zutaten")
    @NotBlank(message = "Zutaten darf nicht leer sein.")
    private String street;

    @Column(name = "preis")
    @NotEmpty(message = "Preis darf nicht leer sein.")
    private String preis;
}
