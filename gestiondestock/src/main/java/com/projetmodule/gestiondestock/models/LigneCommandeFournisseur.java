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
@Table(name = "lignecommandefournisseur")

public class LigneCommandeFournisseur extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;


    @ManyToOne
    @JoinColumn(name="commandefournisseur")
    private CommandeFournisseur commandeFournisseur;

    @Column
    private Integer idEntreprise;



}
