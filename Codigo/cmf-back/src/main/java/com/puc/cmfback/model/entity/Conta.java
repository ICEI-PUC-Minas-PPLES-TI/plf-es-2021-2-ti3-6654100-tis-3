package com.puc.cmfback.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "conta")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Conta implements Serializable {

    @Id
    @GeneratedValue(generator = "conta_generator")
    @SequenceGenerator(name = "conta_generator", sequenceName = "conta_sequence")
    private Integer idConta;

    @Column(name = "tipo_conta")
    private String tipoConta;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "valor_conta")
    private BigDecimal valorConta;

    @Column(name = "receber_notif")
    private Boolean receberNotificacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;
}
