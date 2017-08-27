package fiap.mensagem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import fiap.criptografia.Criptografia;
import fiap.log.GravarLog;

public class LerMensagem {

	public static void main(String[] args) {
		lerMensagem();

		
	}

	static void lerMensagem() {
		String arquivo = JOptionPane.showInputDialog("Digite o Arquivo da Mensagem que deseja ler:");

        String mensagem = lerArquivo(arquivo);
        
		Criptografia criptografia = new Criptografia();
		String mensagemDecodificada = criptografia.decodificaMensagem(mensagem);
		
		JOptionPane.showMessageDialog(null, mensagemDecodificada, "Mensagem Decodificada", 1);
		
        GravarLog gravaLog = new GravarLog();
		gravaLog.gravarLog("Mensagem Decodificada", mensagem.length(), arquivo);
		
	}

    static String lerArquivo(String arquivo) {        
        String linha = null;
	    StringBuilder mensagem= new StringBuilder();
        try(
             FileReader fr= new FileReader(arquivo);
             BufferedReader br= new BufferedReader(fr);
        ){
             while((linha= br.readLine()) != null){
             mensagem = mensagem.append(linha);
             }
        } catch(IOException ex){
             ex.printStackTrace();
        }
	    return mensagem.toString();
	}	

	
}
