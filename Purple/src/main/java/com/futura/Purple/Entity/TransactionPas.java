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
import javax.persistence.UniqueConstraint;

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
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "flcTransNo")})
public class TransactionPas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private Long companyId;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "companyId", updatable = false, insertable = false)
	private Company company;

	private Long flcPolicyNo;
	private Long flcTransNo;
	private String flcReqDate;
	private String flcLogDate;
	private String uinNumber;
	private Long flcPremRefund;  // non ulip policy
	private Long flcTotalPrem;  // ulip policy
	private Long flcPolicyDop;
	private Long penalIntrest;
	private Double grossFlcPay;
	private Long medicalFee;
	private Double stamDuty;
	private Double riskPremRecov;  // non ulip policy
	private Double mortChargeRefund;  // ulip policy 
	private Double totalRecov;
	private Double netFlcPay;  
	private String effDate;  // ulip policy
	private Double fundValue;  // ulip policy
	private String flcApprovalDate;
	private String medicalCategory;
	private String medicalCenter;
	private String medicatTpaCode;
	private String makerFlag;
	private String checkerFlag;
	private String purpleApprovalFlag;
	private String purpleApprovalRemark;
	private String purpleApprovalDate;
	private Long approvalQcUserId;
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
