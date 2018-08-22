package com.novotempo.endofyeartoast.model.enums;

public enum Aceite {

	SIM("S"), NAO("N");
	
	private String aceite;
	
	Aceite(String aceite){
		this.aceite = aceite;
	}

	public String getAceite() {
		return aceite;
	}
}
