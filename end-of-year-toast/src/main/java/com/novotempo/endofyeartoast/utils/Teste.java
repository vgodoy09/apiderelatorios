package com.novotempo.endofyeartoast.utils;

import java.util.Arrays;
import java.util.List;

public class Teste {
	
	public static void main(String[] args) throws Exception {
//		File arquivo = new File("src/main/resources/static/image/chave.jpg");
//		Path path = new File("src/main/resources/static/image/chave.jpg").toPath();
//		Utils.createFileAppData("smb://10.21.0.201/mnt/app_data/DispatchLabel/barcodeteste", Files.readAllBytes(path));
		List<Integer> list2 = Arrays.asList(5, 6, 7);
	      int res = list2.parallelStream().reduce(0, (s1, s2) -> s1 + s2, (p, q) -> p + q);
	      System.out.println(res);
	}
	
//	public InputStream getInputStream() throws Exception {
//		return new FileInputStream(new File("chave.jpg"));
//	}
//	
//	public byte[] getBytesFromInputStream(InputStream is) throws Exception {
//	    ByteArrayOutputStream os = new ByteArrayOutputStream(); 
//	    byte[] buffer = new byte[0xFFFF];
//	    for (int len = is.read(buffer); len != -1; len = is.read(buffer)) { 
//	        os.write(buffer, 0, len);
//	    }
//	    return os.toByteArray();
//	}
}
