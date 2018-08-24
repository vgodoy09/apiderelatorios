package com.novotempo.endofyeartoast.model.donation;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Donation", schema = "dbo")  
public class DonationModel {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
	@Column(name = "financialCompany_id")
    private Integer financialCompany_id;
    
    @Column(name = "tagType_id")
    private Integer tagType_id;
    
    @NotNull(message="Serviço não pode ser nulo")
    @Column(name="service_id")
    private Integer service_id;
    
    @Column(name="project_id")
    private Integer project_id;
    
    @NotNull(message="Quota de início não pode ser nula")
    @Column(name = "quota")
    private Integer quota;
    
    @Column(name = "inactiveDate")
    private LocalDate inactiveDate;
    
    @Column(name = "inactiveByPerson_id")
    private Integer inactiveByPerson;
    
    @Column(name = "desiresReceipt")
    private Boolean desiresReceipt;
    
    @Column(name = "betterDay")
    private Integer betterDay;
    
    @Column(name = "value")
    private Double value;
    
    @Column(name = "branch")
    private String branch;
    
    @Column(name = "branchDigit")
    private String branchDigit;
    
    @Column(name = "account")
    private String account;
    
    @Column(name = "accountDigit")
    private String accountDigit;
    
    @Column(name = "creditCardNumber")
    private String creditCardNumber;
    
    @Column(name = "creditCardName")
    private String creditCardName;
    
    @Column(name = "creditCardValidThruMonth")
    private Short creditCardValidThruMonth;
    
    @Column(name = "creditCardValidThruYear")
    private Short creditCardValidThruYear;
    
    @Column(name = "creditCardSecurity")
    private String creditCardSecurity;
    
    @Column(name = "valueResponsible")
    private double valueResponsible;
    
    @Column(name="carnet")
    private Boolean carnet;
    
    @NotNull(message="Data de início não pode ser nula")
    @Column(name = "startDate")
    private LocalDate startDate;
    
    @NotNull(message="Periodicidade não pode ser nulo")
    @Column(name = "periodicity_id")
    private Integer periodicity_id;
    
    @Column(name = "conclusionDate")
    private LocalDate conclusionDate;
    
    @NotNull(message="Processed não pode ser nulo")
    @Column(name = "processed")
    private Boolean processed;
    
    @Column(name = "source_id")
    private Integer source_id; 
    
    @NotNull(message="Entidade não pode ser vazia")
    @Column(name="company_id")
    private Integer company_id;
    
    @Column(name = "hasToast")
    private Boolean hasToast;
    
    @Column(name = "currency_id")
    private Integer currency_id;
 
    @Column(name = "codeOperation")
    private String codeOperation;
    
    @Column(name = "inactivedBySystem")
    private Boolean inactivedBySystem;
}
