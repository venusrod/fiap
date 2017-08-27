package fiap.mensagem;

import java.io.IOException;
import java.io.PrintWriter;

public class GravarMensagem {

	public static void gravarMensagem(String mensagem, String arquivo) {
    	try(  PrintWriter out= new PrintWriter(arquivo)
		   ) {
			 out.println(mensagem);
        } catch(IOException ex){
             ex.printStackTrace();
        }
	}	

}
