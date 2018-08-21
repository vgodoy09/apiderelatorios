package com.novotempo.endofyeartoast.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class WrappedToast implements Externalizable {
	private byte[] bankslipPdf;

	public WrappedToast() {}

	public WrappedToast(byte[] pdf) {
		this.bankslipPdf = pdf;
	}

	public byte[] getBankSlipPdf() {
		return bankslipPdf;
	}

	public void setBankSlipPdf(byte[] pdf) {
		this.bankslipPdf = pdf;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.write(bankslipPdf, 0, bankslipPdf.length);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		 in.readFully(bankslipPdf, 0, bankslipPdf.length);
	}
}
