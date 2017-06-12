package br.com.fiap.aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import br.com.fiap.dao.Dao;
import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Clientes;
import br.com.fiap.entity.Pedidos;

public class TestaAplicacao {

	public static void main(String[] args) {

		
		// INCLUINDO CLIENTE 1 e 2 PEDIDOS PARA O CLIENTE
		Clientes clientes = new Clientes(); 

		clientes.setNome("CECILIA");
		clientes.seteMail("cecilia@ceci.com");

		Dao<Clientes> clientesDao = new GenericDao<>(Clientes.class);

		try {
			clientesDao.adicionar(clientes);
			System.out.println("Incluindo Cliente 1");			
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Date data = new Date();

		
		Pedidos pedidos = new Pedidos();
		pedidos.setClientes(clientes);
		pedidos.setData(data);
		pedidos.setDescricao("PEDIDO A - CLIENTE 1");
		pedidos.setValor(50);
				
		Dao<Pedidos> pedidosDao = new GenericDao<>(Pedidos.class);
	
		try {
			System.out.println("     Incluindo Pedido: " + pedidos.getDescricao());
			pedidosDao.adicionar(pedidos);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		pedidos = new Pedidos();
		pedidos.setClientes(clientes);
		pedidos.setData(data);
		pedidos.setDescricao("PEDIDO B - CLIENTE 1");
		pedidos.setValor(100);

		try {
			System.out.println("     Incluindo Pedido: " + pedidos.getDescricao());
			pedidosDao.adicionar(pedidos);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//INCLUINDO CLIENTE 2 E 2 PEDIDOS PARA O CLIENTE
		System.out.println("");
		clientes = new Clientes(); 

		clientes.setNome("YARA");
		clientes.seteMail("yara@oregon.com");

		clientesDao = new GenericDao<>(Clientes.class);

		try {
			System.out.println("Incluindo Cliente 2");
			clientesDao.adicionar(clientes);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		pedidos = new Pedidos();
		pedidos.setClientes(clientes);
		pedidos.setData(data);
		pedidos.setDescricao("PEDIDO C - CLIENTE 2");
		pedidos.setValor(150);
				
		pedidosDao = new GenericDao<>(Pedidos.class);
	
		try {
			System.out.println("     Incluindo pedido: " + pedidos.getDescricao());
			pedidosDao.adicionar(pedidos);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		pedidos = new Pedidos();
		pedidos.setClientes(clientes);
		pedidos.setData(data);
		pedidos.setDescricao("PEDIDO D - CLIENTE 2");
		pedidos.setValor(200);

		try {
			System.out.println("     Incluindo Pedido: " + pedidos.getDescricao());
			pedidosDao.adicionar(pedidos);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Listar os Clientes
		System.out.println("");
				
		List<Clientes> listaClientes = new ArrayList<Clientes>();
		listaClientes = clientesDao.listar();
		System.out.println("Listando os Clientes");				
		
        for (Clientes cliente_for: listaClientes) {
        	System.out.println("Nome: " + cliente_for.getNome() + " - EMail: " + cliente_for.geteMail() );
        }

        //Listar os Pedidos
		System.out.println("");
        
        List<Pedidos> listaPedidos = new ArrayList<Pedidos>();
		listaPedidos = pedidosDao.listar();
		System.out.println("Listando os Pedidos");				
			            
        for (Pedidos pedidos_for: listaPedidos) {
        	System.out.println("Descricao: " + pedidos_for.getDescricao() + " - Valor: " + pedidos_for.getValor() );
        }

				
		//Atualizar Nome do cliente 1 
		System.out.println("");

		clientesDao = new GenericDao<>(Clientes.class);
		clientes = clientesDao.buscar(1);
		clientes.setNome("CECILIA NOME ALTERADO");

		try {
			System.out.println("Atualizar Nome do Cliente 1");
			clientesDao.atualizar(clientes);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}


		System.out.println("");
		
		listaClientes = clientesDao.listar();
		System.out.println("Lista de Clientes após Alteração de Cliente 1");
		
        for (Clientes cliente_for: listaClientes) {
        	System.out.println("Nome: " + cliente_for.getNome() + " - EMail: " + cliente_for.geteMail() );
        }

        //Atualizar Pedido A do Cliente 1
        pedidosDao = new GenericDao<>(Pedidos.class);
        pedidos = pedidosDao.buscar(1);
        pedidos.setDescricao("PEDIDO A - CLIENTE 1 - ALTERADO");
        
        System.out.println("");
		        
		try {
			pedidosDao.atualizar(pedidos);
			System.out.println("Atualizar Descrição do Pedido A - Cliente 1");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
		//Remover Pedido B - Cliente 1
        pedidosDao = new GenericDao<>(Pedidos.class);
        
		try {
			System.out.println("Remover Pedido B - Cliente 1");
			pedidosDao.remover(pedidosDao.buscar(2));
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//Listar Pedidos após Alterar Pedido A(1) e Remover Pedido B(2)
		listaPedidos = pedidosDao.listar();
		System.out.println("Listando os Pedidos após alterar Descricao do Pedido A e Remover Pedido B");				
			            
        for (Pedidos pedidos_for: listaPedidos) {
        	System.out.println("Descricao: " + pedidos_for.getDescricao() + " - Valor: " + pedidos_for.getValor() );
        }

		
	}

}
