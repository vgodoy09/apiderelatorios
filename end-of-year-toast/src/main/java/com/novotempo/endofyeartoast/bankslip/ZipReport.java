package com.novotempo.endofyeartoast.bankslip;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.novotempo.endofyeartoast.model.WrappedToast;
import com.novotempo.endofyeartoast.services.donation.DonationService;

public class ZipReport {
	
	
	public static HttpHeaders getHearder(String outputFilename, String mediaType) {
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.parseMediaType(mediaType));
		 headers.setContentDispositionFormData(outputFilename, outputFilename);
		 headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		 return headers;
	}
	
	
	public static WrappedToast createZip(String products, DonationService donationService, Integer userId, String nameLabel) throws Exception {
		Map<Integer, String> lots = getListLotZipCode();
		donationService.insertIntoTableTempToast(products);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try(ZipOutputStream zip = new ZipOutputStream(bos)) {
			lots.entrySet().stream().map(i -> getZip(i.getKey(), i.getValue(), zip)).count();
			zip.closeEntry();
		}
		Integer batchLabelPrint_id = donationService.finishPickingToast(products, userId, nameLabel);
		return new WrappedToast(bos.toByteArray(), batchLabelPrint_id);
		
	}
	
	public File createZipFileTemp(String products) throws Exception {
		Map<Integer, String> lots = getListLotZipCode();
		
		File tempZipFile =
		        File.createTempFile("test-data" + LocalDate.now(), ".zip");
		    tempZipFile.deleteOnExit();
		
		try(FileOutputStream fos = new FileOutputStream(tempZipFile); ZipOutputStream zip = new ZipOutputStream(fos)) {
			lots.entrySet().stream().map(i -> getZip(i.getKey(), i.getValue(), zip)).count();
			zip.closeEntry();
		}
		return tempZipFile;
		
	}
	
	private static ZipOutputStream getZip(Integer key, String fileName, ZipOutputStream zip) {
		try {
			zip.putNextEntry(new ZipEntry(fileName));
			zip.putNextEntry(new ZipEntry(fileName + "lote " + key + " etiqueta.pdf"));
			zip.write(new DispatchLabelReport(DispatchLabelReport.MODEL_PT_86X42, SqlReport.sqlDispatchLabelToast(key+""), true ).dispatchLabelReport());
			zip.putNextEntry(new ZipEntry(fileName + "lote " + key + " boleto.pdf"));
			zip.write(new BankSlipReport(BankSlipReport.MODEL_DONATION, SqlReport.sqlBankSlipToast(key+"")).bankSlipReport());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.err.println("Relatorio :" + key);
		return zip;
	}
	
	private static Map<Integer, String> getListLotZipCode() {
		Map<Integer, String> map = new HashMap<>();
		map.put(0, "lote 0 - internacional/");
		map.put(1, "lote 1 - cep de 01000000 ate 09999999/");
		map.put(2, "lote 2 - cep de 12300000 ate 12349999/");
		map.put(3, "lote 3 - cep de 12200000 ate 12899999/");
		map.put(4, "lote 4 - cep de 18000000 ate 18599999/");
		map.put(5, "lote 5 - cep de 18600000 ate 18999999/");
		map.put(6, "lote 6 - cep de 13000000 ate 13999999/");
		map.put(7, "lote 7 - cep de 14000000 ate 14999999/");
		map.put(8, "lote 8 - cep de 15000000 ate 15999999/");
		map.put(9, "lote 9 - cep de 16000000 ate 16999999/");
		map.put(10, "lote 10 - cep de 17000000 ate 17999999/");
		map.put(11, "lote 11 - cep de 19000000 ate 19999999/");
		map.put(12, "lote 12 - cep de 20000000 ate 59999999/");
		map.put(13, "lote 13 - cep de 60000000 ate 99999999/");
		map.put(14, "lote 14 - cep de 10000000 ate 12199999/");
		map.put(15, "lote 15 - cep de 12900000 ate 12999999/");
		return map;
	}
}
