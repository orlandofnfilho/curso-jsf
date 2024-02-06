package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

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

	public boolean existeSubcategoria(String descricao, Categoria categoriaPai) {
		Long count = manager
				.createQuery("select count(c.id) from Categoria c " + "where upper(c.descricao) = :descricao "
						+ "and c.categoriaPai = :categoriaPai", Long.class)
				.setParameter("descricao", descricao.toUpperCase()).setParameter("categoriaPai", categoriaPai)
				.getSingleResult();

		return count > 0;
	}

	public List<Categoria> filtradas(CategoriaFilter filtro) {
		String jpql = "SELECT c FROM Categoria c LEFT JOIN c.categoriaPai cp WHERE 1=1";
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			jpql += " AND (LOWER(c.descricao) LIKE LOWER(:descricao) OR LOWER(cp.descricao) LIKE LOWER(:descricao))";
		}
		jpql += " ORDER BY cp.id NULLS FIRST, c.descricao ASC";

		Query query = manager.createQuery(jpql);
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			query.setParameter("descricao", "%" + filtro.getDescricao() + "%");
		}
		return query.getResultList();
	}

}