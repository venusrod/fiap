package br.com.fiap.aplicacao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class TesteJdbc {
	
	public static void main(String[] args) throws SQLException {

		//*******Controlando as Transações pelo JDBC
		
		//Teste para simular rollback caso algum erro aconteça durante uma transação 
		//com 2 inserts
		teste_JDBC_CommitRollback();
		
		//Teste para simular Batch Updates de transações
		teste_JDBC_BatchUpdates();
		
		//*******Cache
		
		//Teste para simular tempo de resposta do PreparedStatement
		teste_JDBC_CachePreparedStatement();
		
		//*******Concorrência - bloqueio e isolamento de transações
		
		//Teste para simular que esse tipo de transação, não permite alterar
		//registros sendo manipulados pela transação, mas permite incluir
		//novos registros, não bloqueando totalmente a tabela.

		teste_JDBC_Transaction_Repeatable_Read();
		
		teste_JDBC_Transaction_Read_Uncommitted();
		
	}
	
	public static void teste_JDBC_CommitRollback() throws SQLException {

		System.out.println("*****TESTE JDBC - Controle de Transação com AUTOCOMMIT / COMMIT / ROLLBACK");
        System.out.print("");
				
		Connection cn = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/trabjdbc";
			cn = DriverManager.getConnection(URL,"root","root");
						
			//Fazendo um INSERT DO PRODUTO A na tabela ESTOQUE
			//utilizando o AutoCommit default(True)
			
			String sql = "INSERT INTO ESTOQUE (NOME,QTDE) VALUES (?,?)";
			PreparedStatement stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "PRODUTO A");
			stmt.setLong(2, 500);
			stmt.execute();
			
			//Fazendo um INSERT utilizando o AutoCommit(False) para controlar uma transação
			//Nesse exemplo abaixo, vai realizar um RollBack, pois a tabela PRODUTO
			//não existe no banco de dados. 
			//Com isso, a transação toda será retornada ao original e o PRODUTO D
			//não será gravado na tabela ESTOQUE
			
			cn.setAutoCommit(false);
						
			sql = "INSERT INTO ESTOQUE (NOME,QTDE) VALUES (?,?)";
			stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "PRODUTO D");
			stmt.setLong(2, 50);
			stmt.execute();

			sql = "SELECT IDPRODUTO, NOME, QTDE FROM ESTOQUE";
            stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Consulta da tabela ESTOQUE antes do Rollback "
			            		+ " - Tem o PRODUTO A e o PRODUTO D:");
			
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("IDPRODUTO") + 
						           " - NOME PRODUTO EM ESTOQUE: " + rs.getString("NOME") + 
						           " - QTDE: " + rs.getString("QTDE"));
			}
			
			sql = "INSERT INTO PRODUTO (NOME) VALUES (?)";
			stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "PRODUTO E");
			stmt.execute();
			
			cn.commit();
			cn.setAutoCommit(true);
						
		}
		catch(SQLException e ) {
			cn.rollback();
			String sql = "SELECT IDPRODUTO, NOME, QTDE FROM ESTOQUE";
            PreparedStatement stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Consulta da tabela Estoque depois do Rollback"
					+ " - Contendo apenas o PRODUTO A que foi gravado fora da Transação:");
			
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("IDPRODUTO") + 
				           " - NOME PRODUTO EM ESTOQUE: " + rs.getString("NOME") + 
				           " - QTDE: " + rs.getString("QTDE"));

			}
	
			cn.close();
		}
		finally {
			cn.close();
		}
		
	}

	public static void teste_JDBC_BatchUpdates() throws SQLException {

        System.out.print("");
		System.out.println("*****TESTE JDBC - Controle de Transação com Batch Updates");
        System.out.print("");
				
		Connection cn = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/trabjdbc";
			cn = DriverManager.getConnection(URL,"root","root");

			cn.setAutoCommit(false);
						
			String sql = "INSERT INTO ESTOQUE (NOME,QTDE) VALUES (?,?)";
			PreparedStatement stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, "PRODUTO B");
			stmt.setLong(2, 100);
			stmt.addBatch();

			stmt.setString(1, "PRODUTO C");
			stmt.setLong(2, 200);
			stmt.addBatch();

			stmt.setString(1, "PRODUTO D");
			stmt.setLong(2, 300);
			stmt.addBatch();

			stmt.executeBatch();
			cn.commit();
			cn.setAutoCommit(true);
			
			sql = "SELECT IDPRODUTO, NOME, QTDE FROM ESTOQUE";
            stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Consulta da tabela após BATCH UPDATE ");
			
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("IDPRODUTO") + 
						           " - NOME PRODUTO EM ESTOQUE: " + rs.getString("NOME") + 
						           " - QTDE: " + rs.getString("QTDE"));
			}
						
		}
		catch(SQLException e ) {
			cn.rollback();
			cn.close();
		}
		finally {
			cn.close();
		}
		
	}

	public static void teste_JDBC_CachePreparedStatement() throws SQLException {

		System.out.println("*****TESTE JDBC - Cache - PreparedStatement");
        System.out.print("");
				
		Connection cn = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/trabjdbc";
			cn = DriverManager.getConnection(URL,"root","root");
			
			//Fazendo um INSERT DO PRODUTO A na tabela ESTOQUE
			//utilizando o AutoCommit e PreparedStatement

			cn.setAutoCommit(false);
			String sql = "INSERT INTO ESTOQUE (NOME,QTDE) VALUES (?,?)";
			PreparedStatement stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, "PRODUTO E");
			stmt.setLong(2, 1000);
			LocalDateTime inicio = LocalDateTime.now();
			stmt.execute();
			LocalDateTime fim = LocalDateTime.now();
			long milisegundos = ChronoUnit.MILLIS.between(inicio, fim);
			System.out.println("Total de Milisegundos para inserir Primeira Vez: " + milisegundos);

			stmt.setString(1, "PRODUTO F");
			stmt.setLong(2, 1100);
			inicio = LocalDateTime.now();
			stmt.execute();
			fim = LocalDateTime.now();
			milisegundos = ChronoUnit.MILLIS.between(inicio, fim);
			System.out.println("Total de Milisegundos para inserir Segunda Vez: " + milisegundos);
			
			stmt.setString(1, "PRODUTO G");
			stmt.setLong(2, 1200);
			inicio = LocalDateTime.now();
			stmt.execute();
			fim = LocalDateTime.now();
			milisegundos = ChronoUnit.MILLIS.between(inicio, fim);
			System.out.println("Total de Milisegundos para inserir Terceira Vez: " + milisegundos);

			cn.commit();
			cn.setAutoCommit(true);
			
			System.out.println("finalizou PreparedStatement");
		}
		catch(SQLException e ) {
			cn.rollback();
			cn.close();
		}
		finally {
			cn.close();
		}
		
	}
	
	public static void teste_JDBC_Transaction_Repeatable_Read() throws SQLException {

		System.out.println("*****TESTE JDBC - Concorrencia - TRANSACTION_REPEATABLE_READ");
        System.out.print("");
		
		Connection cn = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/trabjdbc";
			cn = DriverManager.getConnection(URL,"root","root");
			
			cn.setAutoCommit(false);
			cn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
						
			String sql = "INSERT INTO ESTOQUE (NOME,QTDE) VALUES (?,?)";
			PreparedStatement stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "PRODUTO H");
			stmt.setLong(2, 1500);
			stmt.execute();
			
			//antes de dar o OK na mensagem abaixo, entrar no MySQL workbench
			//e inserir um registro diretamente na base de dados do MySQL, com o comando
			//a seguir:
			//insert into estoque (nome, qtde)
			//values ("PRODUTO I", 100);
			//commit;
			
            //Resultado: permitiu inserir o PRODUTO I, pois era o PRODUTO H que
			//           estava alocado nessa transação
			
			//antes de dar o OK na mensagem abaixo, tentar realizar um UPDATE no 
			//Produto H pelo MySQL Workbench:
			//update estoque
			//set qtde = 200
			//where idproduto = 9; //Para IDPRODUTO, é um ID anterior ao ID do PRODUTO I (-1) 
			
			//Resultado: não permitiu fazer update no Registro 1, pois estava 
			//           alocado nessa conexão. Vai ficar travado o update até que 
			//           seja pressionado o OK na mensagem abaixo
			
			JOptionPane.showMessageDialog(null, "TRANSACTION_REPEATABLE_READ - ANTES DE PRESSIONAR OK, Inserir PRODUTO I e realizar update PRODUTO H pelo Workbench. INSERT e UPDATE estão disponiveis no Codigo Fonte");
			
			cn.commit();
			cn.setAutoCommit(true);

			
			
		}
		catch(SQLException e ) {
			cn.rollback();
		}
		finally {
			cn.close();
		}
		
	}

	public static void teste_JDBC_Transaction_Read_Uncommitted() throws SQLException {

		System.out.println("*****TESTE JDBC - Concorrencia - TRANSACTION_READ_UNCOMMITED");
        System.out.print("");

		
		Connection cn = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/trabjdbc";
			cn = DriverManager.getConnection(URL,"root","root");
			
			cn.setAutoCommit(false);
			cn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

			//Para esse exemplo funcionar, a sessão de conexão pelo Workbench do Mysql deve estar como:
			//SET SESSION autocommit = 0 (para que não faça o commit automaticamente pelo Workbench

			//Antes de pressionar OK na mensagem abaixo, entro no MySQl Workbench
			//e insira um registro, sem realizar o COMMIT
			//para comprovar que essa conexão vai pegar esse registro mesmo sem 
			//estar efetivado no banco de dados
			//insert into estoque (nome, qtde)
			//values ("PRODUTO J", 200);
			
			JOptionPane.showMessageDialog(null, "TRANSACTION_READ_UNCOMMITED - ANTES DE PRESSIONAR OK - Insira o PRODUTO J pelo banco de dados e nao dê o commit. Esse registro vai ser listado aqui mesmo sem commit");
			
			String sql = "SELECT IDPRODUTO, NOME, QTDE FROM ESTOQUE";
			PreparedStatement stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = stmt.executeQuery();

			System.out.println("Se foi realizado o INSERT pelo Workbench do PRODUTO J, ENTÃO O PRODUTO J aparecerá na lista abaixo, mesmo sem ter sido dado COMMIT");
			
			while (rs.next()) {
				System.out.println("ID: " + rs.getString("IDPRODUTO") + 
				           " - NOME PRODUTO EM ESTOQUE: " + rs.getString("NOME") + 
				           " - QTDE: " + rs.getString("QTDE"));
			}
			
			
			
			
			
			
			//cn.commit();
			cn.setAutoCommit(true);
						
		}
		catch(Exception e ) {

		}
		finally {
			cn.close();
		}
		
	}

	

}
