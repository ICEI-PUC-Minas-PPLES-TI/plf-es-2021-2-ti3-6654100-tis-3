package com.puc.cmfback.service;

import com.puc.cmfback.exception.errors.NegocioException;
import com.puc.cmfback.model.dto.UsuarioDTO;
import com.puc.cmfback.model.mapper.UsuarioMapper;
import com.puc.cmfback.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigInteger;
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

    private EntityManager em;

    public UsuarioDTO buscarUsuarioPorEmail(String email) {

        var usuario = repository.findByEmail(email);

        if (isNull(usuario) || !usuario.isPresent()) {
            throw new NegocioException("Email não encontrado", NOT_FOUND);
        }
        return UsuarioMapper.INSTANCE.entityToDto(usuario.get());
    }

    public void deletarUsuarioPorEmail(String email) {
        repository.deleteByEmail(email);
    }

    public List<UsuarioDTO> buscarTodosOsUsuarios() {
        var usuarios = repository.findAll();

        if (usuarios.isEmpty())
            throw new NegocioException("Nenhum usuario encontrado", NOT_FOUND);

        return usuarios.stream().map(UsuarioMapper.INSTANCE::entityToDto).collect(toList());
    }

    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDTO) {
        var usuario = UsuarioMapper.INSTANCE.dtoToEntity(usuarioDTO);

        usuario = repository.save(usuario);

        return UsuarioMapper.INSTANCE.entityToDto(usuario);
    }

    private void validarEmailJaExistente(String email) {
        var usuario = repository.findByEmail(email);

        if (usuario.isPresent())
            throw new NegocioException("Usuário já cadastrado no sistema", BAD_REQUEST);
    }

    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        validarEmailJaExistente(usuarioDTO.getEmail());

        var usuario = UsuarioMapper.INSTANCE.dtoToEntity(usuarioDTO);

        repository.save(usuario);

        var usuarioOpt = repository.findByEmail(usuario.getEmail());

        if (usuarioOpt.isPresent()) {
            return UsuarioMapper.INSTANCE.entityToDto(usuario);
        } else {
            throw new NegocioException("Usuario não encontrado", NOT_FOUND);
        }
    }

    public boolean login(String email, String senha) {
        var usuario = repository.findByEmail(email);

        if (isNull(usuario) || !usuario.isPresent())
            throw new NegocioException("Email não encontrado", NOT_FOUND);

        return usuario.get().getSenha().equals(senha);
    }

    public Integer queryCriarUsuario() {

        var query = em.createNativeQuery("INSERT INTO Usuario (email, nome, senha) VALUES (:email, :nome, :senha)");

        var biid = (BigInteger) query.getSingleResult();

        Long id = biid.longValue();

        return id.intValue();
    }
}