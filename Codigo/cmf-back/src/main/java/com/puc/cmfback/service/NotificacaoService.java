package com.puc.cmfback.service;

import com.puc.cmfback.exception.errors.NegocioException;
import com.puc.cmfback.model.dto.NotificacaoDTO;
import com.puc.cmfback.model.mapper.NotificacaoMapper;
import com.puc.cmfback.repository.ContaRepository;
import com.puc.cmfback.repository.NotificacaoRepository;
import com.puc.cmfback.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository repository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public NotificacaoDTO criarNotificacao(NotificacaoDTO notificacaoDTO) {

        var usuario = usuarioRepository.findById(notificacaoDTO.getIdUsuario());

        if (!usuario.isPresent()) {
            throw new NegocioException("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }

        var conta = contaRepository.findById(notificacaoDTO.getIdConta());

        if (!conta.isPresent()) {
            throw new NegocioException("Conta não encontrada", HttpStatus.NOT_FOUND);
        }

        var notificacao = NotificacaoMapper.INSTANCE.dtoToEntity(notificacaoDTO);

        notificacao.setConta(conta.get());
        notificacao.setUsuario(usuario.get());

        notificacao = repository.save(notificacao);

        notificacaoDTO = NotificacaoMapper.INSTANCE.entityToDto(notificacao);

        notificacaoDTO.setIdUsuario(notificacao.getUsuario().getIdUsuario());
        notificacaoDTO.setIdConta(notificacao.getConta().getIdConta());

        return notificacaoDTO;
    }

    public List<NotificacaoDTO> buscarNotificacaoPorIdConta(Integer idConta) {

        var conta = contaRepository.findById(idConta);

        if (!conta.isPresent()) {
            throw new NegocioException("Nenhuma conta encontrada", HttpStatus.NOT_FOUND);
        }

        var notificacaoList = repository.findAllByConta(conta.get());

        return notificacaoList.stream().map(notificacao -> {
            var notificacaoDTO = NotificacaoMapper.INSTANCE.entityToDto(notificacao);
            notificacaoDTO.setIdConta(notificacao.getConta().getIdConta());
            notificacaoDTO.setIdUsuario(notificacao.getUsuario().getIdUsuario());
            return notificacaoDTO;
        }).collect(Collectors.toList());
    }

    public List<NotificacaoDTO> buscarNotificacaoPorIdUsuario(Integer idUsuario) {

        var usuario = usuarioRepository.findById(idUsuario);

        if (!usuario.isPresent()) {
            throw new NegocioException("Nenhum usuario encontrado", HttpStatus.NOT_FOUND);
        }

        var notificacaoList = repository.findAllByUsuario(usuario.get());

        return notificacaoList.stream().map(notificacao -> {
            var notificacaoDTO = NotificacaoMapper.INSTANCE.entityToDto(notificacao);
            notificacaoDTO.setIdConta(notificacao.getConta().getIdConta());
            notificacaoDTO.setIdUsuario(notificacao.getUsuario().getIdUsuario());
            return notificacaoDTO;
        }).collect(Collectors.toList());
    }

    public void deletarNotificacaoPorId(Integer id) {
        repository.deleteById(id);
    }

    public NotificacaoDTO atualizarNotificaco(NotificacaoDTO notificacaoDTO) {
        var notificacao = NotificacaoMapper.INSTANCE.dtoToEntity(notificacaoDTO);

        var usuario = usuarioRepository.findById(notificacaoDTO.getIdUsuario());
        var conta = contaRepository.findById(notificacaoDTO.getIdConta());

        if (!usuario.isPresent()) {
            throw new NegocioException("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }

        if (!conta.isPresent()) {
            throw new NegocioException("Conta não encontrada", HttpStatus.NOT_FOUND);
        }

        notificacao.setUsuario(usuario.get());
        notificacao.setConta(conta.get());

        notificacao = repository.save(notificacao);

        notificacaoDTO = NotificacaoMapper.INSTANCE.entityToDto(notificacao);

        notificacaoDTO.setIdUsuario(usuario.get().getIdUsuario());
        notificacaoDTO.setIdConta(conta.get().getIdConta());

        return notificacaoDTO;
    }
}