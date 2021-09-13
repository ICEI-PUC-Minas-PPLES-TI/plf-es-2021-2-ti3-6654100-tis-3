package com.puc.cmfback.service;

import com.puc.cmfback.exception.errors.UsuarioException;
import com.puc.cmfback.model.dto.UsuarioDTO;
import com.puc.cmfback.model.mapper.UsuarioMapper;
import com.puc.cmfback.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        var usuario = repository.findById(id);

        if (isNull(usuario)) {
            throw new UsuarioException("Usuario não encontrado", NOT_FOUND);
        }
        return UsuarioMapper.entityToDto(usuario.get());
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        var usuario = repository.findByEmail(email).get();

        if (isNull(usuario)) {
            throw new UsuarioException("Email não encontrado", NOT_FOUND);
        }
        return UsuarioMapper.entityToDto(usuario);
    }

    public void criarUsuario(UsuarioDTO usuarioDTO) {
        try {
            validarEmailJaExistente(usuarioDTO.getEmail());

            var usuario = UsuarioMapper.dtoToEntity(usuarioDTO);

            repository.save(usuario);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao salvar usuario", BAD_REQUEST);
        }
    }

    public void deletarUsuarioPorEmail(String email) {
        repository.deleteByEmail(email);
    }

    public List<UsuarioDTO> buscarTodosOsUsuarios() {
        try {
            var usuarios = repository.findAll();

            return usuarios.stream().map(UsuarioMapper::entityToDto).collect(toList());
        } catch (Exception e) {
            throw new UsuarioException("Erro ao buscar todos os usuários", NOT_FOUND);
        }
    }

    public void atualizarUsuario(UsuarioDTO usuarioDTO, Long id) {
        try {
            var usuario = UsuarioMapper.dtoToEntity(usuarioDTO);
            usuario.setId(id);

            repository.save(usuario);
        } catch (Exception e) {
            throw new UsuarioException("Erro ao atualizar usuario", BAD_REQUEST);
        }
    }

    private void validarEmailJaExistente(String email) {
        var usuario = repository.findByEmail(email);

        if (isNull(usuario))
            throw new UsuarioException("Usuario nao encontrado", NOT_FOUND);
    }
}