package com.futura.Purple.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class FundView {

private Long chdrNum;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String fundCode;
    
    private String fundName;
    
    private String navDate;
    
    private Double units;
    
    private Double rateApp;
    
    private Double value;
    
    private String purpleNavDate;
    
    private Double purpleRateApp;
    
    private Double purpleFundValue;
    
    private String status;
}
