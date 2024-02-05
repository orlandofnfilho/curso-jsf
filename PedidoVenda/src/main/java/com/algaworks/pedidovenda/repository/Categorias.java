package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}
	
	public List<Categoria> raizes() {
		return manager.createQuery("from Categoria where categoriaPai is null", 
				Categoria.class).getResultList();
	}
	
	public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
		return manager.createQuery("from Categoria where categoriaPai = :raiz", 
				Categoria.class).setParameter("raiz", categoriaPai).getResultList();
	}
	
	public Categoria guardar(Categoria categoria) {
		return manager.merge(categoria);
	}
	
	@Transactional
	public void remover(Categoria categoria) {
		try {
			categoria = porId(categoria.getId());
			manager.remove(categoria);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Categoria não pode ser excluída.");
		}
	}
	
	public Categoria porDescricao(String descricao) {
		try {
			return manager.createQuery("from Categoria where upper(descricao) = :descricao", Categoria.class)
					.setParameter("descricao", descricao.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Categoria> listarPorDescricao(String descricao) {
		return this.manager.createQuery("from Categoria " +
				"where upper(descricao) like :descricao", Categoria.class)
				.setParameter("descricao", descricao.toUpperCase() + "%")
				.getResultList();
	}
	
	public List<Categoria> listarSubcategoriasPorDescricao(String descricao, Categoria categoriaPai) {
	    return this.manager.createQuery("from Categoria c " +
	            "where upper(c.descricao) like :descricao " +
	            "and c.categoriaPai = :categoriaPai", Categoria.class)
	            .setParameter("descricao", descricao.toUpperCase() + "%")
	            .setParameter("categoriaPai", categoriaPai)
	            .getResultList();
	}
	
}