package com.novotempo.endofyeartoast.bankslip.boleto;

import java.util.Date;

import com.novotempo.endofyeartoast.bankslip.barcode.Barcode;
import com.novotempo.endofyeartoast.bankslip.barcode.BarcodeBradesco;
import com.novotempo.endofyeartoast.bankslip.digitableline.DigitableLine;
import com.novotempo.endofyeartoast.bankslip.digitableline.DigitableLineBradesco;
import com.novotempo.endofyeartoast.bankslip.exception.BoletoException;
import com.novotempo.endofyeartoast.bankslip.validate.ValidacaoBoleto;

public class BoletoBradesco extends Boleto {
	
	private Barcode barcode;
	private DigitableLine digitableLine;
	
	public BoletoBradesco() throws BoletoException{
		setNumeroBanco("237-2");
		setLocalPagamento(getLocalPagamento());
		barcode = new BarcodeBradesco(this);
		digitableLine = new DigitableLineBradesco(this);
	}
	
	public BoletoBradesco(String codigoCedente, String agencia, String carteira, String numeroDocumento, Date dataVencimento) throws BoletoException{
		setNumeroBanco("237-2");
		setLocalPagamento(getLocalPagamento());
		setCodigoCedente(codigoCedente);
		setAgencia(agencia);
		setCarteira(carteira);
		setNumeroDocumento(numeroDocumento);
		setDataVencimento(dataVencimento);
		barcode = new BarcodeBradesco(this);
		digitableLine = new DigitableLineBradesco(this);
	}
	
	public void setBarcode(Barcode barcode) {
		this.barcode = barcode;
	}
	
	public void setDigitableLine(DigitableLine digitableLine) {
		this.digitableLine = digitableLine;
	}
	
	@Override
	public String getLinhaDigitavel() throws BoletoException {
		return digitableLine.getLinhaDigitavel();
	}
	
	@Override
	public String getNossoNumero() throws BoletoException {
		
		String nossoNumero = "";
		String numeroDocumento = getNumeroDocumento();
		String carteira		   = getCarteira();	
		//cria��o do nosso numero do bradesco
		nossoNumero = NossoNumero.getNossoNumeroBradesco(numeroDocumento, carteira);
		return nossoNumero;
	}

	@Override
	public Boleto setCarteira(String carteira) throws BoletoException {
		ValidacaoBoleto.validaNumeroTamanhoEObrigatoriedade(carteira, 2, "Carteira");
		return super.setCarteira(carteira);
	}
	
	@Override
	public Boleto setCodigoCedente(String codigoCedente) throws BoletoException {
		if (codigoCedente == null)
			throw new BoletoException("C�digo do Cendente n�o pode ser nulo");
		
		if (!codigoCedente.contains("-"))
			throw new BoletoException("C�digo do Cendente deve possuir um digito");
		
		return super.setCodigoCedente(codigoCedente);
	}
	
	@Override
	public Boleto setAgencia(String agencia) throws BoletoException {
		if (agencia == null)
			throw new BoletoException("Ag�ncia n�o pode ser nulo");
		
		if (!agencia.contains("-"))
			throw new BoletoException("Ag�ncia deve possuir um digito");

		return super.setAgencia(agencia);
	}
	
	@Override
	public String getCodigoBarras() throws BoletoException {
		return barcode.getBarcode();
	}
}
