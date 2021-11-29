package com.puc.cmfback.model.mapper;

import com.puc.cmfback.model.dto.ContaDTO;
import com.puc.cmfback.model.dto.NotificacaoDTO;
import com.puc.cmfback.model.entity.Conta;
import com.puc.cmfback.model.entity.Notificacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContaMapper {

    ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    ContaDTO entityToDto(Conta entity);

    Conta dtoToEntity(ContaDTO dto);
}