//ROTINA SOLICITA VIA JOPTIONPANE 2CLIENTES COM 2 PEDIDOS PARA CADA CLIENTE
//EXIBE UMA CONSULTA DE TODOS ESSES CLIENTES E PEDIDOS
//SOLICITA O ID DE UM CLIENTE PARA FAZER UMA BUSCA ESPECIFICA (SÓ VÃO EXISTIR ID=1 OU ID=2)
//EXIBE A CONSULTA DO CLIENTE SELECIONADO E TODOS OS SEUS PEDIDOS
//OBS: Validações de e-mail/valor numérico/etc não foram consideradas, por não serem escopo da atividade

package br.com.fiap.aplicacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.entity.Clientes;
import br.com.fiap.entity.Pedidos;
import br.com.fiap.helper.Helper;

import javax.swing.JOptionPane;

public class TesteAplicacao {
	
	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("jpaPU");
		EntityManager createEntityManager = emf.createEntityManager();

		System.out.println("Iniciando...");
		
		Clientes clientes; 
        String nome;
        String eMail;

		List<Pedidos> listaPedidos;
		Pedidos pedidos = new Pedidos();
		String descricao;
		Date data = new Date();
		double valor;

		// Loop para inserir pela JOptionPane apenas 2 Clientes
		for (int i=0; i<2; i++) {
			
			clientes = new Clientes();			
	        nome = JOptionPane.showInputDialog("Informe o Nome do Cliente");
	        eMail = JOptionPane.showInputDialog("Informe o EMail do Cliente");
			clientes.setNome(nome);
			clientes.seteMail(eMail);

			listaPedidos = new ArrayList<Pedidos>();

			// Loop para Inserir pela JOptionPane 2 Pedidos para cada Cliente
			for (int x=0; x<2; x++) {
			    	
				pedidos = new Pedidos();
		        descricao = JOptionPane.showInputDialog("Informe a Descricao do Pedido para o Cliente: " + nome);
                valor     = Double.parseDouble(JOptionPane.showInputDialog("Informe o Valor do Pedido: " + descricao));
		        pedidos.setDescricao(descricao);
		        pedidos.setValor(valor);
                pedidos.setData(data);
		        
				pedidos.setClientes(clientes);
				listaPedidos.add(pedidos);

			}

			clientes.setListaPedidos(listaPedidos);

			Helper dao = new Helper(createEntityManager);
			
			try {
				dao.salvar(clientes);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}

			
			
		}
	
		System.out.println("Termino da Inclusao dos Clientes e Pedidos");
		
		System.out.println("Iniciar Consulta de Todos Clientes e Pedidos");
		
		listarClientesPedidos(createEntityManager);
		
		System.out.println("Iniciar Consulta de um Cliente");

        Integer idClientes = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do Cliente para Consulta"));
                
        buscarClientePedidos(createEntityManager, idClientes);
	}
	
		
	private static void listarClientesPedidos(EntityManager em){
		Helper dao = new Helper(em);
		List<Clientes> clientes = dao.listarClientes();
		
		for (Clientes clientes_for: clientes) {
			System.out.println();
			System.out.println("CLIENTE: " + clientes_for.getNome() + " - EMail: " + clientes_for.geteMail());
			List<Pedidos> pedidos = dao.listarPedidos(clientes_for);

			for (Pedidos pedidos_for: pedidos) {
	            System.out.println("         " + pedidos_for.getDescricao() + " - Valor: " +  pedidos_for.getValor() + " - Data: " + pedidos_for.getData());
			}
			
		}
	
	}
	
	 private static void buscarClientePedidos(EntityManager em, Integer idClientes) {
		 Helper dao = new Helper(em);
         Clientes clientes = dao.buscarCliente(idClientes);
       	 System.out.println();
		 System.out.println("CLIENTE: " + clientes.getNome() + " - EMail: " + clientes.geteMail());
		 List<Pedidos> pedidos = dao.listarPedidos(clientes);

		 for (Pedidos pedidos_for: pedidos) {
	            System.out.println("         " + pedidos_for.getDescricao() + " - Valor: " +  pedidos_for.getValor() + " - Data: " + pedidos_for.getData());
		 }
    	 
    	 
	 }
	
	
}