package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Produto;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produto produto;
	
	
	public void salvar() {
		System.out.println(produto.toString());
	}

	public Produto getProduto() {
		return produto;
	}

}