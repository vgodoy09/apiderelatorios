package com.novotempo.endofyeartoast.bankslip.barcode;

import com.novotempo.endofyeartoast.bankslip.boleto.Boleto;
import com.novotempo.endofyeartoast.bankslip.exception.BoletoException;
import com.novotempo.endofyeartoast.bankslip.validate.ValidacaoBoleto;

public class BarcodeBradesco implements Barcode {
	private final Boleto boleto;
	
	private String preGeneratedBarcode;
	
	public BarcodeBradesco(Boleto boleto) throws BoletoException {
		this.boleto = boleto;
	}
	
	public void setBarcode(String barcode) {
		this.preGeneratedBarcode = barcode;
	}

	@Override
	public String getBarcode() throws BoletoException {
		
		if(preGeneratedBarcode != null && !preGeneratedBarcode.isEmpty()) {
			return preGeneratedBarcode; 
		}
		
		String codigoBarras = "";
		// pega as 20 primeiras posicoes padrao
		String primeiras20 = ValidacaoBoleto.montaCodigoBarrasPrimeiras20posicoes(boleto);
		
		// posicao 20-23 Agencia
		String agencia = boleto.getAgencia();
		if (agencia == null){
			throw new BoletoException("Ag�ncia � obrigat�rio para gerar o c�digo de barras");
		}
		// remove o digito
		try {
			agencia = agencia.split("-")[0];	
		} catch (Exception e) {
			throw new BoletoException("Ag�ncia deve estar neste formato: XXXX-X para gerar o c�digo de barras");
		}
		ValidacaoBoleto.validaNumeroTamanhoEObrigatoriedade(agencia, 0,"Ag�ncia");
		agencia = ValidacaoBoleto.preencheAEsquerdaComZero(agencia, 4);
		
		// posicao 24-25 - carteira
		String carteira = boleto.getCarteira();
		ValidacaoBoleto.validaNumeroTamanhoEObrigatoriedade(carteira, 2,"Carteira");
		
		// posicao 26-36 - nosso numero sem o digito
		String nossoNumero = boleto.getNossoNumero();
		if (nossoNumero != null && (nossoNumero.contains("/") || nossoNumero.contains("-"))){
			//retira a carteira e o digito
			try {
				nossoNumero = nossoNumero.split("/")[1];	
			} catch (Exception e) {
			
			}
			try {
				nossoNumero = nossoNumero.split("-")[0];
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		ValidacaoBoleto.validaNumeroTamanhoEObrigatoriedade(nossoNumero, 0, "Nosso N�mero");
		nossoNumero = ValidacaoBoleto.preencheAEsquerdaComZero(nossoNumero, 11);
		
		// posicao 37-43 - conta do cedente
		String codigoCedente = boleto.getCodigoCedente();
		if (codigoCedente == null){
			throw new BoletoException("C�digo Cedente � obrigat�rio para gerar o c�digo de barras");
		}
		// remove o digito
		try {
			codigoCedente = codigoCedente.split("-")[0];	
		} catch (Exception e) {
			throw new BoletoException("C�digo Cedente deve estar neste formato: XXXX-X para gerar o c�digo de barras");
		}
		ValidacaoBoleto.validaNumeroTamanhoEObrigatoriedade(codigoCedente, 0, "C�digo Cedente");
		codigoCedente = ValidacaoBoleto.preencheAEsquerdaComZero(codigoCedente, 7);

		// pega o digito
		String digito = ValidacaoBoleto.montaDigitoVerificadorCodigoBarras(primeiras20 + agencia + carteira + nossoNumero + codigoCedente + 0);
		// monta o codigo de barras
		codigoBarras = primeiras20.substring(0,4)+digito+primeiras20.substring(4,18)+ agencia + carteira + nossoNumero + codigoCedente + 0;
		
		return codigoBarras;
	}
}