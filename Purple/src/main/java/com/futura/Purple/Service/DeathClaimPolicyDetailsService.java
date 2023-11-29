package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.DeathClaimPolicyDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimPolicyDetailsRepository;

@Service
public class DeathClaimPolicyDetailsService {
	
	@Autowired
	private DeathClaimPolicyDetailsRepository PolicyDetailsRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<DeathClaimPolicyDetails> getAll(){
		return PolicyDetailsRepo.getallActive();
	}
	
	public DeathClaimPolicyDetails getByPolicyNo(Long policyNo) {
		return PolicyDetailsRepo.getActiveByPolicyNo(policyNo);
	}
	
	public DeathClaimPolicyDetails getById(Long id) {
		return PolicyDetailsRepo.getActiveById(id);
	}
	
	public String add(DeathClaimPolicyDetails DeathClaimPolicyDetails) {
		DeathClaimPolicyDetails.setValidFlag(1);
		PolicyDetailsRepo.save(DeathClaimPolicyDetails);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id,DeathClaimPolicyDetails DeathClaimPolicyDetails) {
		DeathClaimPolicyDetails DeathClaimPolicyDetails1 = PolicyDetailsRepo.getActiveById(id);
		if(DeathClaimPolicyDetails.getCompanyId()!=null)
		{
			DeathClaimPolicyDetails1.setCompanyId(DeathClaimPolicyDetails.getCompanyId());
		}
		if(DeathClaimPolicyDetails.getClntNum()!=null)
		{
			DeathClaimPolicyDetails1.setClntNum(DeathClaimPolicyDetails.getClntNum());
		}
		if(DeathClaimPolicyDetails.getChdrNum()!=null)
		{
			DeathClaimPolicyDetails1.setChdrNum(DeathClaimPolicyDetails.getChdrNum());
		}
		if(DeathClaimPolicyDetails.getBillFreq()!=null)
		{
			DeathClaimPolicyDetails1.setBillFreq(DeathClaimPolicyDetails.getBillFreq());
		}
		if(DeathClaimPolicyDetails.getInstallmentPremium()!=null)
		{
			DeathClaimPolicyDetails1.setInstallmentPremium(DeathClaimPolicyDetails.getInstallmentPremium());
		}
		if(DeathClaimPolicyDetails.getExtraPremium()!=null)
		{
			DeathClaimPolicyDetails1.setExtraPremium(DeathClaimPolicyDetails.getExtraPremium());
		}
		if(DeathClaimPolicyDetails.getUinNumber()!=null)
		{
			DeathClaimPolicyDetails1.setUinNumber(DeathClaimPolicyDetails.getUinNumber());
		}
		if(DeathClaimPolicyDetails.getFup()!=null)
		{
			DeathClaimPolicyDetails1.setFup(DeathClaimPolicyDetails.getFup());
		}
		if(DeathClaimPolicyDetails.getDocDate()!=null)
		{
			DeathClaimPolicyDetails1.setDocDate(DeathClaimPolicyDetails.getDocDate());
		}
		if(DeathClaimPolicyDetails.getLaAge()!=null)
		{
			DeathClaimPolicyDetails1.setLaAge(DeathClaimPolicyDetails.getLaAge());
		}
		if(DeathClaimPolicyDetails.getPhAge()!=null)
		{
			DeathClaimPolicyDetails1.setPhAge(DeathClaimPolicyDetails.getPhAge());
		}
		if(DeathClaimPolicyDetails.getStatusCode()!=null)
		{
			DeathClaimPolicyDetails1.setStatusCode(DeathClaimPolicyDetails.getStatusCode());
		}
		if(DeathClaimPolicyDetails.getSmokerFlag()!=null)
		{
			DeathClaimPolicyDetails1.setSmokerFlag(DeathClaimPolicyDetails.getSmokerFlag());
		}
		
		PolicyDetailsRepo.save(DeathClaimPolicyDetails1);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		DeathClaimPolicyDetails DeathClaimPolicyDetails = PolicyDetailsRepo.getActiveById(id);
		DeathClaimPolicyDetails.setValidFlag(-1);
		PolicyDetailsRepo.save(DeathClaimPolicyDetails);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		PolicyDetailsRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<DeathClaimPolicyDetails> search(String key) {
		return PolicyDetailsRepo.globalSearch(key);
	}

}
