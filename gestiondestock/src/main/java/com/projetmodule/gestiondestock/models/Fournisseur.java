package com.projetmodule.gestiondestock.models;


import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fournisseur")
public class Fournisseur extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Embedded
    private Adresse adresse;

    @Column
    private String image;

    @Column
    private String email;

    @Column
    private String numTel;

    @OneToMany(mappedBy ="fournisseur")
    private List<CommandeFournisseur> commandeFournisseurs;

    @Column
    private Integer idEntreprise;

}
