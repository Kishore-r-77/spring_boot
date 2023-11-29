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
public class DeathClaimTransactionPas {

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
	
	private String productType;
	private String contractType;
	private String planName;
	private String planCode;
	private String doc;
	
	private String riskComDate;
	private String policyTerm;
	private String premiumTerm;
	private Double installmentPremium;
	private String billFreq;
	
	private String policyStatus;
	private String fup;
	private String dateOfRevival;
	private String dateOfDeath;
	private String dateOfIntimation;
	
	private String dateOfLogin;
	private String causeOfDeath;
	private String deathReasonCode;
	private Double basicSumAssured;
	private Double additionalSumAssured;
	
	private Double termRiderSumAssured;
	private Double inbuiltRiderSumAssured;
	private Double reversionaryBonus;
	private Double interimBonus;
	private Double guranteedBonus;
	
	private Double loyaltyAddition;
	private Double otherRider;
	private Double terminalBonus;
	private Double totalBonus;
	private Double refundOfPurchasePrice;
	
	private Double fundValue;
	private String effectiveDate;
	private String approvalDate;
	private Double totalDeathClaim;
	private Double policyDeposit;
	private Double penalInterest;
	private Double grossPay;
	
	private Double terminalPremRecov;
	private Double interestOnPrem;
	private Double gstOnPrem;
	private Double cdaCharges;
	private Double otherCharges;
	
	private Double policyLoan;
	private Double policyLoanInterest;
	private Double moneybackPaidRecov;
	private Double annuityPaidRecov;
	private Double mortalityChargeRefund;
	
	private Double yearlyAnnuityAmunt;
	private String annuityStartDate;
	private Double annuityGuranteedYears;
	
	private Double adminFeeRefund;
	private Double guranteedAdditionCharges;
	private Double tds;
	private Double totalRecovery;
	private Double netPayable;
	
	private String nominationFlag;
	private Long nomineeClientId;
	private String assignementFlag;
	private Long assigneeClientId;
	private String dateOfAssignment;
	private Long claimId;
	private String deathClaimPayoutDate;
	
	private String deathBenefitType;
	private String makerFlag;
	private String checkerFlag;
	private String leapApprovalFlag;
	private String leapApprovalRemarks;
	private String leapApprovalDate;
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
	
	private int validFlag;
	
}
