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
		Categoria categoriaPersistida = null;

		if (categoria.getId() != null) {
			categoriaPersistida = categorias.porId(categoria.getId());
		} else if (!categoria.getDescricao().isEmpty()) {
			categoriaPersistida = categorias.porDescricao(categoria.getDescricao());
		}

		if (categoriaPersistida != null) {
			categoria = categoriaPersistida;
		} else {
			validarCategoria(categoria);
			categoria = categorias.guardar(categoria);
		}
		if (subcategoria != null && !subcategoria.getDescricao().isEmpty()) {
			subcategoria.setCategoriaPai(categoria);
			validarSubcategoria(subcategoria);
			categorias.guardar(subcategoria);
		}

		return categoria;
	}

	public void validarCategoria(Categoria categoria) {
		if (categoria.getDescricao().isEmpty()) {
			throw new NegocioException("A descrição da Categoria precisa ser preenchida.");
		}

		Categoria categoriaExistente = categorias.porDescricao(categoria.getDescricao());

		if (categoriaExistente != null && !categoriaExistente.equals(categoria)) {
			throw new NegocioException("Essa Categoria já está cadastrada.");
		}
	}

	public void validarSubcategoria(Categoria subcategoria) {
		if (subcategoria.getCategoriaPai() == null) {
			throw new NegocioException("A SubCategoria precisa estar relacionada a uma Categoria.");
		}
		boolean existe = categorias.existeSubcategoria(subcategoria.getDescricao(), subcategoria.getCategoriaPai());
		if (existe) {
			throw new NegocioException(
					"Já existe uma subcategoria com esta descrição para a categoria pai selecionada.");
		}
	}

}
