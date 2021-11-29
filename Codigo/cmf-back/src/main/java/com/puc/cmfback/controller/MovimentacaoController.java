package com.puc.cmfback.controller;

import com.puc.cmfback.model.dto.MovimentacaoDTO;
import com.puc.cmfback.model.enums.OrdemEnum;
import com.puc.cmfback.model.enums.TipoMovimentacaoEnum;
import com.puc.cmfback.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService service;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<MovimentacaoDTO> criarMovimentacao(@RequestBody MovimentacaoDTO movimentacaoDTO) {
        var resposta = service.criarMovimentacao(movimentacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping(value = "/buscar-movimentacao-por-data/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<MovimentacaoDTO>> buscarMovimentacaoPorData(@RequestParam String dataInicial, @RequestParam String dataFinal) {
        var resposta = service.buscarMovimentacaoPorData(LocalDate.parse(dataInicial), LocalDate.parse(dataFinal));
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @GetMapping(value = "/tipoMovimentacao/{tipoMovimentacao}")
    public ResponseEntity<List<MovimentacaoDTO>> buscarMovimentacaoPorData(@RequestParam TipoMovimentacaoEnum tipoMovimentacaoEnum) {
        var resposta = service.buscarMovimentacaoPorTipoMovimentacao(tipoMovimentacaoEnum.toString());
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @GetMapping(value = "/tipoOrdem/{tipoOrdem}")
    public ResponseEntity<List<MovimentacaoDTO>> buscarMovimentacaoPorData(@RequestParam OrdemEnum ordemEnum) {
        var resposta = service.buscarMovimentacaoPorTipoOrdem(ordemEnum.toString());
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<Void> deletarMovimentacaoPorId(@RequestParam Integer id) {
        service.deletarProdutoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping(value = "/atualizar")
    public ResponseEntity<MovimentacaoDTO> atualizarUsuario(@RequestBody MovimentacaoDTO movimentacaoDTO) {
        var resposta = service.atualizarProduto(movimentacaoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}
