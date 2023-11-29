package com.futura.Purple.Entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "ipca_fund_details")
public class IPCAFundDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private  Long companyId;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId",updatable = false,insertable = false)
    private Company company;
    
    private  Long policyNo;
    
    private String purpleFundCode;
    
    private String purpleFundName;
    
    private String purpleNavDate;
    
    private Double purpleUnits;
    
    private Double purpleRateApp;
    
    private Double purpleFundValue;
    
    private String status;
    private String remark;
    
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
