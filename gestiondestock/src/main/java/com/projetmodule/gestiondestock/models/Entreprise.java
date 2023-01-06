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
@Table(name = "entreprise")
public class Entreprise extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private  String name;

    @Column
    private Adresse adresse;

    @Column
    private String codeFiscal;

    @Column
    private String photo;

    @Column
    private String email;

    @Column
    private String numTel;

    @Column
    private String steWeb;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;


}
