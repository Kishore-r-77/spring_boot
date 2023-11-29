package com.futura.Purple.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.Company;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.CompanyRepository;
import com.futura.Purple.dto.CompanyDto;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepo;
	
	
	@Autowired
	private ErrorService errorService;
	
//	public List<Company> getall(){
//		return companyRepo.getallActive();
//	}
	
	public List<CompanyDto> getall() {
		List<CompanyDto> companyDtos = new ArrayList<>();
		List<Company> companies = companyRepo.getallActive();
		companies.stream().forEach(company -> {
			CompanyDto companyDto = mapToDto(company);
			companyDtos.add(companyDto);
		});
		return companyDtos;
	}
	
	public CompanyDto getById(Long id) {
		Company company= companyRepo.getActiveById(id);
		return mapToDto(company);
	}
	
	public String add(Company entity) {
		entity.setValidFlag(1);
		companyRepo.save(entity);
		return errorService.getErrorById("ER001");
		
	}
	
	public String update(Long id, Company entity) {
		Company company = companyRepo.getActiveById(id);
		
		if(entity.getCompanyCode()!=null) {
			company.setCompanyCode(entity.getCompanyCode());
		}
		if(entity.getCompanyName()!=null) {
			company.setCompanyName(entity.getCompanyName());
		}
		if(entity.getCompanyLongName()!=null) {
			company.setCompanyLongName(entity.getCompanyLongName());
		}
		if(entity.getCompanyShortName()!=null) {
			company.setCompanyShortName(entity.getCompanyShortName());
		}
		if(entity.getAddressId()!=null) {
			company.setAddressId(entity.getAddressId());
		}
		if(entity.getGst()!=null) {
			company.setGst(entity.getGst());
		}
		if(entity.getCin()!=null) {
			company.setCin(entity.getCin());
		}
		if(entity.getCinDate()!=null) {
			company.setCinDate(entity.getCinDate());
		}
		if(entity.getPan()!=null) {
			company.setPan(entity.getPan());
		}
		if(entity.getTin()!=null) {
			company.setTin(entity.getTin());
		}
		if(entity.getCompanyLogo()!=null) {
			company.setCompanyLogo(entity.getCompanyLogo());
		}
		
		
		companyRepo.save(company);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		Company company = companyRepo.getActiveById(id);
		company.setValidFlag(-1);
		companyRepo.save(company);
		return errorService.getErrorById("ER008");
	}
	
	public String hardDelete(Long id) {
		companyRepo.deleteById(id);
		return errorService.getErrorById("ER008");
	}
	
	public List<Company> search(String key){
		return companyRepo.globalSearch(key);
	}
	
	public CompanyDto mapToDto(Company company) {
		CompanyDto companyDto=new CompanyDto();
		
		companyDto.setId(company.getId());
		companyDto.setCompanyCode(company.getCompanyCode());
		companyDto.setCompanyShortName(company.getCompanyShortName());
		companyDto.setCompanyLongName(company.getCompanyLongName());
		companyDto.setCompanyName(company.getCompanyName());
		companyDto.setAddressId(company.getAddressId());
		companyDto.setGst(company.getGst());
		companyDto.setTin(company.getTin());
		companyDto.setCin(company.getCin());
		companyDto.setCinDate(company.getCinDate());
		companyDto.setPan(company.getPan());
		companyDto.setCompanyLogo(company.getCompanyLogo());		
		return companyDto;
	}

}
