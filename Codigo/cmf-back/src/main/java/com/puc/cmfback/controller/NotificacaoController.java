package com.puc.cmfback.controller;

import com.puc.cmfback.model.dto.NotificacaoDTO;
import com.puc.cmfback.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/notificacao")
public class NotificacaoController {

    @Autowired
    private NotificacaoService service;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<NotificacaoDTO> criarNotificacao(@RequestBody NotificacaoDTO categoriaDTO) {
        var resposta = service.criarNotificacao(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping(value = "/buscar-por-conta/{contaId}")
    public ResponseEntity<List<NotificacaoDTO>> buscarNotificacaoPorConta(@RequestParam Integer contaId) {
        var resposta = service.buscarNotificacaoPorIdConta(contaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping(value = "/buscar-por-usuario/{usuarioId}")
    public ResponseEntity<List<NotificacaoDTO>> buscarNotificacaoPorUsuario(@RequestParam Integer usuarioId) {
        var resposta = service.buscarNotificacaoPorIdUsuario(usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletarNotificacoPorId(@RequestParam Integer id) {
        service.deletarNotificacaoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping(value = "/atualizar")
    public ResponseEntity<NotificacaoDTO> atualizarNotificaco(@RequestBody NotificacaoDTO notificacaoDTO) {
        var resposta = service.atualizarNotificaco(notificacaoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}