package com.projetmodule.gestiondestock.models;


import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "utilisateur")
public class Utilisateur extends AbstractEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;


  @Column
  private String nom;

  @Column
  private String prenom;

  @Column
  private String email;

  @Column
  private Instant dateDeNaissance;

  @Embedded
  private Adresse adresse;

  @Column
  private String photo;

  @Column
  private String motDePasse;



  @ManyToOne
  @JoinColumn(name = "identreprise")
  private Entreprise entreprise;

  @OneToMany(mappedBy = "utilisateur")
  private List<Role> roles;



}
