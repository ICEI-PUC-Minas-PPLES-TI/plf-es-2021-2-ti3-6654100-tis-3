package com.puc.cmfback.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "usuario")
@Entity
@Getter
@Setter
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(generator = "usuario_generator")
    @SequenceGenerator(name = "usuario_generator", sequenceName = "usuario_sequence")
    private Integer idUsuario;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "nome")
    private String nome;

    @Column(name = "senha")
    private String senha;
}
