package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.futura.Purple.Entity.SurrenderClientDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.SurrenderClientDetailsRepository;

@Service
public class SurrenderClientDetailsService {

	
	@Autowired
	private SurrenderClientDetailsRepository detailsRepository;
	
	@Autowired
	private ErrorService errorService;
	
	public List<SurrenderClientDetails> getAll(){
		return detailsRepository.getallActive();
	}
	
	public SurrenderClientDetails getById(Long id) {
		return detailsRepository.getActiveById(id);
	}
	
	public String add(SurrenderClientDetails surrenderClientDetails) {
		surrenderClientDetails.setValidFlag(1);
		detailsRepository.save(surrenderClientDetails);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, SurrenderClientDetails surrenderClientDetails) {
		SurrenderClientDetails details = detailsRepository.getActiveById(id);
        if(surrenderClientDetails.getCompanyId() !=null) {
			details.setCompanyId(surrenderClientDetails.getCompanyId());
		}
        if(surrenderClientDetails.getClntNum() !=null) {
			details.setClntNum(surrenderClientDetails.getClntNum());
		}
        if(surrenderClientDetails.getLaName() !=null) {
			details.setLaName(surrenderClientDetails.getLaName());
		}
        if(surrenderClientDetails.getLaDob() !=null) {
			details.setLaDob(surrenderClientDetails.getLaDob());
		}
        if(surrenderClientDetails.getNationality() !=null) {
			details.setNationality(surrenderClientDetails.getNationality());
		}
        if(surrenderClientDetails.getResidentStatus() !=null) {
			details.setResidentStatus(surrenderClientDetails.getResidentStatus());
		}
        if(surrenderClientDetails.getGender() !=null) {
			details.setGender(surrenderClientDetails.getGender());
		}
        if(surrenderClientDetails.getContactNumber() !=null) {
			details.setContactNumber(surrenderClientDetails.getContactNumber());
		}
        if(surrenderClientDetails.getEmailId() !=null) {
			details.setEmailId(surrenderClientDetails.getEmailId());
		}
       
		detailsRepository.save(details);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		SurrenderClientDetails surrenderClientDetails = detailsRepository.getActiveById(id);
		surrenderClientDetails.setValidFlag(-1);
		detailsRepository.save(surrenderClientDetails);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		detailsRepository.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<SurrenderClientDetails> search(String key){
		return detailsRepository.globalSearch(key);
	}
}
