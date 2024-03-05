package com.algaworks.pedidovenda.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class SaldoEmissaoBoletosController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String aviso;
	private Date dataCalculo;
	private Date dataInicial;
	private Date dataFinal;
	private List<Parcela> parcelas;
	private boolean selecionarTodas;;

	@PostConstruct
	public void init() {
		parcelas = new ArrayList<>();
		mockParcelas();
	}

	private void mockParcelas() {
		for (int i = 1; i <= 5; i++) {
			Parcela parcela = new Parcela();
			parcela.setNumero(i);
			parcela.setDataVencimento(criarDataVencimento(i));
			parcela.setValor(100.00 * i);
			parcela.setEmAtraso(i % 2 == 0);
			parcelas.add(parcela);
		}
	}

	private Date criarDataVencimento(int incrementoMes) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, incrementoMes);
		return cal.getTime();
	}

	public void selecionarTodasParcelas() {
		for (Parcela parcela : parcelas) {
			parcela.setAntecipar(this.selecionarTodas);
		}
	}

	public boolean isTemParcelaEmAtraso() {
		for (Parcela parcela : this.parcelas) {
			if (parcela.isEmAtraso()) {
				return true;
			}
		}
		return false;
	}

	public String getAviso() {
		return aviso;
	}

	public void setAviso(String aviso) {
		this.aviso = aviso;
	}

	public Date getDataCalculo() {
		return dataCalculo;
	}

	public void setDataCalculo(Date dataCalculo) {
		this.dataCalculo = dataCalculo;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public boolean isSelecionarTodas() {
		return selecionarTodas;
	}

	public void setSelecionarTodas(boolean selecionarTodas) {
		this.selecionarTodas = selecionarTodas;
	}

	public static class Parcela implements Serializable {
		private int numero;
		private Date dataVencimento;
		private double valor;
		private boolean emAtraso;
		private boolean antecipar;
		private double valorMora; // Adicionado
		private double valorMulta; // Adicionado
		private double valorAbatimento; // Adicionado

		// Getters e Setters
		public int getNumero() {
			return numero;
		}

		public void setNumero(int numero) {
			this.numero = numero;
		}

		public Date getDataVencimento() {
			return dataVencimento;
		}

		public void setDataVencimento(Date dataVencimento) {
			this.dataVencimento = dataVencimento;
		}

		public double getValor() {
			return valor;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}

		public boolean isEmAtraso() {
			return emAtraso;
		}

		public void setEmAtraso(boolean emAtraso) {
			this.emAtraso = emAtraso;
		}

		public boolean isAntecipar() {
			return antecipar;
		}

		public void setAntecipar(boolean antecipar) {
			this.antecipar = antecipar;
		}

		public double getValorMora() {
			return valorMora;
		}

		public void setValorMora(double valorMora) {
			this.valorMora = valorMora;
		}

		public double getValorMulta() {
			return valorMulta;
		}

		public void setValorMulta(double valorMulta) {
			this.valorMulta = valorMulta;
		}

		public double getValorAbatimento() {
			return valorAbatimento;
		}

		public void setValorAbatimento(double valorAbatimento) {
			this.valorAbatimento = valorAbatimento;
		}
	}

}
