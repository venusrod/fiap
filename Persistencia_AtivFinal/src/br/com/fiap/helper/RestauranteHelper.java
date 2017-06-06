package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.entity.Produto;
import br.com.fiap.entity.Restaurante;
import br.com.fiap.entity.CategoriaProduto;

public class RestauranteHelper {
	private EntityManager em;
	public RestauranteHelper(EntityManager em){
		this.em = em;
	}
	
	public void salvar(Restaurante restaurante) {
		try {
			
			em.getTransaction().begin();
			em.persist(restaurante);
			em.getTransaction().commit();
		}
		catch (Exception e) {
			throw e;
		} finally {
		}

		
	}
	

	public List<Restaurante> listarRestaurante(){
		Query query = em.createQuery("select r from Restaurante r");
		return query.getResultList();
	}
	
	public List<CategoriaProduto> listarCategoriaProduto(Restaurante restaurante){
		Query query = em.createQuery("select c from CategoriaProduto c where restaurante = :restaurante" );
		query.setParameter("restaurante", restaurante);
		return query.getResultList();
	}

	public List<Produto> listarProduto(CategoriaProduto categoriaProduto){
		Query query = em.createQuery("select p from Produto p where categoriaProduto = :categoriaProduto" );
		query.setParameter("categoriaProduto", categoriaProduto);
		return query.getResultList();
	}
 
}	