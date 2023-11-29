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
public class CoverDetailsPas {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
 
     private  Long id;
     private  Long companyId;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId",updatable = false,insertable = false)
    private Company company;
    
     private  Long clntNum;
     
     private  Long chdrNum;
     private  String cntType;
     private  String crTable;
     private  String uinNumber;
     private  String riskComDate;
     private  String docDate;
     private  Long sumAssured;
     private  String riskCessTerm; // policy term
     private  String premCessTerm;
     private  Long coverPremium;
     private  String coverStatus;
//     private String mortFlag;
//     private Double stampRate;
 
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
