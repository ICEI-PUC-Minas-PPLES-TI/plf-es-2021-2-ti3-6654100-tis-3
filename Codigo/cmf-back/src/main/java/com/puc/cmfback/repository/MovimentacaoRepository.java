package com.puc.cmfback.repository;


import com.puc.cmfback.model.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

    List<Movimentacao> findAllByDataCriacaoBetween(LocalDate startDate, LocalDate endDate);

    List<Movimentacao> findAllByTipoMovimentacao(String tipoMovimentacao);

    List<Movimentacao> findAllByOrdem(String oredem);
}
