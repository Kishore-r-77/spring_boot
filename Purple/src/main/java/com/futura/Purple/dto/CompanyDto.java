package com.futura.Purple.dto;

import java.time.LocalDate;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class CompanyDto {
	
	private Long id;
	private String companyCode;
	private String companyName;
	private String companyShortName;
	private String companyLongName;
	private Long addressId;
	private String gst;
	
	private String cin;
	
	private String cinDate;
	
	private String tin;
	
	private String pan;
	@Lob
	private String companyLogo;
	
	
	
	
	
	

}
