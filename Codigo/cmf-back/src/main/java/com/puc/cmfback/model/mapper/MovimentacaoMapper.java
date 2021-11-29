package com.puc.cmfback.model.mapper;

import com.puc.cmfback.model.dto.MovimentacaoDTO;
import com.puc.cmfback.model.entity.Movimentacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovimentacaoMapper {
    MovimentacaoMapper INSTANCE = Mappers.getMapper(MovimentacaoMapper.class);

    MovimentacaoDTO entityToDto(Movimentacao movimentacao);

    Movimentacao dtoToEntity(MovimentacaoDTO movimentacaoDTO);
}