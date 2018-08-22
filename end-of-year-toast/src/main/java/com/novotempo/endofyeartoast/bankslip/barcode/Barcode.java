package com.novotempo.endofyeartoast.bankslip.barcode;

import com.novotempo.endofyeartoast.bankslip.exception.BoletoException;

public interface Barcode {
	public String getBarcode() throws BoletoException;
}
