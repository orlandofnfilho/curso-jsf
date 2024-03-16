package br.com.gfunc.model;

import java.io.Serializable;
import java.util.Objects;

public class Apontamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long numApontamento;
	private String placa;
	private Long indRemarcado;
	private String chassi;
	private String renavam;
	private String ufPlaca;
	private String descricao;
	
	public Apontamento() {
	}
	

	public Apontamento(Long numApontamento, String placa, Long indRemarcado, String chassi, String renavam,
			String ufPlaca, String descricao) {
		this.numApontamento = numApontamento;
		this.placa = placa;
		this.indRemarcado = indRemarcado;
		this.chassi = chassi;
		this.renavam = renavam;
		this.ufPlaca = ufPlaca;
		this.descricao = descricao;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public Long getIndRemarcado() {
		return indRemarcado;
	}


	public void setIndRemarcado(Long indRemarcado) {
		this.indRemarcado = indRemarcado;
	}


	public Long getNumApontamento() {
		return numApontamento;
	}
	public void setNumApontamento(Long numApontamento) {
		this.numApontamento = numApontamento;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public String getRenavam() {
		return renavam;
	}
	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}
	public String getUfPlaca() {
		return ufPlaca;
	}
	public void setUfPlaca(String ufPlaca) {
		this.ufPlaca = ufPlaca;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numApontamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apontamento other = (Apontamento) obj;
		return Objects.equals(numApontamento, other.numApontamento);
	}

	@Override
	public String toString() {
		return "Apontamento [numApontamento=" + numApontamento + ", chassi=" + chassi + ", renavam=" + renavam
				+ ", ufPlaca=" + ufPlaca + ", descricao=" + descricao + "]";
	}

	
	
}
