package com.novotempo.endofyeartoast.model.donation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class PrintingToast implements Externalizable {
	
    private Integer product_id;
    private String labelName;
    private Integer user_id;
    
    public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		product_id = (Integer) in.readObject();
		labelName = (String) in.readObject();
		user_id = (Integer) in.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(product_id);
		out.writeObject(labelName);
		out.writeObject(user_id);
	}
}
