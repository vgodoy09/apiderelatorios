package com.novotempo.endofyeartoast.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class WrappedToast implements Externalizable {
	private byte[] bankslipPdf;
	private Integer batchLabelPrint_id;

	public WrappedToast() {}

	public WrappedToast(byte[] pdf, Integer batchLabelPrint_id) {
		this.bankslipPdf = pdf;
		this.batchLabelPrint_id = batchLabelPrint_id;
	}

	public byte[] getBankSlipPdf() {
		return bankslipPdf;
	}

	public void setBankSlipPdf(byte[] pdf) {
		this.bankslipPdf = pdf;
	}
	
	public Integer getBatchLabelPrint_id() {
		return batchLabelPrint_id;
	}

	public void setBatchLabelPrint_id(Integer batchLabelPrint_id) {
		this.batchLabelPrint_id = batchLabelPrint_id;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.write(bankslipPdf, 0, bankslipPdf.length);
		out.writeObject(batchLabelPrint_id);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		 in.readFully(bankslipPdf, 0, bankslipPdf.length);
		 batchLabelPrint_id = (Integer) in.readObject();
	}
}
