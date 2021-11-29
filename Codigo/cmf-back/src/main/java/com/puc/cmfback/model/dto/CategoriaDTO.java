package com.puc.cmfback.model.dto;


import com.puc.cmfback.model.enums.OrdemEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Integer idCategoria;

    @NotEmpty
    private String nome;

    @NotEmpty
    private OrdemEnum ordem;

}
