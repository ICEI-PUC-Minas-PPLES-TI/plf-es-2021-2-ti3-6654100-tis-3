package com.puc.cmfback.service;

import com.puc.cmfback.exception.errors.NegocioException;
import com.puc.cmfback.model.dto.CategoriaDTO;
import com.puc.cmfback.model.mapper.CategoriaMapper;
import com.puc.cmfback.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Slf4j
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public CategoriaDTO buscarCategoriaPorNome(String nome) {

        var categoria = repository.findByNome(nome);

        if (!categoria.isPresent())
            throw new NegocioException("Categoria não encontrada", BAD_REQUEST);

        return CategoriaMapper.INSTANCE.entityToDto(categoria.get());
    }

    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO) {
        validarCategoriaJaCadastrada(categoriaDTO.getNome());

        var categoria = repository.save(CategoriaMapper.INSTANCE.dtoToEntity(categoriaDTO));

        if (Objects.nonNull(categoria)) {
            return CategoriaMapper.INSTANCE.entityToDto(categoria);
        } else {
            throw new NegocioException("Categoria não encontrada", NOT_FOUND);
        }
    }

    public void atualizarCategoria(CategoriaDTO categoriaDTO) {
        try {
            var categoria = CategoriaMapper.INSTANCE.dtoToEntity(categoriaDTO);
            repository.save(categoria);
        } catch (Exception e) {
            throw new NegocioException("Erro ao atualizar categoria", BAD_REQUEST);
        }
    }

    private void validarCategoriaJaCadastrada(String nome) {
        var categoria = repository.findByNome(nome);

        if (categoria.isPresent())
            throw new NegocioException("Categoria já cadastrada", BAD_REQUEST);
    }

    public void deletarCategoriaPorId(Integer id) {
        repository.deleteById(id);
    }

    public List<CategoriaDTO> buscarTodosAsCategorias() {
        var categoriaList = repository.findAll();

        if (categoriaList.isEmpty())
            throw new NegocioException("Nenhuma categoria encontrada", NOT_FOUND);

        return categoriaList.stream().map(CategoriaMapper.INSTANCE::entityToDto).collect(toList());
    }
}