package com.puc.cmfback.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "categoria")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(generator = "categoria_generator")
    @SequenceGenerator(name = "categoria_generator", sequenceName = "categoria_sequence")
    private Integer idCategoria;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "ordem")
    private String ordem;
}
