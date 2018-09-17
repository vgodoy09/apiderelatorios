package com.novotempo.endofyeartoast.model.donation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LotesPrintedDTO implements Externalizable {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
    
    @Column(name = "product_id")
    private Integer product_id;
 
    @Column(name = "labelName")
    private String labelName;
    
    @Column(name = "registryDate")
    private LocalDate registryDate;
    
    @Column(name = "amount")
    private Integer amount;
    
    @Column(name = "[login]")
    private String whoprint;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public LocalDate getRegistryDate() {
		return registryDate;
	}

	public void setRegistryDate(LocalDate registryDate) {
		this.registryDate = registryDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getWhoprint() {
		return whoprint;
	}

	public void setWhoprint(String whoprint) {
		this.whoprint = whoprint;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		id = (Integer) in.readObject();
		product_id = (Integer) in.readObject();
		labelName = (String) in.readObject();
		registryDate = (LocalDate) in.readObject();
		amount = (Integer) in.readObject();
		whoprint = (String) in.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(id);
		out.writeObject(product_id);
		out.writeObject(labelName);
		out.writeObject(registryDate);
		out.writeObject(amount);
		out.writeObject(whoprint);
	}
    
}