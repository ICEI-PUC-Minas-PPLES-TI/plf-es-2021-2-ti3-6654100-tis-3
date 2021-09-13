package com.puc.cmfback.controller;

import com.puc.cmfback.model.dto.UsuarioDTO;
import com.puc.cmfback.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
        var resposta = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestHeader String email) {
        var resposta = usuarioService.buscarUsuarioPorEmail(email);
        return ResponseEntity.ok().body(resposta);
    }

    @PostMapping(value = "cadastrar")
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.atualizarUsuario(usuarioDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @DeleteMapping
    public ResponseEntity.BodyBuilder deletarUsuarioPorEmail(@RequestHeader String email) {
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodosOsUsuarios() {
        var resposta = usuarioService.buscarTodosOsUsuarios();
        return ResponseEntity.ok().body(resposta);
    }
}
