package com.futura.Purple.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.SurrenderFundDetailsPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.SurrenderFundDetailsPasRepository;

@Service
public class SurrenderFundDetailsPasService {
	
//	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	@Autowired
	private SurrenderFundDetailsPasRepository fundDetailsPasRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<SurrenderFundDetailsPas> getAll(){
		return fundDetailsPasRepo.getallActive();
	}
	
	public SurrenderFundDetailsPas getById(Long id) {
		return fundDetailsPasRepo.getActiveById(id);
	}
	
	public String add(SurrenderFundDetailsPas fundDeatailsPas) {
		double rate=0;
		rate = (fundDeatailsPas.getUnits()*fundDeatailsPas.getRateApp());

		System.out.println("******** Double  Value Before ***********"+rate);
		
		BigDecimal bd = new BigDecimal(rate).setScale(2, RoundingMode.HALF_EVEN);  
		double value = bd.doubleValue();
		
		System.out.println("******** Double  Value After ***********"+value);
		fundDeatailsPas.setValue(value);
		fundDeatailsPas.setValidFlag(1);
		fundDetailsPasRepo.save(fundDeatailsPas);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, SurrenderFundDetailsPas fundDeatailsPas) {
		SurrenderFundDetailsPas fundDeatailsPas1 = fundDetailsPasRepo.getActiveById(id);
		if(fundDeatailsPas.getCompanyId()!=null) {
			fundDeatailsPas1.setCompanyId(fundDeatailsPas1.getCompanyId());
		}
		if(fundDeatailsPas.getPolicyNum()!=null) {
			fundDeatailsPas1.setPolicyNum(fundDeatailsPas.getPolicyNum());
		}
		if(fundDeatailsPas.getFundCode()!=null) {
			fundDeatailsPas1.setFundCode(fundDeatailsPas.getFundCode());
		}
		if(fundDeatailsPas.getFundName()!=null) {
			fundDeatailsPas1.setFundName(fundDeatailsPas.getFundName());
		}
		if(fundDeatailsPas.getUnits()!=null) {
			fundDeatailsPas1.setUnits(fundDeatailsPas.getUnits());
		}
		if(fundDeatailsPas.getRateApp()!=null) {
			fundDeatailsPas1.setRateApp(fundDeatailsPas.getRateApp());
		}
		double rate=0;
		rate = (fundDeatailsPas.getUnits()*fundDeatailsPas.getRateApp());
		fundDeatailsPas1.setValue(rate);
		
		fundDetailsPasRepo.save(fundDeatailsPas1);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		SurrenderFundDetailsPas fundDeatailsPas = fundDetailsPasRepo.getActiveById(id);
		fundDeatailsPas.setValidFlag(-1);
		fundDetailsPasRepo.save(fundDeatailsPas);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		fundDetailsPasRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<SurrenderFundDetailsPas> search(String key){
		return fundDetailsPasRepo.globalSearch(key);
	}
	
	public List<SurrenderFundDetailsPas> getAllByPolicyNo(Long policyNo){
		return fundDetailsPasRepo.getallByPolicy(policyNo);
	}

}
