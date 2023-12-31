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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurrenderTransactionPas {

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
	private String svReqDate;
	private String logDate;	
	private Double gsv;
	private Double gsvPayble;
	private Double ssv;
	private Double policyDeposit;
	private Double penalInterest;
	private Double grossPay;
	private Double cdaCharges;
	private Double tds;
	private Double cashValueBonus;
	private Double paidUpValue;
	private Double reversionaryBonus;
	private Double interimBonus;
	private Double totalBonus;
	private Double totalRecovery;
	private Double otherRecovery;
	private Double netPayable;
	private String effectiveDate;
	private String approvDate;
	private Double fundValue;
	private Double surrenderCharge;
	private Double policyLoan;
	private Double loanInterest;
	private String makerFlag;
	private String checkerFlag;
	private String ipcaApprovalFlag;
	private String ipcaApprovalRemarks;
	private String ipcaApprovalDate;
	private Long qcUserId;
	private String interimStatus;

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
