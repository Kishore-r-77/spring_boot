package com.futura.Purple.Entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "ipca_surrender")
public class IPCASurrender {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long companyId;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "companyId", updatable = false, insertable = false)
	private Company company;
	
	private Long policyNo;
	private Long transNo;
	private String uinNumber;
	private String reqDate;
	private String logDate;
	private Double noOfDues;
	private Double totalPremium;
	private Double valueOfbonus;
	private Double cvbFactor;
	private Double gsvFactor;
	private Double gsv;
	private Double gsvGross;
	private Double sbPaid;
	private Double gsvNet;
	private Double paidUpValue;
	private Double reversionaryBonus;
	private Double interimBonus;
	private Double cashValueBonus;
	private Double terminalBonus;
	private Double ssvGrossAmount;
	private Double ssvFactor;
	private Double ssvNet;
	private String ssvOrGsv;
	private Double fundValue;
	private String effDate;
	private Double policyDeposite;
	private Double penalIntrest;
	private Double grossPay;
	private Double cdaCharge;
	private Double ulipSurrenderCharges;
	private Double tds;
	private Double totalRecovery;
	private Double otherRecovery;
	private Double netPayable;
	private Double policyLoan;
	private Double loanInterest;
	private String makerFlag;
	private String checkerFlag;
	private String pfFlag;
	private String pfRemarks;
	private String purpleApprovalFlag;
	private String purpleApprovalRemark;
	private String purpleApprovalDate;
	private Long approvalQcUserId;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@CreationTimestamp
	private LocalDateTime createdDate;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long createdBy;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@UpdateTimestamp
	private LocalDateTime modifiedDate;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long modifiedBy;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int validFlag;
	
}
