package com.puc.cmfback.model.dto;


import com.puc.cmfback.model.enums.OrdemEnum;
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
public class MovimentacaoDTO {

    private Integer idMovimentacao;

    @NotEmpty
    private OrdemEnum ordem;

    @NotEmpty
    private BigDecimal valor;

    @NotEmpty
    private Integer idUsuario;

    private Integer idProduto;

    @NotEmpty
    private Integer idCategoria;

    private String tipoMovimentacao;

    private LocalDate dataCriacao;
}
