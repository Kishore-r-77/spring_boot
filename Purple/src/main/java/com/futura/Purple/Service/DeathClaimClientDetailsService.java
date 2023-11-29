package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.DeathClaimClientDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimClientDetailsRepository;

@Service
public class DeathClaimClientDetailsService {

	
	@Autowired
	private DeathClaimClientDetailsRepository detailsRepository;
	
	@Autowired
	private ErrorService errorService;
	
	public List<DeathClaimClientDetails> getAll(){
		return detailsRepository.getallActive();
	}
	
	public DeathClaimClientDetails getById(Long id) {
		return detailsRepository.getActiveById(id);
	}
	
	public String add(DeathClaimClientDetails DeathClaimClientDetails) {
		DeathClaimClientDetails.setValidFlag(1);
		detailsRepository.save(DeathClaimClientDetails);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, DeathClaimClientDetails DeathClaimClientDetails) {
		DeathClaimClientDetails details = detailsRepository.getActiveById(id);
        if(DeathClaimClientDetails.getCompanyId() !=null) {
			details.setCompanyId(DeathClaimClientDetails.getCompanyId());
		}
        if(DeathClaimClientDetails.getClntNum() !=null) {
			details.setClntNum(DeathClaimClientDetails.getClntNum());
		}
        if(DeathClaimClientDetails.getLaName() !=null) {
			details.setLaName(DeathClaimClientDetails.getLaName());
		}
        if(DeathClaimClientDetails.getLaDob() !=null) {
			details.setLaDob(DeathClaimClientDetails.getLaDob());
		}
        if(DeathClaimClientDetails.getNationality() !=null) {
			details.setNationality(DeathClaimClientDetails.getNationality());
		}
        if(DeathClaimClientDetails.getResidentStatus() !=null) {
			details.setResidentStatus(DeathClaimClientDetails.getResidentStatus());
		}
        if(DeathClaimClientDetails.getGender() !=null) {
			details.setGender(DeathClaimClientDetails.getGender());
		}
        if(DeathClaimClientDetails.getContactNumber() !=null) {
			details.setContactNumber(DeathClaimClientDetails.getContactNumber());
		}
        if(DeathClaimClientDetails.getEmailId() !=null) {
			details.setEmailId(DeathClaimClientDetails.getEmailId());
		}
       
		detailsRepository.save(details);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		DeathClaimClientDetails DeathClaimClientDetails = detailsRepository.getActiveById(id);
		DeathClaimClientDetails.setValidFlag(-1);
		detailsRepository.save(DeathClaimClientDetails);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		detailsRepository.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<DeathClaimClientDetails> search(String key){
		return detailsRepository.globalSearch(key);
	}
}
