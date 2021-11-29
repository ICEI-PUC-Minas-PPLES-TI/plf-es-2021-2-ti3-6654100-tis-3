package com.puc.cmfback.model.mapper;

import com.puc.cmfback.model.dto.CategoriaDTO;
import com.puc.cmfback.model.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaDTO entityToDto(Categoria entity);

    Categoria dtoToEntity(CategoriaDTO dto);
}