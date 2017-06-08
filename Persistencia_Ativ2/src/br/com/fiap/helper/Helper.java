//colocar classes entity no persistence.xml
//colocar create true no persistence.xml para criar a tabela
//atualizar jars no diretorio /lib
//atualizar build path com os jars do /lib

package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.entity.Clientes;
import br.com.fiap.entity.Pedidos;

public class Helper {
	private EntityManager em;
	public Helper(EntityManager em){
		this.em = em;
	}
	
	public void salvar(Clientes clientes) {
		try {
			
			em.getTransaction().begin();
			em.persist(clientes);
			em.getTransaction().commit();
		}
		catch (Exception e) {
			throw e;
		} finally {
		}

		
	}
	

	public List<Clientes> listarClientes(){
		//Query query = em.createQuery("select c from Clientes c");
		//return query.getResultList();

		TypedQuery<Clientes> tQuery = em.createQuery("select c from Clientes c", Clientes.class);
		return tQuery.getResultList(); 

		
	}
	
	//typedquery
	
	public List<Pedidos> listarPedidos(Clientes clientes){
//		Query query = em.createQuery("select p from Pedidos p where clientes = :clientes" );
//		query.setParameter("clientes", clientes);
//		return query.getResultList();
			
		TypedQuery<Pedidos> tQuery = em.createQuery("select p from Pedidos p where clientes = :clientes", Pedidos.class);
		tQuery.setParameter("clientes", clientes); 
		return tQuery.getResultList(); 
	}
	
	public Clientes buscarCliente(Integer idClientes){
		//Query query = em.createQuery("select c from Clientesc c where idcliente = :idClientes");
		//query.setParameter("idClientes", idClientes); 
		//Clientes clientes = (Clientes) query.getSingleResult(); 
		//return clientes;
		
		//Alternativa
		
		TypedQuery<Clientes> tQuery = em.createQuery("select c from Clientes c where idcliente = :idClientes", Clientes.class);
		tQuery.setParameter("idClientes", idClientes); 
		return tQuery.getSingleResult(); 
		
	}


}