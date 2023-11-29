package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.PurpleFundDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PurpleFundDetailsRepository;

@Service
public class PurpleFundDetailsService {
	
	@Autowired
	private PurpleFundDetailsRepository purpleFundDetailsRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<PurpleFundDetails> getAll(){
		return purpleFundDetailsRepo.getallActive();
	}
	
	public PurpleFundDetails getById(Long id) {
		return purpleFundDetailsRepo.getActiveById(id);
	}
	
	public String add(PurpleFundDetails purpleFundDetails) {
		purpleFundDetails.setValidFlag(1);
		purpleFundDetailsRepo.save(purpleFundDetails);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, PurpleFundDetails purpleFundDetails) {
		PurpleFundDetails purpleFundDetails1 = purpleFundDetailsRepo.getActiveById(id);
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
		PurpleFundDetails purpleFundDetails = purpleFundDetailsRepo.getActiveById(id);
		purpleFundDetails.setValidFlag(-1);
		purpleFundDetailsRepo.save(purpleFundDetails);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		purpleFundDetailsRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<PurpleFundDetails> search(String key){
		return purpleFundDetailsRepo.globalSearch(key);
	}
	
	public List<PurpleFundDetails> getAllByPolicyNo(Long policyNo){
		return purpleFundDetailsRepo.getallByPolicyNo(policyNo);
	}

}
