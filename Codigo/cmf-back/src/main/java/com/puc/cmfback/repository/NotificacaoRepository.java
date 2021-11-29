package com.puc.cmfback.repository;


import com.puc.cmfback.model.entity.Conta;
import com.puc.cmfback.model.entity.Notificacao;
import com.puc.cmfback.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Integer> {


    List<Notificacao> findAllByConta(Conta conta);

    List<Notificacao> findAllByUsuario(Usuario usuario);
}
