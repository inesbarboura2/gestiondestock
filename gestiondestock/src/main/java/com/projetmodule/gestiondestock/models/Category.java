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
@Table(name = "category")
public class Category extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String code;

    @Column
    private String designation;



    @OneToMany(mappedBy ="category")
    private List<Article> articles;



    @Column
    private Integer idEntreprise;
}
