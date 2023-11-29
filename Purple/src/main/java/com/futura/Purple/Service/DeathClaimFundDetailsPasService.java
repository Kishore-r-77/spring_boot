package com.futura.Purple.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.DeathClaimFundDetailsPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimFundDetailsPasRepository;

@Service
public class DeathClaimFundDetailsPasService {
	
//	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	@Autowired
	private DeathClaimFundDetailsPasRepository deathClaimFundDetailsRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<DeathClaimFundDetailsPas> getAll(){
		return deathClaimFundDetailsRepo.getallActive();
	}
	
	public DeathClaimFundDetailsPas getById(Long id) {
		return deathClaimFundDetailsRepo.getActiveById(id);
	}
	
	public String add(DeathClaimFundDetailsPas deathClaimFundDetailsPas) {
		double rate=0;
		rate = (deathClaimFundDetailsPas.getUnits()*deathClaimFundDetailsPas.getRateApp());

		System.out.println("******** Double  Value Before ***********"+rate);
		
		BigDecimal bd = new BigDecimal(rate).setScale(2, RoundingMode.HALF_EVEN);  
		double value = bd.doubleValue();
		
		System.out.println("******** Double  Value After ***********"+value);
		deathClaimFundDetailsPas.setValue(value);
		deathClaimFundDetailsPas.setValidFlag(1);
		deathClaimFundDetailsRepo.save(deathClaimFundDetailsPas);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, DeathClaimFundDetailsPas deathClaimFundDetailsPas) {
		DeathClaimFundDetailsPas deathClaimFundDetailsPas1 = deathClaimFundDetailsRepo.getActiveById(id);
		if(deathClaimFundDetailsPas.getCompanyId()!=null) {
			deathClaimFundDetailsPas1.setCompanyId(deathClaimFundDetailsPas1.getCompanyId());
		}
		if(deathClaimFundDetailsPas.getPolicyNum()!=null) {
			deathClaimFundDetailsPas1.setPolicyNum(deathClaimFundDetailsPas.getPolicyNum());
		}
		if(deathClaimFundDetailsPas.getFundCode()!=null) {
			deathClaimFundDetailsPas1.setFundCode(deathClaimFundDetailsPas.getFundCode());
		}
		if(deathClaimFundDetailsPas.getFundName()!=null) {
			deathClaimFundDetailsPas1.setFundName(deathClaimFundDetailsPas.getFundName());
		}
		if(deathClaimFundDetailsPas.getUnits()!=null) {
			deathClaimFundDetailsPas1.setUnits(deathClaimFundDetailsPas.getUnits());
		}
		if(deathClaimFundDetailsPas.getRateApp()!=null) {
			deathClaimFundDetailsPas1.setRateApp(deathClaimFundDetailsPas.getRateApp());
		}
		double rate=0;
		rate = (deathClaimFundDetailsPas.getUnits()*deathClaimFundDetailsPas.getRateApp());
		deathClaimFundDetailsPas1.setValue(rate);
		
		deathClaimFundDetailsRepo.save(deathClaimFundDetailsPas1);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		DeathClaimFundDetailsPas deathClaimFundDetailsPas = deathClaimFundDetailsRepo.getActiveById(id);
		deathClaimFundDetailsPas.setValidFlag(-1);
		deathClaimFundDetailsRepo.save(deathClaimFundDetailsPas);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		deathClaimFundDetailsRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<DeathClaimFundDetailsPas> search(String key){
		return deathClaimFundDetailsRepo.globalSearch(key);
	}
	
	public List<DeathClaimFundDetailsPas> getAllByPolicyNo(Long policyNo){
		return deathClaimFundDetailsRepo.getallByPolicy(policyNo);
	}

}
