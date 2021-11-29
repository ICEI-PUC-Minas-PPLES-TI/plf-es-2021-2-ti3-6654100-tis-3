package com.puc.cmfback.controller;

import com.puc.cmfback.model.dto.ContaDTO;
import com.puc.cmfback.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/conta")
public class ContaController {

    @Autowired
    private ContaService service;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ContaDTO> criarConta(@RequestBody ContaDTO contaDTO) {
        var resposta = service.criarConta(contaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping(value = "/buscar-por-usuario/{usuarioId}")
    public ResponseEntity<List<ContaDTO>> buscarContaPorUsuario(@RequestParam Integer usuarioId) {
        var resposta = service.buscarContaPorIdUsuario(usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletarContaPorId(@RequestParam Integer id) {
        service.deletarContaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping(value = "/atualizar")
    public ResponseEntity<ContaDTO> atualizarConta(@RequestBody ContaDTO contaDTO) {
        var resposta = service.atualizarConta(contaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}