package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.Categorias;
import com.algaworks.pedidovenda.service.CadastroCategoriaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	@Inject
	private CadastroCategoriaService cadastroCategoriaService;

	private Categoria categoria;
	private Categoria subCategoria;

	public CadastroCategoriaBean() {
		limpar();
	}
	
	public void inicializar() {
	}


	private void limpar() {
		categoria = new Categoria();
		subCategoria = new Categoria();
	}

	public void salvar() {
		cadastroCategoriaService.salvar(categoria, subCategoria);
		limpar();
		FacesUtil.addInfoMessage("Categoria salva com sucesso!");
	}
	
	public boolean isEditando() {
	    return this.categoria.getId() != null || this.subCategoria.getId() != null;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Categoria getSubCategoria() {
		return subCategoria;
	}


	public void setSubCategoria(Categoria subCategoria) {
		this.subCategoria = subCategoria;
	}
	
	public List<Categoria> completarPorDescricao(String descricao) {
		return categorias.listarPorDescricao(descricao);
	}
}
