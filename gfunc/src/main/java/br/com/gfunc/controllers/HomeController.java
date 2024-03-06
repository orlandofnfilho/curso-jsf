package br.com.gfunc.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gfunc.services.exception.NegocioException;

@ManagedBean
@ViewScoped
public class HomeController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public HomeController() {
		
	}
	
	public void testador() throws Exception {
		System.out.println("Chegou aqui!!");
		throw new Exception("Exception message");
	}

}
