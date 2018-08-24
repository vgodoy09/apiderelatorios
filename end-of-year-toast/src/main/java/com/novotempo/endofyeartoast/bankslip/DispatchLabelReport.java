package com.novotempo.endofyeartoast.bankslip;

import static com.novotempo.endofyeartoast.utils.CheckInstanceObject.IsNullOrIsEmpty;
import static com.novotempo.endofyeartoast.utils.Utils.getPath;

import java.util.HashMap;
import java.util.Map;

public class DispatchLabelReport {
	
	private String model;
	private String sql;
//	private final String PDF = ".pdf";
//	private final String HTML = ".html";
	private final String db = "Donation";
	private final boolean isOficial = false;
	private boolean isToast = false;
	
	public final static String MODEL_PT 				= "dispatch/dispatchPrint.jrxml";
	public final static String MODEL_PT_86X42	 		= "dispatch/dispatchPrint_86x42mm.jrxml";
	public final static String MODEL_ESP 				= "dispatch/dispatchPrintESP.jrxml";
	public final static String MODEL_ESPBOL				= "dispatch/dispatchPrintESPBOL.jrxml";
	public final static String MODEL_ESPURU				= "dispatch/dispatchPrintESPURU.jrxml";
	public final static String MODEL_ESPPY				= "dispatch/dispatchPrintESPPY.jrxml";
	public final static String MODEL_ESPCH				= "dispatch/dispatchPrintESPCH.jrxml";
	public final static String MODEL_ESPECU				= "dispatch/dispatchPrintESPECU.jrxml";
	public final static String MODEL_ESPPE				= "dispatch/dispatchPrintESPPE.jrxml";
	private final static String PARAMETER_LOGO 			= "diretorioLOGO";
//	private final static String PARAMETER_LOGO_ULC 		= "diretorioLOGOULC";
	private final static String PARAMETER_DEVOLUCAO 	= "diretorioDevolucao";
	private final static String PARAMETER_MALADIRETA 	= "diretorioMalaDireta";
	private final static String PARAMETER_LOGO_CORREIOS = "logoCorreios";
	private final static String PARAMETER_LOGO_NAME 	= "logoName";
	public final static String LOGO_NAME 	   			= "logo_NT.jpg";
	private final static String LOGO_NAME_DONATION		= "logos_nt_donation.jpg";
//	private final static String LOGO_NAME_ESP  			= "logo_NT_ESP_New.png";
//	private final static String LOGO_NAME_ULC	   		= "etiqueta_ulc-nt.jpg";
	private final static String IMG_DEVOLUCAO			= "devolucao.png";
	private final static String IMG_MALADIRETA			= "malaDireta.png";
	private final static String IMG_LOGO_CORREIOS		= "logo_correios_gradient.png";

	public DispatchLabelReport(String model, String sql) {
		this.model = model;
		this.sql = sql;
	}
	public DispatchLabelReport(String model, String sql, boolean isToast) {
		this.model = model;
		this.sql = sql;
		this.isToast = isToast;
	}
	
	public byte[] dispatchLabelReport() {
		if(IsNullOrIsEmpty(model) || IsNullOrIsEmpty(sql))
			return new byte[1];
		NTJasperReport report = new NTJasperReport(db, isOficial);
		report.setJrxml( model);
		report.setType(NTJasperReport.PDF);
		report.setParameters(getParameters());
		byte[] byte1;
		try {
			byte1 = report.toByte();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return byte1;
	}

	private Map<String, Object> getParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(isToast) 
			parameters.put(PARAMETER_LOGO, 		getPath("image/"+LOGO_NAME));
		else
			parameters.put(PARAMETER_LOGO, 		getPath("image/dispatch/"+LOGO_NAME_DONATION));
		parameters.put(PARAMETER_DEVOLUCAO, 	getPath("image/dispatch/"+IMG_DEVOLUCAO));
		parameters.put(PARAMETER_MALADIRETA,	getPath("image/dispatch/"+IMG_MALADIRETA));
		parameters.put(PARAMETER_LOGO_CORREIOS,	getPath("image/dispatch/"+IMG_LOGO_CORREIOS));
		parameters.put(PARAMETER_LOGO_NAME,	getPath("image/"+DispatchLabelReport.LOGO_NAME));
		parameters.put("sql", sql);
		return parameters;
	}
}
