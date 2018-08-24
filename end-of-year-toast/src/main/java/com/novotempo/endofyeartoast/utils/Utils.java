package com.novotempo.endofyeartoast.utils;

import java.io.File;

import javax.swing.text.MaskFormatter;

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
	
	public static String getPathImage(String image) {
		String separator = System.getProperty("file.separator");
		String result = "\\\\10.21.8.2\\app_data" + separator+separator+"Boletos"+separator+separator+"Imagens"+separator+separator+"logo_NT.jpg";
		return (!System.getProperty("os.name").contains("Windows")) ? image : result;
	}
	
	public static String maskFormatter(String text, String mask) throws Exception {
		if(text != null && !text.equals("") ){
			MaskFormatter mf = new MaskFormatter(mask);
			mf.setValueContainsLiteralCharacters(false);
			text = mf.valueToString(text.replace(".", "").replace("-", "").replace("/", "")).replace(" ", "").trim();
		}
		return text;
	}	
	
	
}
