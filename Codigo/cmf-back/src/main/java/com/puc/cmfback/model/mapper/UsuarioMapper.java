package com.puc.cmfback.model.mapper;

import com.puc.cmfback.model.dto.UsuarioDTO;
import com.puc.cmfback.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO entityToDto(Usuario usuario);

    Usuario dtoToEntity(UsuarioDTO usuarioDTO);
}