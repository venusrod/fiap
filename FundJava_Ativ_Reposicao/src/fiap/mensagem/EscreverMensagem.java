package fiap.mensagem;

import javax.swing.JOptionPane;

import fiap.criptografia.Criptografia;
import fiap.log.GravarLog;

public class EscreverMensagem {

	public static void main(String[] args) {
		escreverMensagem();
	}

	static void escreverMensagem() 	{
		String mensagem = JOptionPane.showInputDialog("Digite a Mensagem que deseja ESCREVER:");
		String arquivo = JOptionPane.showInputDialog("Informe o nome do Arquivo onde gravar sua mensagem:");
		System.out.println("CODIFICAR mensagem: " + mensagem);
		System.out.println("Gravar Mensagem no Arquivo: " + arquivo);

		Criptografia criptografia = new Criptografia();
		String mensagemCodificada = criptografia.codificaMensagem(mensagem);
		System.out.println("Mensagem Codificada: " + mensagemCodificada);

		GravarMensagem gravaMsg = new GravarMensagem();
		gravaMsg.gravarMensagem(mensagemCodificada, arquivo);

        GravarLog gravaLog = new GravarLog();
		gravaLog.gravarLog("Mensagem Codificada", mensagem.length(), arquivo);
	}
}
