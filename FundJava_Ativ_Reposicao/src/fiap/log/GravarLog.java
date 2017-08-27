package fiap.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GravarLog {

    public static void gravarLog(String tipoOperacao, int tamanhoMensagem, String arquivo) {
    	StringBuilder log= new StringBuilder();
    	LocalDateTime data = LocalDateTime.now() ; 
    	DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        String resultadoData = data.format(formatador);

        File f = new File(arquivo);
        long tamanhoArquivo = f.length();
        
    	log = log.append(resultadoData + ";" + tipoOperacao + ";" + tamanhoMensagem + ";" + arquivo + ";" + tamanhoArquivo + ";");

    	try {
			BufferedWriter arq = new BufferedWriter(new FileWriter("arquivo_controle.txt", true));
			arq.append(log + "\n");
	        arq.close();
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	System.out.println("Log gravado em arquivo_controle.txt: " + log);
        
    }

	
}
