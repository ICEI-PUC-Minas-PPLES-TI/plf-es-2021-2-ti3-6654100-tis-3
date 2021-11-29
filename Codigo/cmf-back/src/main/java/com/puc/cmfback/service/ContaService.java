package com.puc.cmfback.service;

import com.puc.cmfback.exception.errors.NegocioException;
import com.puc.cmfback.model.dto.ContaDTO;
import com.puc.cmfback.model.mapper.ContaMapper;
import com.puc.cmfback.repository.ContaRepository;
import com.puc.cmfback.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ContaDTO criarConta(ContaDTO contaDTO) {

        var usuario = usuarioRepository.findById(contaDTO.getIdUsuario());

        if (!usuario.isPresent()) {
            throw new NegocioException("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }

        var conta = ContaMapper.INSTANCE.dtoToEntity(contaDTO);
        conta.setUsuario(usuario.get());
        conta = repository.save(conta);


        contaDTO = ContaMapper.INSTANCE.entityToDto(conta);
        contaDTO.setIdUsuario(conta.getUsuario().getIdUsuario());

        return contaDTO;
    }

    public List<ContaDTO> buscarContaPorIdUsuario(Integer idUsuario) {

        var usuario = usuarioRepository.findById(idUsuario);

        if (!usuario.isPresent()) {
            throw new NegocioException("Nenhum usuario encontrado", HttpStatus.NOT_FOUND);
        }

        var contaList = repository.findAllByUsuario(usuario.get());

        return contaList.stream().map(conta -> {
            var contaDTO = ContaMapper.INSTANCE.entityToDto(conta);
            contaDTO.setIdUsuario(conta.getUsuario().getIdUsuario());
            return contaDTO;
        }).collect(toList());
    }

    public void deletarContaPorId(Integer id) {
        repository.deleteById(id);
    }

    public ContaDTO atualizarConta(ContaDTO contaDTO) {
        var conta = ContaMapper.INSTANCE.dtoToEntity(contaDTO);

        var usuario = usuarioRepository.findById(contaDTO.getIdUsuario());

        if (!usuario.isPresent()) {
            throw new NegocioException("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        conta.setUsuario(usuario.get());

        conta = repository.save(conta);

        contaDTO = ContaMapper.INSTANCE.entityToDto(conta);
        contaDTO.setIdUsuario(usuario.get().getIdUsuario());

        return contaDTO;
    }
}