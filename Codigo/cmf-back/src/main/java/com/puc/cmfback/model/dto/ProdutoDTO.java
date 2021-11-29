package com.puc.cmfback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Integer idProduto;

    @NotEmpty
    private String nome;

    @NotEmpty
    private Integer quantidade;

}
