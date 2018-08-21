package com.novotempo.endofyeartoast.bankslip;

import static com.novotempo.endofyeartoast.utils.CheckInstanceObject.IsNullOrIsEmpty;
import static com.novotempo.endofyeartoast.utils.Utils.getPath;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.lowagie.text.pdf.PdfPageEventHelper;
import com.novotempo.endofyeartoast.utils.Connect;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRCompiler;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@SuppressWarnings("rawtypes")
public class NTJasperReport extends PdfPageEventHelper {


	private Map<String,Object> parameters 			= new HashMap<String,Object>(); 
	private String connectionName 	= "";
	private boolean isOficial       = false;
	private String type				= "";
	private String jasper			= "";

	public final static String PDF	= "pdf";
	public final static String HTML	= "html";

	public final static String PARAMETER_LOGO = "diretorioLOGO";
	public final static String LOGO_NAME 	   = "logo_NT.jpg";
	private final static String PARAMETER_FUNDO = "diretorioFUNDO";
	private final static String PARAMETER_KEY = "chave";
	private final static String FUNDO_NAME = "bg_report_NT.jpg";
	private final static String KEY_NAME = "chave.jpg";

	public NTJasperReport(String connectionName, boolean isOficial) {
		if(IsNullOrIsEmpty(connectionName))
			throw new RuntimeException("Conexão não pode ser null nem vazia.");
		this.connectionName = connectionName;
		this.isOficial = isOficial;
	}

	public byte[] toReport(String type) {
		byte[] result =  null;
		Connect connect = new Connect();
		Connection conn = connect.getConnection(connectionName, isOficial);

		if (parameters == null)
			throw new RuntimeException("Parametros não enviados");
	
		if (jasper == null)
			throw new RuntimeException("Nome do arquivo não enviado");
		
		parameters.put(PARAMETER_LOGO, getPath("image/"+LOGO_NAME));
		parameters.put(PARAMETER_FUNDO, getPath("image/"+FUNDO_NAME));
		parameters.put(PARAMETER_KEY, getPath("image/"+KEY_NAME));
		parameters.put(JRParameter.REPORT_LOCALE, new Locale("pt", "br"));
		
//		ReportExecutionStatus.
		
		JRPropertiesUtil instance = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance());
		String classpath = instance.getProperty(JRCompiler.COMPILER_CLASSPATH);
		instance.setProperty(JRCompiler.COMPILER_CLASSPATH, classpath);
		System.err.println("Classpath: " + classpath);

		try {
			JasperDesign jasperDesign = JRXmlLoader.load(getPath("reports/"+jasper));
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

			if (type.equals(PDF))
				result = JasperExportManager.exportReportToPdf ( print );
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
			
		connect.release(conn);

		return result;
	}
	
	
	
	public byte[] toByte() throws Exception {
		return toReport(type);
	}
	

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map<String,Object> parameters) {
		this.parameters = parameters;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJrxml() {
		return jasper;
	}

	public void setJrxml(String jrxml) {
		this.jasper = jrxml;
	}
}