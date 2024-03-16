package br.com.gfunc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gfunc.model.Apontamento;
import br.com.gfunc.util.jsf.FacesUtil;

@ManagedBean
@SessionScoped
public class ConsultaHistoricoController {

	private String chassi;
	private String view = "none";
	private String indicacaoRemarcacao;
	private List<Apontamento> apontamentos = null;

	public void pesquisarEV01() {
		apontamentos = popularApontamentos();

		if (chassi.isEmpty()) {
			FacesUtil.addInfoMessage("Informe um chassi para pesquisar");
		} else {
			apontamentos = pesquisarApontamentoPorChassi(chassi);
			if (apontamentos == null || apontamentos.isEmpty()) {
				view = "none";
				FacesUtil.addInfoMessage("Nenhum apontamento para o chassi: " + chassi);
			} else {
				view = "ocorrenciasNovo";
				System.out.println(view);
				System.out.println(apontamentos);
			}
		}

	}

	public void pesquisarEV02(Long numApontamento) {
		System.out.println("APONTAMENTO: " + numApontamento);
		view = "detalheNovo";
	}

	public List<Apontamento> popularApontamentos() {
		List<Apontamento> apontamentos = new ArrayList<>();

		apontamentos.add(new Apontamento(1L, "KHU8175", 1L, "9BWZZZ377VT004251", "010203040506", "SP",
				"Descrição do apontamento 1"));
		apontamentos.add(new Apontamento(2L, "KHU8175", 0L, "9BWZZZ377VT004251", "010203040507", "SP",
				"Descrição do apontamento 2"));
		apontamentos.add(new Apontamento(3L, "DHQ0552", 1L, "9BWZZZ377VT004251", "010203040508", "SP",
				"Descrição do apontamento 3"));
		apontamentos.add(new Apontamento(4L, "DHQ0552", 0L, "8BWZZZ377VT004222", "010203040509", "ES",
				"Descrição do apontamento 4"));
		apontamentos.add(new Apontamento(5L, "BZD8359", 0L, "7BWZZZ377VT007890", "010203040506", "MG",
				"Descrição do apontamento 5"));
		apontamentos.add(new Apontamento(6L, "BZD8359", 0L, "7BWZZZ377VT007890", "010203040507", "MG",
				"Descrição do apontamento 6"));
		apontamentos.add(new Apontamento(7L, "CJA0720", 0L, "6BWZZZ377VT003252", "010203040508", "ES",
				"Descrição do apontamento 7"));
		apontamentos.add(new Apontamento(8L, "CJA0720", 0L, "6BWZZZ377VT003252", "010203040509", "ES",
				"Descrição do apontamento 8"));

		return apontamentos;
	}

	public List<Apontamento> pesquisarApontamentoPorChassi(String chassi) {
		return this.apontamentos.stream().filter(apontamento -> apontamento.getChassi().equals(chassi))
				.collect(Collectors.toList());
	}

	public List<Apontamento> pesquisaPorindRemarcado(Long ind) {
		return this.apontamentos.stream().filter(apontamento -> apontamento.getIndRemarcado().equals(ind)).collect(Collectors.toList());
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public List<Apontamento> getApontamentos() {
		return apontamentos;
	}

	public void setApontamentos(List<Apontamento> apontamentos) {
		this.apontamentos = apontamentos;
	}
	
	public String getIndicacaoRemarcacao() {
		return indicacaoRemarcacao;
	}

	public void setIndicacaoRemarcacao(String indicacaoRemarcacao) {
		this.indicacaoRemarcacao = indicacaoRemarcacao;
	}


}
