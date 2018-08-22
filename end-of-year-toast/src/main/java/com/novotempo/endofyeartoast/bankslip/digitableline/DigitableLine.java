package com.novotempo.endofyeartoast.bankslip.digitableline;

import com.novotempo.endofyeartoast.bankslip.exception.BoletoException;

public interface DigitableLine {
	public String getLinhaDigitavel() throws BoletoException;
}
