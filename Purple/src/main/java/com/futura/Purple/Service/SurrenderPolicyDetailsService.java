package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.SurrenderPolicyDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.SurrenderPolicyDetailsRepository;

@Service
public class SurrenderPolicyDetailsService {
	
	@Autowired
	private SurrenderPolicyDetailsRepository surrenderPolicyDetailsRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<SurrenderPolicyDetails> getAll(){
		return surrenderPolicyDetailsRepo.getallActive();
	}
	
	public SurrenderPolicyDetails getByPolicyNo(Long policyNo) {
		return surrenderPolicyDetailsRepo.getActiveByPolicyNo(policyNo);
	}
	
	public SurrenderPolicyDetails getById(Long id) {
		return surrenderPolicyDetailsRepo.getActiveById(id);
	}
	
	public String add(SurrenderPolicyDetails surrenderpolicydetails) {
		surrenderpolicydetails.setValidFlag(1);
		surrenderPolicyDetailsRepo.save(surrenderpolicydetails);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id,SurrenderPolicyDetails surrenderpolicydetails) {
		SurrenderPolicyDetails surrenderpolicydetails1 = surrenderPolicyDetailsRepo.getActiveById(id);
		if(surrenderpolicydetails.getCompanyId()!=null)
		{
			surrenderpolicydetails1.setCompanyId(surrenderpolicydetails.getCompanyId());
		}
		if(surrenderpolicydetails.getClntNum()!=null)
		{
			surrenderpolicydetails1.setClntNum(surrenderpolicydetails.getClntNum());
		}
		if(surrenderpolicydetails.getChdrNum()!=null)
		{
			surrenderpolicydetails1.setChdrNum(surrenderpolicydetails.getChdrNum());
		}
		if(surrenderpolicydetails.getBillFreq()!=null)
		{
			surrenderpolicydetails1.setBillFreq(surrenderpolicydetails.getBillFreq());
		}
		if(surrenderpolicydetails.getInstallmentPremium()!=null)
		{
			surrenderpolicydetails1.setInstallmentPremium(surrenderpolicydetails.getInstallmentPremium());
		}
		if(surrenderpolicydetails.getExtraPremium()!=null)
		{
			surrenderpolicydetails1.setExtraPremium(surrenderpolicydetails.getExtraPremium());
		}
		if(surrenderpolicydetails.getUinNumber()!=null)
		{
			surrenderpolicydetails1.setUinNumber(surrenderpolicydetails.getUinNumber());
		}
		if(surrenderpolicydetails.getFup()!=null)
		{
			surrenderpolicydetails1.setFup(surrenderpolicydetails.getFup());
		}
		if(surrenderpolicydetails.getDocDate()!=null)
		{
			surrenderpolicydetails1.setDocDate(surrenderpolicydetails.getDocDate());
		}
		if(surrenderpolicydetails.getLaAge()!=null)
		{
			surrenderpolicydetails1.setLaAge(surrenderpolicydetails.getLaAge());
		}
		if(surrenderpolicydetails.getPhAge()!=null)
		{
			surrenderpolicydetails1.setPhAge(surrenderpolicydetails.getPhAge());
		}
		if(surrenderpolicydetails.getStatusCode()!=null)
		{
			surrenderpolicydetails1.setStatusCode(surrenderpolicydetails.getStatusCode());
		}
		if(surrenderpolicydetails.getSmokerFlag()!=null)
		{
			surrenderpolicydetails1.setSmokerFlag(surrenderpolicydetails.getSmokerFlag());
		}
		
		surrenderPolicyDetailsRepo.save(surrenderpolicydetails1);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		SurrenderPolicyDetails surrenderpolicydetails = surrenderPolicyDetailsRepo.getActiveById(id);
		surrenderpolicydetails.setValidFlag(-1);
		surrenderPolicyDetailsRepo.save(surrenderpolicydetails);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		surrenderPolicyDetailsRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<SurrenderPolicyDetails> search(String key) {
		return surrenderPolicyDetailsRepo.globalSearch(key);
	}

}
