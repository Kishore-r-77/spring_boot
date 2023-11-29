package com.futura.Purple.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.CoverDetailsPas;
import com.futura.Purple.Entity.TransactionPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.CoverDetailsPasRepository;
import com.futura.Purple.Repository.TransactionPasRepository;
@Service
public class CoverDetailsPasService {
	
    @Autowired
    private CoverDetailsPasRepository coverdetailspasRepo;
    
    @Autowired
    private TransactionPasRepository transRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<CoverDetailsPas> getAll(){
		return coverdetailspasRepo.getallActive();
	}
	
	public CoverDetailsPas getById(Long id) {
		return coverdetailspasRepo.getActiveById(id);
	}
	
	public String add(CoverDetailsPas coverdetailspas) {
		coverdetailspas.setValidFlag(1);
		coverdetailspasRepo.save(coverdetailspas);
		return errorService.getErrorById("ER001");
		
	}
	
	public List<CoverDetailsPas> getAllByPolicy(Long chdrNum){
		return coverdetailspasRepo.getAllByPolicyNo(chdrNum);
	}
	
	public void calculate(Long policyNo) {
		TransactionPas trans = transRepo.findByFlcPolicyNo(policyNo);
	}
	
	public String update(Long id, CoverDetailsPas coverdetailspas) {
		CoverDetailsPas coverdetailspas1 = coverdetailspasRepo.getActiveById(id);
        if(coverdetailspas.getId() !=null) {
			coverdetailspas1.setId(coverdetailspas.getId());
		}
        if(coverdetailspas.getCompanyId() !=null) {
			coverdetailspas1.setCompanyId(coverdetailspas.getCompanyId());
		}
        if(coverdetailspas.getClntNum() !=null) {
			coverdetailspas1.setClntNum(coverdetailspas.getClntNum());
		}
        if(coverdetailspas.getChdrNum() !=null) {
			coverdetailspas1.setChdrNum(coverdetailspas.getChdrNum());
		}
        if(coverdetailspas.getCntType() !=null) {
			coverdetailspas1.setCntType(coverdetailspas.getCntType());
		}
        if(coverdetailspas.getCrTable() !=null) {
			coverdetailspas1.setCrTable(coverdetailspas.getCrTable());
		}
        if(coverdetailspas.getUinNumber() !=null) {
			coverdetailspas1.setUinNumber(coverdetailspas.getUinNumber());
		}
        if(coverdetailspas.getRiskComDate() !=null) {
			coverdetailspas1.setRiskComDate(coverdetailspas.getRiskComDate());
		}
        if(coverdetailspas.getDocDate() !=null) {
			coverdetailspas1.setDocDate(coverdetailspas.getDocDate());
		}
        if(coverdetailspas.getSumAssured() !=null) {
			coverdetailspas1.setSumAssured(coverdetailspas.getSumAssured());
		}
        if(coverdetailspas.getRiskCessTerm() !=null) {
			coverdetailspas1.setRiskCessTerm(coverdetailspas.getRiskCessTerm());
		}
        if(coverdetailspas.getPremCessTerm() !=null) {
			coverdetailspas1.setPremCessTerm(coverdetailspas.getPremCessTerm());
		}
        if(coverdetailspas.getCoverPremium() !=null) {
			coverdetailspas1.setCoverPremium(coverdetailspas.getCoverPremium());
		}
        if(coverdetailspas.getCoverStatus() !=null) {
			coverdetailspas1.setCoverStatus(coverdetailspas.getCoverStatus());
		}
//        if(coverdetailspas.getMortFlag() !=null) {
//			coverdetailspas1.setMortFlag(coverdetailspas.getMortFlag());
//		}
//        if(coverdetailspas.getStampRate() !=null) {
//			coverdetailspas1.setStampRate(coverdetailspas.getStampRate());
//		}
		coverdetailspasRepo.save(coverdetailspas1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		CoverDetailsPas coverdetailspas = coverdetailspasRepo.getActiveById(id);
		coverdetailspas.setValidFlag(-1);
		coverdetailspasRepo.save(coverdetailspas);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		coverdetailspasRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<CoverDetailsPas> search(String key){
		return coverdetailspasRepo.globalSearch(key);
	}

}
