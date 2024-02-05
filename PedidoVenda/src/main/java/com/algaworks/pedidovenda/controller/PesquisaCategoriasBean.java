package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.Categorias;
import com.algaworks.pedidovenda.repository.filter.CategoriaFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCategoriasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	private CategoriaFilter filtro;
	private List<Categoria> categoriasFiltradas;

	private Categoria categoriaSelecionada;

	public PesquisaCategoriasBean() {
		filtro = new CategoriaFilter();
	}

	public void pesquisar() {
		categoriasFiltradas = categorias.filtradas(filtro);
	}
	
	public void excluir() {
		categorias.remover(categoriaSelecionada);
		categoriasFiltradas.remove(categoriaSelecionada);
		
		FacesUtil.addInfoMessage("Categoria " + categoriaSelecionada.getDescricao() 
				+ " excluída com sucesso.");
	}
	public List<Categoria> getCategoriasFiltradas() {
		return categoriasFiltradas;
	}

	public CategoriaFilter getFiltro() {
		return filtro;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

}
