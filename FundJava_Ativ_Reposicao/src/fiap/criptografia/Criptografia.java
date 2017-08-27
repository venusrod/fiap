package fiap.criptografia;

public class Criptografia {

	public static String codificaMensagem(String mensagem) {
		
		StringBuilder mensagemCodificada = new StringBuilder();
		int codigo;
		char caracter;
		char caracterCodificado;
		
		String vogalA = "Aa¡·¿‡¬„¬‚";
		String vogalE = "Ee…È»Ë Í";
		String vogalI = "IiÌÕÓŒ";
		String vogalO = "Oo”Û‘Ù’ı";
		String vogalU = "Uu⁄˙€˚";
				
		String consoante = "bcÁdfghjklmnpqrstvxwyzBC«DFGHJKLMNPQRSTVWXYZ";
		
		int numeroLetras = mensagem.length();
		
		for (int i=0; i<numeroLetras; i++) {
			caracter = mensagem.charAt(i);
			codigo = (int) caracter;
 		    
   		    if(vogalA.indexOf(mensagem.substring(i,i+1)) >= 0) {
   	            mensagemCodificada = mensagemCodificada.append("1");   		    	
   		    }	
   		    else if(vogalE.indexOf(mensagem.substring(i,i+1)) >= 0) {
   	            mensagemCodificada = mensagemCodificada.append("2");
   		    }
   		    else if(vogalI.indexOf(mensagem.substring(i,i+1)) >= 0) {
   	            mensagemCodificada = mensagemCodificada.append("3");
   		    }	
   		    else if(vogalO.indexOf(mensagem.substring(i,i+1)) >= 0) {
   	            mensagemCodificada = mensagemCodificada.append("4");
   		    }	
   		    else if(vogalU.indexOf(mensagem.substring(i,i+1)) >= 0) {
   	            mensagemCodificada = mensagemCodificada.append("5");
   		    }
            else if(consoante.indexOf(mensagem.substring(i,i+1)) >= 0) {
        	    codigo = codigo + 6;
        	    caracterCodificado = (char) codigo;        	    
                mensagemCodificada = mensagemCodificada.append(caracterCodificado);        	    
            }
            else {
               codigo = codigo + 6;            	
               caracterCodificado = (char) codigo;  
               mensagemCodificada = mensagemCodificada.append(caracterCodificado);               
            }
            
		}
		
		return mensagemCodificada.toString();		
	}

	
	public static String decodificaMensagem(String mensagem) {
		
		StringBuilder mensagemDecodificada = new StringBuilder();
		int codigo;
		char caracter;
		char caracterCodificado;
	
		int numeroLetras = mensagem.length();
		
		for (int i=0; i<numeroLetras; i++) {
			caracter = mensagem.charAt(i);
			codigo = (int) caracter;

			String letra = mensagem.substring(i,i+1);
			
			if (letra.equals("1")) {
				mensagemDecodificada = mensagemDecodificada.append("a");
			}
			else if (letra.equals("2")) {
				mensagemDecodificada = mensagemDecodificada.append("e");
			}
			else if (letra.equals("3")) {
				mensagemDecodificada = mensagemDecodificada.append("i");
			}
			else if (letra.equals("4")) {
				mensagemDecodificada = mensagemDecodificada.append("o");
			}
			else if (letra.equals("5")) {
				mensagemDecodificada = mensagemDecodificada.append("u");
			}
			else {
				codigo = codigo - 6;
        	    caracterCodificado = (char) codigo;        	    
                mensagemDecodificada = mensagemDecodificada.append(caracterCodificado);        	    
			}
            
		}
		
		return mensagemDecodificada.toString();		
	}

	
	
}
