package com.novotempo.endofyeartoast.bankslip.exception;

/**
 * Encapsula as exce��es de boleto
 */
public class BoletoException extends Exception{

	private static final long serialVersionUID = -3915262039481563553L;

	public BoletoException(String msg){
		super(msg);
	}
	
	public BoletoException(Exception exception) {
		super(exception);
	}
	
	public BoletoException(String message, Exception exception) {
		super(message, exception);
	}
}