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
@NoArgsConstructor
@AllArgsConstructor
public class DeathClaimLeapParameter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long companyId;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "companyId",updatable = false,insertable = false)
	private Company company;
	
	private String basicSa;
	private Long increaseSaYears;
	private Long percentageSaIncrease;
	private String reversionaryBonus;
	private String loyaltyBonus;
	private String guaranteedBonus;
	private String terminalBonus;
	private String suicideClause;
	private Long waitingPeriod;
	private String refundOfAdminFee;
	private String refundOfMc;
	private String refundOfGuaranteedCharges;
	private String returnOfPremiums;
	private String fundvalueSaPayable;
	private String claimConcession;
	private String tdsType;
	private Long tdsRate;
	
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















