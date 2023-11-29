package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.AnnuityRates;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.AnnuityRatesRepository;

@Service
public class AnnuityRatesService {
	
	@Autowired
	private AnnuityRatesRepository annuityRatesRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<AnnuityRates> getall(){
		return annuityRatesRepo.getallActive();
	}
	
	public AnnuityRates getById(Long id) {
		return annuityRatesRepo.getActiveById(id);
	}
	
	public String add(AnnuityRates entity) {
		entity.setValidFlag(1);
		annuityRatesRepo.save(entity);
		return errorService.getErrorById("ER001");
		
	}
	
	public String update(Long id, AnnuityRates entity) {
		AnnuityRates annuityRates = annuityRatesRepo.getActiveById(id);
		if(entity.getCompanyId()!=null) {
			annuityRates.setCompanyId(entity.getCompanyId());
		}
		if(entity.getProductCode()!=null) {
			annuityRates.setProductCode(entity.getProductCode());
		}
		if(entity.getPendingAnnuityYears()!=null) {
			annuityRates.setPendingAnnuityYears(entity.getPendingAnnuityYears());
		}
		if(entity.getAnnuityDiscountRate()!=null) {
			annuityRates.setAnnuityDiscountRate(entity.getAnnuityDiscountRate());
		}
	
		
		annuityRatesRepo.save(annuityRates);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		AnnuityRates annuityRates = annuityRatesRepo.getActiveById(id);
		annuityRates.setValidFlag(-1);
		annuityRatesRepo.save(annuityRates);
		return errorService.getErrorById("ER008");
	}
	
	
}
