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

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class UinMaster {
 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long companyId;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "companyId", updatable = false, insertable = false)
	private Company company;
	
	private String uinNumber;
	private String planName;
	private String planCode;
	private String gsvFactor;
	private String gsvCashValue;
	private String ssvFactor;
	private String revesionaryBonus;
	private String interimBonus;
	private String terminalBonus;
	private String guaranteedBonus;
	private String loyaltyBonus;
	private String productType;
	private String flcEligibility;
	private Double surrenderChargeRate;
	private String startDate;
	private String endDate;

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
