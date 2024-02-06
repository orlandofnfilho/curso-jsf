package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

	private Long categoriaId;
	private Long categoriaPaiId;
	private Long subcategoriaId;
	private Categoria categoria;
	private Categoria subCategoria;

	public CadastroCategoriaBean() {
		limpar();
	}

	public void inicializar() {
		if (this.categoriaPaiId != null) { 
			this.categoria = categorias.porId(this.categoriaPaiId);
		}
		if (this.subCategoria != null) { 
			Categoria subcategoria = categorias.porId(this.subcategoriaId);
			this.subCategoria = categorias.porId(subcategoriaId);
		}

	}

	private void limpar() {
		categoria = new Categoria();
		subCategoria = new Categoria();
	}

	public void salvar() {
		cadastroCategoriaService.salvar(categoria);
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

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Long getCategoriaPaiId() {
		return categoriaPaiId;
	}

	public void setCategoriaPaiId(Long categoriaPaiId) {
		this.categoriaPaiId = categoriaPaiId;
	}

	public Long getSubcategoriaId() {
		return subcategoriaId;
	}

	public void setSubcategoriaId(Long subcategoriaId) {
		this.subcategoriaId = subcategoriaId;
	}


}
