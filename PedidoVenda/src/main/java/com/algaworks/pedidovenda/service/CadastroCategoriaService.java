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
	public Categoria salvar(Categoria categoria, Categoria subcategoria) {	
		if(!subcategoria.getDescricao().isEmpty()) {
			validarRelacao(categoria, subcategoria);
			return categorias.guardar(categoria);
		}else {	
			return categorias.guardar(categoria);
		}
		
	}

	public Categoria porDescricao(String descricao) {
		Categoria categoriaExistente = categorias.porDescricao(descricao);
		return categoriaExistente;
	}
	
	public void validarRelacao(Categoria categoria, Categoria subcategoria) {
		if(categoria.getDescricao().isEmpty()) {
			throw new NegocioException("A SubCategoria precisa está relacionada à uma Categoria");	
		}
		if(subcategoria.getDescricao().isEmpty()) {
			throw new NegocioException("A descrição da SubCategoria não pode está vazia.");	
		}
		subcategoria.setCategoriaPai(categoria);
		categoria.getSubcategorias().add(subcategoria);
	}
	
	public void validarCategoria(Categoria categoria) {
		if(categoria.getDescricao().isEmpty()) {
			throw new NegocioException("A descrição da Categoria precisa ser preenchida");
		}
		
		Categoria categoriaExistente = categorias.porDescricao(categoria.getDescricao());

		if (categoriaExistente != null && !categoriaExistente.equals(categoria)) {
			throw new NegocioException("Essa Categoria já está cadastrada");
		}
	}

}
