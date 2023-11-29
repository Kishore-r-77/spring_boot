package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.DeathClaimCoverDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimCoverDetailsRepository;

@Service
public class DeathClaimCoverDetailsService {

	@Autowired
	private DeathClaimCoverDetailsRepository coverDetailsRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<DeathClaimCoverDetails> getAll(){
		return coverDetailsRepo.getallActive();
	}
	
	public DeathClaimCoverDetails getById(Long id) {
		return coverDetailsRepo.getActiveById(id);
	}
	
	public List<DeathClaimCoverDetails> getAllByPolicy(Long policyNo){
		return coverDetailsRepo.getAllByPolicyNo(policyNo);
	}
	
	public DeathClaimCoverDetails getByUinNo(String uinNo) {
		return coverDetailsRepo.findByuinNo(uinNo);
	}
	
	public List<DeathClaimCoverDetails> search(String key){
		return coverDetailsRepo.globalSearch(key);
	}
	
	public String add(DeathClaimCoverDetails DeathClaimCoverDetails) {
		DeathClaimCoverDetails.setValidFlag(1);
		coverDetailsRepo.save(DeathClaimCoverDetails);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, DeathClaimCoverDetails DeathClaimCoverDetails) {
		DeathClaimCoverDetails DeathClaimCoverDetails1 = coverDetailsRepo.getActiveById(id);
        if(DeathClaimCoverDetails.getId() !=null) {
			DeathClaimCoverDetails1.setId(DeathClaimCoverDetails.getId());
		}
        if(DeathClaimCoverDetails.getCompanyId() !=null) {
			DeathClaimCoverDetails1.setCompanyId(DeathClaimCoverDetails.getCompanyId());
		}
        if(DeathClaimCoverDetails.getClntNum() !=null) {
			DeathClaimCoverDetails1.setClntNum(DeathClaimCoverDetails.getClntNum());
		}
        if(DeathClaimCoverDetails.getPolicyNo() !=null) {
			DeathClaimCoverDetails1.setPolicyNo(DeathClaimCoverDetails.getPolicyNo());
		}
        if(DeathClaimCoverDetails.getPlanName() !=null) {
			DeathClaimCoverDetails1.setPlanName(DeathClaimCoverDetails.getPlanName());
		}
        if(DeathClaimCoverDetails.getPlanCode() !=null) {
			DeathClaimCoverDetails1.setPlanCode(DeathClaimCoverDetails.getPlanCode());
		}
        if(DeathClaimCoverDetails.getUinNumber() !=null) {
			DeathClaimCoverDetails1.setUinNumber(DeathClaimCoverDetails.getUinNumber());
		}
        if(DeathClaimCoverDetails.getRiskComDate() !=null) {
			DeathClaimCoverDetails1.setRiskComDate(DeathClaimCoverDetails.getRiskComDate());
		}
        if(DeathClaimCoverDetails.getDocDate() !=null) {
			DeathClaimCoverDetails1.setDocDate(DeathClaimCoverDetails.getDocDate());
		}
        if(DeathClaimCoverDetails.getSumAssured() !=null) {
			DeathClaimCoverDetails1.setSumAssured(DeathClaimCoverDetails.getSumAssured());
		}
        if(DeathClaimCoverDetails.getTermRiderSumAssured() !=null) {
			DeathClaimCoverDetails1.setTermRiderSumAssured(DeathClaimCoverDetails.getTermRiderSumAssured());
		}
        if(DeathClaimCoverDetails.getPolicyTerm() !=null) {
			DeathClaimCoverDetails1.setPolicyTerm(DeathClaimCoverDetails.getPolicyTerm());
		}
        if(DeathClaimCoverDetails.getPremiumTerm() !=null) {
			DeathClaimCoverDetails1.setPremiumTerm(DeathClaimCoverDetails.getPremiumTerm());
		}
        if(DeathClaimCoverDetails.getCoverPremium() !=null) {
			DeathClaimCoverDetails1.setCoverPremium(DeathClaimCoverDetails.getCoverPremium());
		}
        if(DeathClaimCoverDetails.getCoverStatus() !=null) {
			DeathClaimCoverDetails1.setCoverStatus(DeathClaimCoverDetails.getCoverStatus());
		}
		coverDetailsRepo.save(DeathClaimCoverDetails1);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
	 DeathClaimCoverDetails cover=coverDetailsRepo.getActiveById(id);
	 cover.setValidFlag(-1);
	 coverDetailsRepo.save(cover);
	 return errorService.getErrorById("ER003");
	}

}
