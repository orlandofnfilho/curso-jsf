package br.com.gfunc.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class HomeController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String usuario = "Admin";
	
	public HomeController() {
		
	}
	
	public void testador() throws Exception {
		System.out.println("Chegou aqui!!");
		throw new Exception("Exception message");
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	

}
