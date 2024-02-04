package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.service.CadastroCategoriaService;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCategoriaService cadastroCategoriaService;

	private Categoria categoria;
	private Categoria categoriaPai;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;
	
	
	
	public CadastroCategoriaBean() {
		limpar();
	}
	

	private void limpar() {
		categoria = new Categoria();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
