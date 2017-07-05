ReadMe

Para iniciar os testes da parte de JDBC:

. Execute o arquivo CreateTable.sql no MySql Workbench
. A senha e user configurados em cada método é root/root (Se for utilizar outra, esta informação está em cada método da classe TesteJava
. O Projeto é o Persistencia_JDBC
. A Classe Main que executa os testes JDBC é a TesteJDBC
. Foi implementado na Classe TesteJDBC:
	. teste_JDBC_CommitRollback();  //TRANSACAO
				//Teste para simular rollback caso algum erro aconteça durante uma transação 
          		//com 2 inserts
	
	. teste_JDBC_BatchUpdates();  //TRANSACAO EM LOTE
				//Teste para simular Batch Updates de transações
				
	. teste_JDBC_CachePreparedStatement();  //CACHE
				//Teste para simular tempo de resposta do PreparedStatement, que fica no cache do banco depois da primeira execução
	
	. teste_JDBC_Transaction_Repeatable_Read();  //CONCORRENCIA
				//Teste para simular a concorrência de transações através do SetTransactionIsolation

				Para esse teste de CONCORRÊNCIA, o programa vai dar um pause aguardando um OK do usuário (através do JOptionPane).
				Antes de pressionar OK, insira o registro e faça update através do MySql Workbench, conforme orientado no código fonte do Método, 
				para simular uma concorrência de transação.
				
	. teste_JDBC_Transaction_Read_Uncommitted();  //CONCORRENCIA
				//Teste para simular a concorrência de transações através do SetTransactionIsolation
				Antes de pressionar OK, insira o registro através do MySql Workbench, conforme orientado no código fonte do Método, 
				para simular uma concorrência de transação.


