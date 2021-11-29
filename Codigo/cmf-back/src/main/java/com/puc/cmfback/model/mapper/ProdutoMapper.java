package com.puc.cmfback.model.mapper;

import com.puc.cmfback.model.dto.ProdutoDTO;
import com.puc.cmfback.model.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    ProdutoDTO entityToDto(Produto produto);

    Produto dtoToEntity(ProdutoDTO produto);
}