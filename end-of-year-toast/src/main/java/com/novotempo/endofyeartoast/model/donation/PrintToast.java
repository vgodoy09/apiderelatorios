package com.novotempo.endofyeartoast.model.donation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PrintToast implements Externalizable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer product_id;
 
    @Column(name = "productName")
    private String labelName;
    
    @Column(name = "amount")
    private Integer amount;
    
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		product_id = (Integer) in.readObject();
		labelName = (String) in.readObject();
		amount = (Integer) in.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(product_id);
		out.writeObject(labelName);
		out.writeObject(amount);
	}
}
