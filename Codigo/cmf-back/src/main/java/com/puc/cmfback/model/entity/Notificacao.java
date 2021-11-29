package com.puc.cmfback.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "notificacao")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notificacao implements Serializable {

    @Id
    @GeneratedValue(generator = "notificacao_generator")
    @SequenceGenerator(name = "notificacao_generator", sequenceName = "notificacao_sequence")
    private Integer idNotificacao;

    @Column(name = "data_lembrete")
    private LocalDate dataLembrete;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_conta")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Conta conta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;
}
