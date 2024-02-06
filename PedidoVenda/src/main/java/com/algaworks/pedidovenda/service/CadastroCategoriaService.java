package com.algaworks.pedidovenda.service;


import java.io.Serializable;
import javax.inject.Inject;
import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.Categorias;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroCategoriaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Categorias categorias;

    @Transactional
    public void salvar(Categoria categoria, Categoria subcategoria) throws NegocioException {
        // Primeiro, valida e salva/atualiza a categoria pai se necessário.
        if (categoria != null) {
            validarCategoria(categoria);
            // Se categoria não tem ID, é nova e deve ser salva.
            // Se tem ID, pode ser uma atualização de descrição ou simplesmente reuso da instância.
            categoria = categorias.guardar(categoria);
        }
        
        // Se uma subcategoria é fornecida, trata de sua atualização ou salvamento.
        if (subcategoria != null && !subcategoria.getDescricao().isEmpty()) {
            // A subcategoria sempre precisa de uma categoria pai válida.
            if (categoria == null) {
                throw new NegocioException("A subcategoria precisa estar relacionada a uma categoria pai válida.");
            }
            
            subcategoria.setCategoriaPai(categoria);
            validarSubcategoria(subcategoria);
            // Se a subcategoria já existe (por ID), atualiza a relação com a nova categoria pai.
            // Não é necessário buscar por descrição, pois a identificação é feita pelo ID.
            categorias.guardar(subcategoria);
        }
    }

    private void validarCategoria(Categoria categoria) throws NegocioException {
        if (categoria.getDescricao().isEmpty()) {
            throw new NegocioException("A descrição da Categoria precisa ser preenchida.");
        }

        // Verifica duplicidade de descrição para novas categorias
        if (categoria.getId() == null) {
            Categoria categoriaExistente = categorias.porDescricao(categoria.getDescricao());
            if (categoriaExistente != null) {
                throw new NegocioException("Essa Categoria já está cadastrada.");
            }
        }
    }

    private void validarSubcategoria(Categoria subcategoria) throws NegocioException {
        if (subcategoria.getDescricao().isEmpty() || subcategoria.getCategoriaPai() == null) {
            throw new NegocioException("A descrição da SubCategoria precisa ser preenchida e estar relacionada a uma Categoria.");
        }

        // Verifica duplicidade de descrição para novas subcategorias dentro da mesma categoria pai
        if (subcategoria.getId() == null) {
            boolean existe = categorias.existeSubcategoria(subcategoria.getDescricao(), subcategoria.getCategoriaPai());
            if (existe) {
                throw new NegocioException("Já existe uma subcategoria com esta descrição para a categoria pai selecionada.");
            }
        }
    }
}
