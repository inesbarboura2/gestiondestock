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
@Table(name="ventes")
public class Ventes extends AbstractEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   @Column(name = "id", nullable = false)
   private Long id;


   @Column
   private String code;

   @Column
   private Instant dateVente;

   @Column
   private String commantaire;

   @Column
   private Integer idEntreprise;

   @OneToMany(mappedBy = "ventes")
   private List<LigneVente> ligneVentes;


}
