package com.novotempo.endofyeartoast.model;

public class BankSlip {
	private String sql;
	private Integer type; //se for 0 é carne se for 1 é boleto
	private Integer typeSend; // se for 4 carta se for 1 email
	private String articleFileReport;
	private byte[] image_first;
	private byte[] image_second;
	private byte[] image_third;
	private byte[] image_carne_first;
	private byte[] image_carne_second;
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getArticleFileReport() {
		return articleFileReport;
	}
	public void setArticleFileReport(String articleFileReport) {
		this.articleFileReport = articleFileReport;
	}
	public byte[] getImage_first() {
		return image_first;
	}
	public void setImage_first(byte[] image_first) {
		this.image_first = image_first;
	}
	public byte[] getImage_second() {
		return image_second;
	}
	public void setImage_second(byte[] image_second) {
		this.image_second = image_second;
	}
	public byte[] getImage_third() {
		return image_third;
	}
	public void setImage_third(byte[] image_third) {
		this.image_third = image_third;
	}
	public byte[] getImage_carne_first() {
		return image_carne_first;
	}
	public void setImage_carne_first(byte[] image_carne_first) {
		this.image_carne_first = image_carne_first;
	}
	public byte[] getImage_carne_second() {
		return image_carne_second;
	}
	public void setImage_carne_second(byte[] image_carne_second) {
		this.image_carne_second = image_carne_second;
	}
	public Integer getTypeSend() {
		return typeSend;
	}
	public void setTypeSend(Integer typeSend) {
		this.typeSend = typeSend;
	}
}
