package com.puc.cmfback.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "produto")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {

    @Id
    @GeneratedValue(generator = "produto_generator")
    @SequenceGenerator(name = "produto_generator", sequenceName = "produto_sequence")
    private Integer idProduto;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "quantidade")
    private Integer quantidade;
}
