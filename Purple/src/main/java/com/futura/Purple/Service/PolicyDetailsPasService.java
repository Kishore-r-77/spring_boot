package com.futura.Purple.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.PolicyDetailsPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PolicyDetailsPasRepository;
@Service
public class PolicyDetailsPasService {
	
    @Autowired
    private PolicyDetailsPasRepository policydetailspasRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<PolicyDetailsPas> getAll(){
		return policydetailspasRepo.getallActive();
	}
	
	public List<PolicyDetailsPas> getAllByClientNo(Long clntNum){
		return policydetailspasRepo.getByClientNo(clntNum);
	}
	
	public PolicyDetailsPas getById(Long id) {
		return policydetailspasRepo.getActiveById(id);
	}
	
	public PolicyDetailsPas getByPolicyNo(Long policyNo) {
		return policydetailspasRepo.getActiveByPolicyNo(policyNo);
	}
	
	public String add(PolicyDetailsPas policydetailspas) {
		policydetailspas.setValidFlag(1);
		policydetailspasRepo.save(policydetailspas);
		return errorService.getErrorById("ER001");
		
	}
	
	public String update(Long id, PolicyDetailsPas policydetailspas) {
		PolicyDetailsPas policydetailspas1 = policydetailspasRepo.getActiveById(id);
        if(policydetailspas.getCompanyId() !=null) {
			policydetailspas1.setCompanyId(policydetailspas.getCompanyId());
		}
        if(policydetailspas.getClntNum() !=null) {
			policydetailspas1.setClntNum(policydetailspas.getClntNum());
		}
        if(policydetailspas.getChdrNum() !=null) {
			policydetailspas1.setChdrNum(policydetailspas.getChdrNum());
		}
        if(policydetailspas.getBillFreq() !=null) {
			policydetailspas1.setBillFreq(policydetailspas.getBillFreq());
		}
        if(policydetailspas.getInstallmentPremium() !=null) {
			policydetailspas1.setInstallmentPremium(policydetailspas.getInstallmentPremium());
		}
        if(policydetailspas.getPremToDate() !=null) {
			policydetailspas1.setPremToDate(policydetailspas.getPremToDate());
		}
        if(policydetailspas.getDocDate() !=null) {
			policydetailspas1.setDocDate(policydetailspas.getDocDate());
		}
        if(policydetailspas.getAnbAtCcd() !=null) {
			policydetailspas1.setAnbAtCcd(policydetailspas.getAnbAtCcd());
		}
        if(policydetailspas.getClntDob() !=null) {
			policydetailspas1.setClntDob(policydetailspas.getClntDob());
		}
        if(policydetailspas.getStatCode() !=null) {
			policydetailspas1.setStatCode(policydetailspas.getStatCode());
		}
        if(policydetailspas.getMedicalFlag() !=null) {
			policydetailspas1.setMedicalFlag(policydetailspas.getMedicalFlag());
		}
        if(policydetailspas.getSmokerFlag() !=null) {
			policydetailspas1.setSmokerFlag(policydetailspas.getSmokerFlag());
		}
		policydetailspasRepo.save(policydetailspas1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		PolicyDetailsPas policydetailspas = policydetailspasRepo.getActiveById(id);
		policydetailspas.setValidFlag(-1);
		policydetailspasRepo.save(policydetailspas);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		policydetailspasRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<PolicyDetailsPas> search(String key){
		return policydetailspasRepo.globalSearch(key);
	}
	
	

}
