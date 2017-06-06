//INCLUSAO DE CARDAPIO DE RESTAURANTE
//EXEMPLO DOS DADOS QUE SERAO SOLICITADOS NA JOPTIONPANE:
//RESTAURANTE: ANDIAMO
//   CATEGORIA PRODUTO: MASSAS
//             PRODUTO: PENNE AO MOLHO BRANCO - VALOR: 10
//             PRODUTO: LASAGNA AO SUGO       - VALOR: 20
//   CATEGORIA PRODUTO: SOBREMESAS
//             PRODUTO: MOUSSE DE CHOCOLATE   - VALOR: 20
//             PRODUTO: SORVETE CREME         - VALOR: 10
//SERÃO SOLICITADOS APENAS 2 RESTAURANTES, 2 CATEGORIAS POR RESTAURANTE E 2 PRODUTOS POR CATEGORIA
//
//AO FINAL, SERÁ EXIBIDO O CARDAPIO DE TODOS OS DADOS INSERIDOS


package br.com.fiap.aplicacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.entity.Restaurante;
import br.com.fiap.entity.CategoriaProduto;
import br.com.fiap.entity.Produto;
import br.com.fiap.helper.RestauranteHelper;

import javax.swing.JOptionPane;



public class TesteRestauranteCardapio {
	
	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("jpaPU");
		EntityManager createEntityManager = emf.createEntityManager();

		Restaurante restaurante; 
        String nomeRestaurante;

		List<CategoriaProduto> listaCategoriaProduto;
		CategoriaProduto categoriaProduto = new CategoriaProduto();
		String nomeCategoriaProduto;
		List<Produto> listaProduto = new ArrayList<Produto>();
		String nomeProduto;
		Double valorProduto;

		
		//Permite inserir 2 Restaurantes , com 2 Categoria de Produto do Cardápio e 2 Produtos em cada Categoria

		//Loop para Inserir pela JOptionPane 2 Restaurantes
		for (int i=0; i<2; i++) {
			
			restaurante = new Restaurante();			
	        nomeRestaurante = JOptionPane.showInputDialog("Informe o Nome do Restaurante");
			restaurante.setNomeRestaurante(nomeRestaurante);

			listaCategoriaProduto = new ArrayList<CategoriaProduto>();

			// Loop para Inserir pela JOptionPane 2 Categorias de Produtos por Restaurante
			for (int x=0; x<2; x++) {
			    	
				categoriaProduto = new CategoriaProduto();
		        nomeCategoriaProduto = JOptionPane.showInputDialog("Informe a Categoria do Produto para o Restaurante: " + nomeRestaurante);
				categoriaProduto.setNomeCategoriaProduto(nomeCategoriaProduto);
				categoriaProduto.setRestaurante(restaurante);
				listaCategoriaProduto.add(categoriaProduto);

				listaProduto = new ArrayList<Produto>();
				
				//Loop para Inserir pela JOptionPane 2 Produtos por Categoria
				for (int z=0; z<2; z++) {

					nomeProduto = JOptionPane.showInputDialog("Informe o Produto da Categoria " + categoriaProduto.getNomeCategoriaProduto() + " do Restaurante: "  + nomeRestaurante);
					valorProduto = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do Produto " + nomeProduto));
					Produto produto = new Produto();
					produto.setNomeProduto(nomeProduto);
					produto.setCategoriaProduto(categoriaProduto);
					produto.setValorProduto(valorProduto);
					listaProduto.add(produto);
					
				}

				categoriaProduto.setListaProduto(listaProduto);

			}

			restaurante.setListaCategoriaProduto(listaCategoriaProduto);

			RestauranteHelper dao = new RestauranteHelper(createEntityManager);
			
			try {
				dao.salvar(restaurante);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}

			
			
		}
	
		System.out.println("Termino da Inclusao do Cardapio dos Restaurantes");
		
		listarCardapio(createEntityManager);
		//buscarFuncionario(createEntityManager, MATRICULA);
	}
	
	
    
	
	private static void listarCardapio(EntityManager em){
		RestauranteHelper dao = new RestauranteHelper(em);
		List<Restaurante> restaurante = dao.listarRestaurante();
		
		for (Restaurante restaurante_for: restaurante) {
			System.out.println();
			System.out.println("CARDAPIO DO RESTAURANTE: " + restaurante_for.getNomeRestaurante() );
			List<CategoriaProduto> categoriaProduto = dao.listarCategoriaProduto(restaurante_for);

			for (CategoriaProduto categoria_for: categoriaProduto) {
	            System.out.println("         " + categoria_for.getNomeCategoriaProduto() );
	            
	    		List<Produto> produto = dao.listarProduto(categoria_for);	            
	            for (Produto produto_for: produto) {
	            	System.out.println("           " + produto_for.getNomeProduto() + " - Valor: " + produto_for.getValorProduto() );
	            }
	            
			}
			
		}
		
	}
	
}