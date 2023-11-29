package com.futura.Purple.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.FundDetailsPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.FundDetailsPasRepository;

@Service
public class FundDetailsPasService {
	
//	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	@Autowired
	private FundDetailsPasRepository fundDetailsPasRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<FundDetailsPas> getAll(){
		return fundDetailsPasRepo.getallActive();
	}
	
	public FundDetailsPas getById(Long id) {
		return fundDetailsPasRepo.getActiveById(id);
	}
	
	public String add(FundDetailsPas fundDeatailsPas) {
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
	
	public String update(Long id, FundDetailsPas fundDeatailsPas) {
		FundDetailsPas fundDeatailsPas1 = fundDetailsPasRepo.getActiveById(id);
		if(fundDeatailsPas.getCompanyId()!=null) {
			fundDeatailsPas1.setCompanyId(fundDeatailsPas1.getCompanyId());
		}
		if(fundDeatailsPas.getChdrNum()!=null) {
			fundDeatailsPas1.setChdrNum(fundDeatailsPas.getChdrNum());
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
		FundDetailsPas fundDeatailsPas = fundDetailsPasRepo.getActiveById(id);
		fundDeatailsPas.setValidFlag(-1);
		fundDetailsPasRepo.save(fundDeatailsPas);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		fundDetailsPasRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<FundDetailsPas> search(String key){
		return fundDetailsPasRepo.globalSearch(key);
	}
	
	public List<FundDetailsPas> getAllByPolicyNo(Long policyNo){
		return fundDetailsPasRepo.getallByPolicy(policyNo);
	}

}
