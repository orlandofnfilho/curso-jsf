package com.algaworks.cursojavaee;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class InversaoNomeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String nomeInvertido;
	private String nomeMaiusculo;
	private String nomeMinusculo;
	private int quantidadePalavras;

	public void maiusculo() {
		this.nomeMaiusculo = "";
		this.nomeMaiusculo = getNome().toUpperCase();
		System.out.println("Nome em caixa alta: " + this.nomeMaiusculo);
	}
	
	public void nomeMinusculo() {
		this.nomeMinusculo = "";
		this.nomeMinusculo = getNome().toLowerCase();
		System.out.println("Nome em caixa baixa: " + this.nomeMinusculo);
	}

	public void inverter() {
		this.nomeInvertido = "";
		this.quantidadePalavras = 0;

		if (this.nome != null && !this.nome.isEmpty()) { 
			this.quantidadePalavras = 1;
		}

		for (int i = this.nome.length() - 1; i >= 0; i--) {
			char letra = this.nome.charAt(i);
			this.nomeInvertido += letra;

			if (letra == ' ') {
				this.quantidadePalavras++;
			}
		}
		this.maiusculo();
		this.nomeMinusculo();
		System.out.println("Nome invertido: " + this.nomeInvertido);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeInvertido() {
		return nomeInvertido;
	}

	public String getNomeMinusculo() {
		return nomeMinusculo;
	}

	public int getQuantidadePalavras() {
		return quantidadePalavras;
	}

	public String getNomeMaiusculo() {
		return nomeMaiusculo;
	}

}