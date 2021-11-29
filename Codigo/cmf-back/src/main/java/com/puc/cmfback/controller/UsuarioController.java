package com.puc.cmfback.controller;

import com.puc.cmfback.model.dto.UsuarioDTO;
import com.puc.cmfback.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/buscar-por-email/{email}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam String email) {
        var resposta = service.buscarUsuarioPorEmail(email);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping(value = "/buscar-todos")
    public ResponseEntity<List<UsuarioDTO>> buscarTodosOsUsuarios() {
        var resposta = service.buscarTodosOsUsuarios();
        return ResponseEntity.ok().body(resposta);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        var resposta = service.criarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PutMapping(value = "/atualizar")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        var resposta = service.atualizarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @DeleteMapping(value = "/deletar/{email}")
    public ResponseEntity deletarUsuarioPorId(@RequestParam String email) {
        service.deletarUsuarioPorEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Boolean> login(@RequestHeader String email, @RequestHeader String senha) {
        var resposta = service.login(email, senha);
        return ResponseEntity.ok().body(resposta);
    }
}