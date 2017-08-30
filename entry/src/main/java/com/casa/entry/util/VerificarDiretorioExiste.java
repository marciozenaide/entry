package com.casa.entry.util;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class VerificarDiretorioExiste {
	private static final String DIR_NAME = "PROCESSADOS";
	private String caminho;
	private File file;
	
	public VerificarDiretorioExiste() throws IOException{
		caminho ="C:\\java";
		file = new File(caminho + "\\" + DIR_NAME);
		//System.out.println("Construtor >>>>>>>Dir existe?"+caminho);
	}
		
	public Boolean dirExiste(){
		Boolean retorno = Boolean.FALSE;
		
		if (!file.exists()){
			retorno = file.mkdir();
		}else{
			retorno = Boolean.TRUE;
		}
		return retorno;
	}
}
