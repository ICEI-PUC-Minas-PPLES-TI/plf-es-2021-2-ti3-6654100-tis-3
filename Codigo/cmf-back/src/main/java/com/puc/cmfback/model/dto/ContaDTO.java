package com.puc.cmfback.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

    private Integer idConta;

    @NotEmpty
    private String tipoConta;

    @NotEmpty
    private LocalDate dataVencimento;

    @NotEmpty
    private BigDecimal valorConta;

    @NotEmpty
    private Boolean receberNotificacao;

    @NotEmpty
    private Integer idUsuario;

}
