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
@Table(name = "mvtstk")
public class MvtStk extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Instant dateMvt;

    @Column
    private BigDecimal quantite;

    @Column
    private TypeMvtStk typeMvt;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column
    private Integer idEntreprise;


}
