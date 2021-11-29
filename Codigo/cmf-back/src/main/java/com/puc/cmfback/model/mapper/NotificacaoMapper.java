package com.puc.cmfback.model.mapper;

import com.puc.cmfback.model.dto.NotificacaoDTO;
import com.puc.cmfback.model.entity.Notificacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NotificacaoMapper {

    NotificacaoMapper INSTANCE = Mappers.getMapper(NotificacaoMapper.class);

    NotificacaoDTO entityToDto(Notificacao entity);

    Notificacao dtoToEntity(NotificacaoDTO dto);
}