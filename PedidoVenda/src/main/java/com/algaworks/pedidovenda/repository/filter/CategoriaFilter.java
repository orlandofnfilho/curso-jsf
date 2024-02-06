package com.algaworks.pedidovenda.repository.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.pedidovenda.model.Categoria;

public class CategoriaFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private Categoria categoriaPai;
	private List<Categoria> subcategorias = new ArrayList<>();

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
	

}
