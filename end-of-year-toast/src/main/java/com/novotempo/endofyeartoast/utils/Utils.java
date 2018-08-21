package com.novotempo.endofyeartoast.utils;

import java.io.File;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class Utils {
	
	public static void createFileAppData(String filePath, byte[] data) throws Exception {
		System.setProperty("jcifs.smb.client.username", "");
		System.setProperty("jcifs.smb.client.password", "");
		SmbFile f = new SmbFile(filePath);
		//Abre o arquivo
		SmbFileOutputStream out = new SmbFileOutputStream(f);
		//Escreve o conteúdo do arquivo
		out.write(data);
		//Fecha o arquivo e o libera para ser usado por outra aplicação
		out.close();
	}
	
	
	public static String getPath(String name) {
		return new File("src/main/resources/static/" + name).getAbsolutePath();
	}
	
	
}
