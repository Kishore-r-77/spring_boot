package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.DeathClaimClientDetails;
import com.futura.Purple.Entity.DeathClaimCoverTablePas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimCoverTablePasRepository;

@Service
public class DeathClaimCoverTablePasService {

	@Autowired
	private ErrorService errorService;
	
	@Autowired
	private DeathClaimCoverTablePasRepository coverTablePasRepo;
	
	public List<DeathClaimCoverTablePas> getAll(){
		return coverTablePasRepo.getallActive();
	}
	
	public DeathClaimCoverTablePas getById(Long id) {
		return coverTablePasRepo.getById(id);
	}
	
	public DeathClaimCoverTablePas getByUinNo(String uinNo) {
		return coverTablePasRepo.getActiveByUinNo(uinNo);
	}
	
	public String add(DeathClaimCoverTablePas deathClaimCoverTablePas) {
		
		deathClaimCoverTablePas.setValidFlag(1);
		coverTablePasRepo.save(deathClaimCoverTablePas);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, DeathClaimCoverTablePas dcctp) {
		
		DeathClaimCoverTablePas dcctp1 = coverTablePasRepo.getActiveById(id);
		
		if(dcctp.getCompanyId() != null) {
			dcctp1.setCompanyId(dcctp.getCompanyId());
		}
		
		if(dcctp.getUinNumber() != null) {
			dcctp1.setUinNumber(dcctp.getUinNumber());
		}
		
		if(dcctp.getDoc() != null) {
			dcctp1.setDoc(dcctp.getDoc());
		}
		
		if(dcctp.getRiskComDate() != null) {
			dcctp1.setRiskComDate(dcctp.getRiskComDate());
		}
		
		if(dcctp.getOriginalSumAssured() != null) {
			dcctp1.setOriginalSumAssured(dcctp.getOriginalSumAssured());
		}
		
		if(dcctp.getPasSumAssured() != null) {
			dcctp1.setPasSumAssured(dcctp.getPasSumAssured());
		}
		
		if(dcctp.getFlag() != null) {
			dcctp1.setFlag(dcctp.getFlag());
		}
		
		if(dcctp.getRemark() != null) {
			dcctp1.setRemark(dcctp.getRemark());
		}
		
		coverTablePasRepo.save(dcctp1);
		return errorService.getErrorById("ER003");
	}
	
	
	public String deactivate(Long id) {
		DeathClaimCoverTablePas dcctp = coverTablePasRepo.getActiveById(id);
		dcctp.setValidFlag(-1);
		coverTablePasRepo.save(dcctp);
		return errorService.getErrorById("ER012");
	}
	
	
	public List<DeathClaimCoverTablePas> search(String key){
		return coverTablePasRepo.globalSearch(key);
	}
	
}
