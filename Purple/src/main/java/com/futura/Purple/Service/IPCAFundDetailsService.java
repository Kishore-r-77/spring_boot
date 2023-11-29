package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.IPCAFundDetails;
import com.futura.Purple.Entity.PurpleFundDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.IPCAFundDetailsRepository;

@Service
public class IPCAFundDetailsService {
	
	@Autowired
	private IPCAFundDetailsRepository purpleFundDetailsRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<IPCAFundDetails> getAll(){
		return purpleFundDetailsRepo.getallActive();
	}
	
	public IPCAFundDetails getById(Long id) {
		return purpleFundDetailsRepo.getActiveById(id);
	}
	
	public String add(IPCAFundDetails purpleFundDetails) {
		purpleFundDetails.setValidFlag(1);
		purpleFundDetailsRepo.save(purpleFundDetails);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, IPCAFundDetails purpleFundDetails) {
		IPCAFundDetails purpleFundDetails1 = purpleFundDetailsRepo.getActiveById(id);
		if(purpleFundDetails.getCompanyId()!=null) {
			purpleFundDetails1.setCompanyId(purpleFundDetails.getCompanyId());
		}
		if(purpleFundDetails.getPolicyNo()!=null) {
			purpleFundDetails1.setPolicyNo(purpleFundDetails.getPolicyNo());
		}
		if(purpleFundDetails.getPurpleFundCode()!=null) {
			purpleFundDetails1.setPurpleFundCode(purpleFundDetails.getPurpleFundCode());
		}
		if(purpleFundDetails.getPurpleFundName()!=null) {
			purpleFundDetails1.setPurpleFundName(purpleFundDetails.getPurpleFundName());
		}
		if(purpleFundDetails.getPurpleUnits()!=null) {
			purpleFundDetails1.setPurpleUnits(purpleFundDetails.getPurpleUnits());
		}
		if(purpleFundDetails.getPurpleRateApp()!=null) {
			purpleFundDetails1.setPurpleRateApp(purpleFundDetails.getPurpleRateApp());
		}
		if(purpleFundDetails.getRemark()!=null) {
			purpleFundDetails1.setRemark(purpleFundDetails.getRemark());
		}
		
		purpleFundDetailsRepo.save(purpleFundDetails1);
		return errorService.getErrorById("ER001");
	}
	
	public String deactivate(Long id) {
		IPCAFundDetails purpleFundDetails = purpleFundDetailsRepo.getActiveById(id);
		purpleFundDetails.setValidFlag(-1);
		purpleFundDetailsRepo.save(purpleFundDetails);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		purpleFundDetailsRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<IPCAFundDetails> search(String key){
		return purpleFundDetailsRepo.globalSearch(key);
	}
	
	public List<IPCAFundDetails> getAllByPolicyNo(Long policyNo){
		return purpleFundDetailsRepo.getallByPolicyNo(policyNo);
	}

}
