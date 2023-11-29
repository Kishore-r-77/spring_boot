package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.DeathClaimLeapFundDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimLeapFundDetailsRepository;

@Service
public class DeathClaimLeapFundDetailsService {
	
	@Autowired
	private DeathClaimLeapFundDetailsRepository deathClaimLeapFundDetailsRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<DeathClaimLeapFundDetails> getAll(){
		return deathClaimLeapFundDetailsRepo.getallActive();
	}
	
	public DeathClaimLeapFundDetails getById(Long id) {
		return deathClaimLeapFundDetailsRepo.getActiveById(id);
	}
	
	public String add(DeathClaimLeapFundDetails deathClaimLeapFundDetails) {
		deathClaimLeapFundDetails.setValidFlag(1);
		deathClaimLeapFundDetailsRepo.save(deathClaimLeapFundDetails);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, DeathClaimLeapFundDetails deathClaimLeapFundDetails) {
		DeathClaimLeapFundDetails deathClaimLeapFundDetails1 = deathClaimLeapFundDetailsRepo.getActiveById(id);
		if(deathClaimLeapFundDetails.getCompanyId()!=null) {
			deathClaimLeapFundDetails1.setCompanyId(deathClaimLeapFundDetails.getCompanyId());
		}
		if(deathClaimLeapFundDetails.getPolicyNo()!=null) {
			deathClaimLeapFundDetails1.setPolicyNo(deathClaimLeapFundDetails.getPolicyNo());
		}
		if(deathClaimLeapFundDetails.getPurpleFundCode()!=null) {
			deathClaimLeapFundDetails1.setPurpleFundCode(deathClaimLeapFundDetails.getPurpleFundCode());
		}
		if(deathClaimLeapFundDetails.getPurpleFundName()!=null) {
			deathClaimLeapFundDetails1.setPurpleFundName(deathClaimLeapFundDetails.getPurpleFundName());
		}
		if(deathClaimLeapFundDetails.getPurpleUnits()!=null) {
			deathClaimLeapFundDetails1.setPurpleUnits(deathClaimLeapFundDetails.getPurpleUnits());
		}
		if(deathClaimLeapFundDetails.getPurpleRateApp()!=null) {
			deathClaimLeapFundDetails1.setPurpleRateApp(deathClaimLeapFundDetails.getPurpleRateApp());
		}
		if(deathClaimLeapFundDetails.getRemark()!=null) {
			deathClaimLeapFundDetails1.setRemark(deathClaimLeapFundDetails.getRemark());
		}
		
		deathClaimLeapFundDetailsRepo.save(deathClaimLeapFundDetails1);
		return errorService.getErrorById("ER001");
	}
	
	public String deactivate(Long id) {
		DeathClaimLeapFundDetails deathClaimLeapFundDetails = deathClaimLeapFundDetailsRepo.getActiveById(id);
		deathClaimLeapFundDetails.setValidFlag(-1);
		deathClaimLeapFundDetailsRepo.save(deathClaimLeapFundDetails);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		deathClaimLeapFundDetailsRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<DeathClaimLeapFundDetails> search(String key){
		return deathClaimLeapFundDetailsRepo.globalSearch(key);
	}
	
	public List<DeathClaimLeapFundDetails> getAllByPolicyNo(Long policyNo){
		return deathClaimLeapFundDetailsRepo.getallByPolicyNo(policyNo);
	}

}
