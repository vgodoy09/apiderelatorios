package com.novotempo.endofyeartoast.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;


public class NTFileWriter {

	private String charset = null;

	public NTFileWriter() {
	}

	public NTFileWriter(String charset) {
		this.charset = charset;
	}

	public File write(String path, byte[] bytes) throws IOException {
		File file = new File(path);
		Reader inputStreamReader = null;
		Writer outputStreamWriter = null;
		FileOutputStream fileOutputStream = null;
		ByteArrayInputStream byteArrayInputStream = null;
		try {
			if(file.getParentFile() != null) {
				file.getParentFile().mkdirs();
			}

			if (charset == null || charset.isEmpty()) {
				fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(bytes);
				fileOutputStream.flush();
			} else {
				byteArrayInputStream = new ByteArrayInputStream(bytes);
				inputStreamReader = new InputStreamReader(byteArrayInputStream, Charset.defaultCharset());
				StringBuilder stringBuilder = new StringBuilder();
				
				int read = 0;
				while((read = inputStreamReader.read()) != -1) {
					stringBuilder.append((char)read);
				}
				
				char[] chars = new char[stringBuilder.length()];
				stringBuilder.getChars(0, stringBuilder.length(), chars, 0);
				
				outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), Charset.forName(charset));
				outputStreamWriter.write(chars);
				outputStreamWriter.flush();
			}

		} finally {
			try {
				if(inputStreamReader != null) 	inputStreamReader.close();
				if(byteArrayInputStream != null) byteArrayInputStream.close();
				if(outputStreamWriter != null) 	outputStreamWriter.close();
				if(fileOutputStream != null) 	fileOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public File write(String path, byte[] bytes, Reader reader) throws IOException{
		OutputStream outputStream = null;
		InputStream inputStream = null;
		File file = null;
		try {
			file = new File(path);
			if(file.getParentFile() != null) {
				file.getParentFile().mkdirs();
			}
			
			if (charset == null || charset.isEmpty()) {
				charset = "UTF-8";
			}
			
			outputStream = new FileOutputStream(path);
			inputStream = new ReaderInputStream(reader,charset);
			for (int count; (count = inputStream.read(bytes)) != -1;) {
				outputStream.write(bytes, 0, count);
			}
			
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(inputStream != null) inputStream.close();
				if(outputStream != null) outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

}