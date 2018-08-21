package com.novotempo.endofyeartoast.bankslip;

import static com.novotempo.endofyeartoast.utils.CheckInstanceObject.IsNullOrIsEmpty;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BankSlipReport {
	
	private String model;
	private String sql;
	private String sysConfigGetArticleFilePath;
//	private final String PDF = ".pdf";
//	private final String HTML = ".html";
	private final String db = "Donation";
	private final boolean isOficial = false;
	
	public static final String IMAGE_FIRST  = "first.jpg";
	public static final String IMAGE_SECOND = "second.jpg";
	public static final String IMAGE_THIRD  = "third.jpg";
	public static final String IMAGE_FIRST_CARNET  = "first_carnet.jpg";
	public static final String IMAGE_SECOND_CARNET = "second_carnet.jpg";
	
	public static final String MODEL_DONATION = "bankSlipProposal.jrxml";
	public static final String MODEL_DONATION_BACK = "bankSlipFrontAndBankProposal.jrxml";
	public static final String MODEL_DONATION_CARNET_NEW = "bankSlipCarnet.jrxml";
	public static final String MODEL_DONATION_CARNET_NEW_EMAIL = "bankSlipCarnetEmail.jrxml"; 

	public BankSlipReport(String model, String sql) {
		this.model = model;
		this.sql = sql;
	}
	
	public BankSlipReport(String model, String sql, String sysConfigGetArticleFilePath) {
		this.model = model;
		this.sql = sql;
		this.sysConfigGetArticleFilePath = sysConfigGetArticleFilePath;
	}
	
	public byte[] bankSlipReport() throws Exception {
		if(IsNullOrIsEmpty(model) || IsNullOrIsEmpty(sql))
			return new byte[1];
		NTJasperReport report = new NTJasperReport(db, isOficial);
		report.setJrxml("donation/" + model);
		report.setType(NTJasperReport.PDF);
		report.setParameters(getParameters());
		return report.toByte();
	}
	
//	/**
//	 * @deprecate o metodo nao esta completo precisa terminar a implementa��o do boleto em html esta saindo sem as imagens 
//	 * estudar os links 
//	 * 1 - http://www.guj.com.br/t/jasperreports-exportar-para-html/146099/7,
//	 * 2 - https://github.com/caelum/caelum-stella/blob/master/stella-boleto/src/main/java/br/com/caelum/stella/boleto/transformer/GeradorDeBoletoHTML.java,
//	 * 3 - https://www.programcreek.com/java-api-examples/index.php?api=net.sf.jasperreports.engine.export.JRHtmlExporterParameter
//	 * 4 - http://jasperreports.sourceforge.net/api/net/sf/jasperreports/engine/export/JRHtmlExporterParameter.html
//	 * 5 - https://www.programcreek.com/java-api-examples/?class=net.sf.jasperreports.engine.export.JRHtmlExporter&method=exportReport
//	 * 6 - https://stackoverflow.com/questions/22906188/convert-pdf-to-html-file-java-api
//	 * 7 - https://www.codeproject.com/Questions/321487/Convert-a-pdf-file-to-html-in-Java
//	 * 8 - https://sourceforge.net/projects/cssbox/files/Pdf2DOM/
//	 * para terminar a implementa��o
//	 */
//	@Deprecated
//	public byte[] bankSlipHtml() {
//		if(IsNullOrIsEmpty(model) || IsNullOrIsEmpty(sql))
//			return new byte[1];
//		NTJasperReport report = new NTJasperReport(db);
//		report.setJrxml("donation/" + model);
//		report.setType(NTJasperReport.HTML);
//		report.setParameters(getParameters());
//		return report.toByte();
//	}
//	
//	@Deprecated
//	public Object[] bankSlipHtmlAndPdf() {
//		if(IsNullOrIsEmpty(model) || IsNullOrIsEmpty(sql))
//			return null;
//		NTJasperReport report = new NTJasperReport(db);
//		report.setJrxml("donation/" + model);
//		report.setType(NTJasperReport.HTML);
//		report.setParameters(getParameters());
//		return report.toObject();
//	}

	private Map<String, Object> getParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("sql", sql);
		if (MODEL_DONATION_BACK.equals(model)) {
			parameters.put("propaganda", getImageBoleto(IMAGE_FIRST));
			parameters.put("anjosA", getImageBoleto(IMAGE_SECOND));
			parameters.put("anjosB", getImageBoleto(IMAGE_THIRD));
		}
		
		if(MODEL_DONATION_CARNET_NEW.equals(model)){
			parameters.put("image_first", getImageBoleto(IMAGE_FIRST_CARNET));
			parameters.put("image_second", getImageBoleto(IMAGE_SECOND_CARNET));
		}
		return parameters;
	}
	

	private String getImageBoleto(String position){
		return getImageBoleto(position, false);
	}
	
	private String getImageBoleto(String position, boolean create){
		String result = "";
		
		String path = getPropagandaPath();
		
		String separator = System.getProperty("file.separator");
		
		String companyDir = "2"+separator+separator;;
		
		result = path+companyDir+position;
		
		File dir = new File(result);
		
		if (!dir.exists()){
			if (create){
				try {
					
					new File(path+companyDir).mkdirs();
					dir.createNewFile();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				result = path+"empty"+(position.substring(0,1).toUpperCase()+position.substring(1));
			}
		}
		return result;
	}
	
	private String getPropagandaPath(){
		String separator = System.getProperty("file.separator");
		String path = sysConfigGetArticleFilePath+separator+separator+"Boletos"+separator+separator+"Imagens"+separator+separator;
		return path;
	}

}
