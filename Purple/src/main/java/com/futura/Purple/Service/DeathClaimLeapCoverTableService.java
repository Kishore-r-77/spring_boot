package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.DeathClaimLeapCoverTable;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimLeapCoverTableRepository;

@Service
public class DeathClaimLeapCoverTableService {
	@Autowired
	private ErrorService errorService;
	
	@Autowired
	private DeathClaimLeapCoverTableRepository leapCoverTableRepo;
	
	public List<DeathClaimLeapCoverTable> getAll(){
		return leapCoverTableRepo.getallActive();
	}
	
	public DeathClaimLeapCoverTable getById(Long id) {
		return leapCoverTableRepo.getById(id);
	}
	
	public DeathClaimLeapCoverTable getByUinNo(String uinNo) {
		return leapCoverTableRepo.getActiveByUinNo(uinNo);
	}
	
	public String add(DeathClaimLeapCoverTable DeathClaimLeapCoverTable) {
		
		DeathClaimLeapCoverTable.setValidFlag(1);
		leapCoverTableRepo.save(DeathClaimLeapCoverTable);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, DeathClaimLeapCoverTable dclct) {
		
		DeathClaimLeapCoverTable dclct1 = leapCoverTableRepo.getActiveById(id);
		
		if(dclct.getCompanyId() != null) {
			dclct1.setCompanyId(dclct.getCompanyId());
		}
		
		if(dclct.getUinNumber() != null) {
			dclct1.setUinNumber(dclct.getUinNumber());
		}
		
		if(dclct.getDoc() != null) {
			dclct1.setDoc(dclct.getDoc());
		}
		
		if(dclct.getRiskComDate() != null) {
			dclct1.setRiskComDate(dclct.getRiskComDate());
		}
		
		if(dclct.getOriginalSumAssured() != null) {
			dclct1.setOriginalSumAssured(dclct.getOriginalSumAssured());
		}
		
		if(dclct.getPasSumAssured() != null) {
			dclct1.setPasSumAssured(dclct.getPasSumAssured());
		}
		if(dclct.getLeapSumAssured() != null) {
			dclct1.setLeapSumAssured(dclct.getLeapSumAssured());
		}
		
		if(dclct.getLeapFlag() != null) {
			dclct1.setLeapFlag(dclct.getLeapFlag());
		}
		
		if(dclct.getLeapRemark() != null) {
			dclct1.setLeapRemark(dclct.getLeapRemark());
		}
		
		if(dclct.getQcRemark() != null) {
			dclct1.setQcRemark(dclct.getQcRemark());
		}
		
		leapCoverTableRepo.save(dclct1);
		return errorService.getErrorById("ER003");
	}
	
	
	public String deactivate(Long id) {
		DeathClaimLeapCoverTable dclct = leapCoverTableRepo.getActiveById(id);
		dclct.setValidFlag(-1);
		leapCoverTableRepo.save(dclct);
		return errorService.getErrorById("ER012");
	}
	
	
	public List<DeathClaimLeapCoverTable> search(String key){
		return leapCoverTableRepo.globalSearch(key);
	}
}
