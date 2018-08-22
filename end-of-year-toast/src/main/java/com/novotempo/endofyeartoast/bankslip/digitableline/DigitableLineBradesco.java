package com.novotempo.endofyeartoast.bankslip.digitableline;

import com.novotempo.endofyeartoast.bankslip.boleto.Boleto;
import com.novotempo.endofyeartoast.bankslip.exception.BoletoException;
import com.novotempo.endofyeartoast.bankslip.validate.ValidacaoBoleto;

public class DigitableLineBradesco implements DigitableLine{
	
	private final Boleto boleto;
	
	private String preGeneratedDigitableLine;
	
	public DigitableLineBradesco(Boleto boleto) throws BoletoException {
		this.boleto = boleto;
	}
	
	public void setDigitableLine(String digitableLine) {
		this.preGeneratedDigitableLine = digitableLine;
	}
	
	@Override
	public String getLinhaDigitavel() throws BoletoException {
		
		if(preGeneratedDigitableLine != null && !preGeneratedDigitableLine.isEmpty()) {
			return preGeneratedDigitableLine;
		}
		
		String campo1 = "";
		String campo2 = "";
		String campo3 = "";
		String campo4 = "";
		String campo5 = "";
		
		String codigoBarras = boleto.getCodigoBarras();
		String campoLivre = codigoBarras.substring(19,codigoBarras.length());

		// CAMPO 1
		campo1 = ValidacaoBoleto.montaLinhaDigitavel4PrimeirasPosicoes(boleto);
		campo1 += campoLivre.substring(0,5);
		String digito1 = ValidacaoBoleto.aplicaFatorPorPesoModulo10(campo1 , (short)2, false);
		campo1 = ValidacaoBoleto.adicionaPontoNoCampoLinhaDigitavel(campo1+digito1);
		
		// CAMPO 2
		campo2 = campoLivre.substring(5,15);
		String digito2 = ValidacaoBoleto.aplicaFatorPorPesoModulo10(campo2 , (short)2, false);
		campo2 = ValidacaoBoleto.adicionaPontoNoCampoLinhaDigitavel(campo2+digito2);
		
		// CAMPO 3
		campo3 = campoLivre.substring(15,25);
		String digito3 = ValidacaoBoleto.aplicaFatorPorPesoModulo10(campo3 , (short)2, false);
		campo3 = ValidacaoBoleto.adicionaPontoNoCampoLinhaDigitavel(campo3+digito3);
		
		// CAMPO 4
		campo4 = codigoBarras.substring(4,5);
		
		// CAMPO 5
		campo5 = ValidacaoBoleto.montaCampo5LinhaDigitavel(boleto);
		
		return campo1 + " " + campo2 + " "+ campo3 + " "+ campo4 + " "+ campo5;
	}

}
