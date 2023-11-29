package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.SurrenderCoverDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.SurrenderCoverDetailsRepository;

@Service
public class SurrenderCoverDetailsService {

	@Autowired
	private SurrenderCoverDetailsRepository surrenderCoverDetailsRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<SurrenderCoverDetails> getAll(){
		return surrenderCoverDetailsRepo.getallActive();
	}
	
	public SurrenderCoverDetails getById(Long id) {
		return surrenderCoverDetailsRepo.getActiveById(id);
	}
	
	public List<SurrenderCoverDetails> getAllByPolicy(Long policyNo){
		return surrenderCoverDetailsRepo.getAllByPolicyNo(policyNo);
	}
	
	public List<SurrenderCoverDetails> search(String key){
		return surrenderCoverDetailsRepo.globalSearch(key);
	}
	
	public String add(SurrenderCoverDetails surrenderCoverDetails) {
		surrenderCoverDetails.setValidFlag(1);
		surrenderCoverDetailsRepo.save(surrenderCoverDetails);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, SurrenderCoverDetails surrenderCoverDetails) {
		SurrenderCoverDetails surrenderCoverDetails1 = surrenderCoverDetailsRepo.getActiveById(id);
        if(surrenderCoverDetails.getId() !=null) {
			surrenderCoverDetails1.setId(surrenderCoverDetails.getId());
		}
        if(surrenderCoverDetails.getCompanyId() !=null) {
			surrenderCoverDetails1.setCompanyId(surrenderCoverDetails.getCompanyId());
		}
        if(surrenderCoverDetails.getClntNum() !=null) {
			surrenderCoverDetails1.setClntNum(surrenderCoverDetails.getClntNum());
		}
        if(surrenderCoverDetails.getPolicyNo() !=null) {
			surrenderCoverDetails1.setPolicyNo(surrenderCoverDetails.getPolicyNo());
		}
        if(surrenderCoverDetails.getPlanName() !=null) {
			surrenderCoverDetails1.setPlanName(surrenderCoverDetails.getPlanName());
		}
        if(surrenderCoverDetails.getPlanCode() !=null) {
			surrenderCoverDetails1.setPlanCode(surrenderCoverDetails.getPlanCode());
		}
        if(surrenderCoverDetails.getUinNumber() !=null) {
			surrenderCoverDetails1.setUinNumber(surrenderCoverDetails.getUinNumber());
		}
        if(surrenderCoverDetails.getRiskComDate() !=null) {
			surrenderCoverDetails1.setRiskComDate(surrenderCoverDetails.getRiskComDate());
		}
        if(surrenderCoverDetails.getDocDate() !=null) {
			surrenderCoverDetails1.setDocDate(surrenderCoverDetails.getDocDate());
		}
        if(surrenderCoverDetails.getSumAssured() !=null) {
			surrenderCoverDetails1.setSumAssured(surrenderCoverDetails.getSumAssured());
		}
        if(surrenderCoverDetails.getPolicyTerm() !=null) {
			surrenderCoverDetails1.setPolicyTerm(surrenderCoverDetails.getPolicyTerm());
		}
        if(surrenderCoverDetails.getPremiumTerm() !=null) {
			surrenderCoverDetails1.setPremiumTerm(surrenderCoverDetails.getPremiumTerm());
		}
        if(surrenderCoverDetails.getCoverPremium() !=null) {
			surrenderCoverDetails1.setCoverPremium(surrenderCoverDetails.getCoverPremium());
		}
        if(surrenderCoverDetails.getCoverStatus() !=null) {
			surrenderCoverDetails1.setCoverStatus(surrenderCoverDetails.getCoverStatus());
		}
		surrenderCoverDetailsRepo.save(surrenderCoverDetails1);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
	 SurrenderCoverDetails cover=surrenderCoverDetailsRepo.getActiveById(id);
	 cover.setValidFlag(-1);
	 surrenderCoverDetailsRepo.save(cover);
	 return errorService.getErrorById("ER003");
	}

}
