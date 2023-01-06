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
@Table(name = "client")
public class Client extends AbstractEntity {
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
    private String photo;

    @Column
    private String mail;

    @Column
    private String numTel;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;

    @Column
    private Integer idEntreprise;
}
