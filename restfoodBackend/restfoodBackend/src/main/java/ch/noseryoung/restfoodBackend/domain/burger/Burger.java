package ch.noseryoung.restfoodBackend.domain.burger;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter

@Table(name = "burger")
public class Burger{





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "burger_id")
    private Integer burgerId;
    private Integer relevance;



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
    private Integer preis;

    private boolean vegetarian;
    private String img_url;

}
