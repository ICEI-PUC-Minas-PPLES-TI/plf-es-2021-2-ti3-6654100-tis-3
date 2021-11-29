package com.puc.cmfback.repository;


import com.puc.cmfback.model.entity.Conta;
import com.puc.cmfback.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {


    List<Conta> findAllByUsuario(Usuario usuario);
}
