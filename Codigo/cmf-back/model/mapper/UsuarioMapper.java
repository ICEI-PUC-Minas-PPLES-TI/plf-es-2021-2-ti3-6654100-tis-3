package com.puc.cmfback.model.mapper;

import com.puc.cmfback.model.dto.UsuarioDTO;
import com.puc.cmfback.model.entity.Usuario;
import org.springframework.beans.BeanUtils;

import static java.util.Objects.isNull;

public class UsuarioMapper {

    public static UsuarioDTO entityToDto(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        if (!isNull(usuario)) {
            BeanUtils.copyProperties(usuario, usuarioDTO);
        }
        return usuarioDTO;
    }

    public static Usuario dtoToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        if (!isNull(usuarioDTO)) {
            BeanUtils.copyProperties(usuarioDTO, usuario);
        }
        return usuario;
    }
}