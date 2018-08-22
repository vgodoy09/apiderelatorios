package com.novotempo.endofyeartoast.bankslip.boleto;

import com.novotempo.endofyeartoast.bankslip.exception.BoletoException;
import com.novotempo.endofyeartoast.bankslip.validate.ValidacaoBoleto;

public class NossoNumero {
	
	/**
	 * Metodo que gera o nossoNumero do bradesco
	 * @param numeroDocumento numero do documento que no caso � financialRecord
	 * @param carteira numero da carteira que no caso � o portfolio do FinancialRecord
	 * @return (String) nossoNumero
	 * @throws BoletoException
	 */
	public static String getNossoNumeroBradesco(String numeroDocumento, String carteira) throws BoletoException{
		// se nao tiver carteira ou for diferente de 2
		ValidacaoBoleto.validaNumeroTamanhoEObrigatoriedade(carteira, 2, "Carteira");
		//valida o numeroDocumento
		numeroDocumento = validateNumeroDocumento(numeroDocumento);
		String digito = getDigitoBradesco(numeroDocumento, carteira);
		return carteira+"/"+numeroDocumento+"-"+digito;
	}

	/**
	 * Valida numeroDocumento vendo se nao tem numero  de documento e forcando a possuir 11 caracter
	 * @param numeroDocumento numero do documento que no caso � financialRecord
	 * @return (String) nossoNumero
	 * @throws BoletoException
	 */
	public static String validateNumeroDocumento(String numeroDocumento) throws BoletoException {
		// se nao tiver numero de documento
		ValidacaoBoleto.validaNumeroTamanhoEObrigatoriedade(numeroDocumento, 0, "N�mero de Documento");
		// no bradesco, a numeracao deve possui 11 posicoes
		numeroDocumento = ValidacaoBoleto.preencheAEsquerdaComZero(numeroDocumento,11);
		return numeroDocumento;
	}
	
	/**
	 * Metodo que gera o digito do nossoNumero
	 * @param numeroDocumento numero do documento que no caso � financialRecord
	 * @param carteira numero da carteira que no caso � o portfolio do FinancialRecord
	 * @return (String) nossoNumero
	 * @throws BoletoException
	 */
	public static String getDigitoBradesco(String numeroDocumento, String carteira) throws BoletoException{
		String resto = ValidacaoBoleto.aplicaFatorPorPesoModulo11(carteira+numeroDocumento, (short)7, true);
		// pega 11 - o resto para saber o digito
		short digito_tmp = (short) (11 - Short.parseShort(resto));
		
		String digito = "";
		switch (Short.parseShort(resto)) {
		case 1:
			digito = "P";
			break;
		case 0:
			digito = "0";
			break;

		default:
			digito = String.valueOf(digito_tmp);
			break;
		}
		
		return digito;
	}

}
