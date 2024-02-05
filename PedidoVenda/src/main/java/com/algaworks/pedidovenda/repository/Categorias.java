package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.NullPrecedence;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.filter.CategoriaFilter;
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
		return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
	}

	public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
		return manager.createQuery("from Categoria where categoriaPai = :raiz", Categoria.class)
				.setParameter("raiz", categoriaPai).getResultList();
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
					.setParameter("descricao", descricao.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Categoria> listarPorDescricao(String descricao) {
		return this.manager
				.createQuery("from Categoria c " + "where c.categoriaPai is null "
						+ "and upper(c.descricao) like :descricao", Categoria.class)
				.setParameter("descricao", descricao.toUpperCase() + "%").getResultList();
	}

	public boolean existeSubcategoria(String descricao, Categoria categoriaPai) {
		Long count = manager
				.createQuery("select count(c.id) from Categoria c " + "where upper(c.descricao) = :descricao "
						+ "and c.categoriaPai = :categoriaPai", Long.class)
				.setParameter("descricao", descricao.toUpperCase()).setParameter("categoriaPai", categoriaPai)
				.getSingleResult();

		return count > 0; 
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> filtradas(CategoriaFilter filtro) {
	    Session session = manager.unwrap(Session.class);
	    Criteria criteria = session.createCriteria(Categoria.class);
	    
	    if (StringUtils.isNotBlank(filtro.getDescricao())) {
	        Criterion descricaoCategoria = Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE);
	        Criterion descricaoCategoriaPai = Restrictions.ilike("categoriaPai.descricao", filtro.getDescricao(), MatchMode.ANYWHERE);
	        criteria.add(Restrictions.or(descricaoCategoria, descricaoCategoriaPai));
	    }
	    
	    Order orderByCategoriaPai = Order.asc("categoriaPai.id").nulls(NullPrecedence.FIRST);
	    Order orderByDescricao = Order.asc("descricao");
	    criteria.addOrder(orderByCategoriaPai).addOrder(orderByDescricao);
	    
	    return criteria.list();
	}


}