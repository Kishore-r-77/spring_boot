package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.DeathClaimLeapParameter;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimLeapParameterRepository;

@Service
public class DeathClaimLeapParameterService {
	
	@Autowired
	private DeathClaimLeapParameterRepository detailsRepository;
	
	@Autowired
	private ErrorService errorService;
	
	public List<DeathClaimLeapParameter> getAll(){
		return detailsRepository.getallActive();
	}
	
	public DeathClaimLeapParameter getById(Long id) {
		return detailsRepository.getActiveById(id);
	}
	
	public String add(DeathClaimLeapParameter deathClaimLeapParameter) {
		deathClaimLeapParameter.setValidFlag(1);
		detailsRepository.save(deathClaimLeapParameter);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, DeathClaimLeapParameter deathClaimLeapParameter) {
		DeathClaimLeapParameter details = detailsRepository.getActiveById(id);
        if(deathClaimLeapParameter.getCompanyId() !=null) {
			details.setCompanyId(deathClaimLeapParameter.getCompanyId());
		}
        if(deathClaimLeapParameter.getBasicSa() !=null) {
			details.setBasicSa(deathClaimLeapParameter.getBasicSa());
		}
        if(deathClaimLeapParameter.getIncreaseSaYears() !=null) {
			details.setIncreaseSaYears(deathClaimLeapParameter.getIncreaseSaYears());
		}
        if(deathClaimLeapParameter.getPercentageSaIncrease() !=null) {
			details.setPercentageSaIncrease(deathClaimLeapParameter.getPercentageSaIncrease());
		}
        if(deathClaimLeapParameter.getLoyaltyBonus() !=null) {
			details.setLoyaltyBonus(deathClaimLeapParameter.getLoyaltyBonus());
		}
        if(deathClaimLeapParameter.getGuaranteedBonus() !=null) {
			details.setGuaranteedBonus(deathClaimLeapParameter.getGuaranteedBonus());
		}
        if(deathClaimLeapParameter.getTerminalBonus() !=null) {
			details.setTerminalBonus(deathClaimLeapParameter.getTerminalBonus());
		}
        if(deathClaimLeapParameter.getSuicideClause() !=null) {
			details.setSuicideClause(deathClaimLeapParameter.getSuicideClause());
		}
        if(deathClaimLeapParameter.getWaitingPeriod() !=null) {
			details.setWaitingPeriod(deathClaimLeapParameter.getWaitingPeriod());
		}
        if(deathClaimLeapParameter.getRefundOfAdminFee() !=null) {
			details.setRefundOfAdminFee(deathClaimLeapParameter.getRefundOfAdminFee());
		}
        if(deathClaimLeapParameter.getRefundOfMc() !=null) {
			details.setRefundOfMc(deathClaimLeapParameter.getRefundOfMc());
		}
        if(deathClaimLeapParameter.getRefundOfGuaranteedCharges() !=null) {
			details.setRefundOfGuaranteedCharges(deathClaimLeapParameter.getRefundOfGuaranteedCharges());
		}
        
        if(deathClaimLeapParameter.getReturnOfPremiums() !=null) {
			details.setReturnOfPremiums(deathClaimLeapParameter.getReturnOfPremiums());
		}
        if(deathClaimLeapParameter.getFundvalueSaPayable() !=null) {
			details.setFundvalueSaPayable(deathClaimLeapParameter.getFundvalueSaPayable());
		}
        if(deathClaimLeapParameter.getClaimConcession() !=null) {
			details.setClaimConcession(deathClaimLeapParameter.getClaimConcession());
		}
        if(deathClaimLeapParameter.getTdsType() !=null) {
			details.setTdsType(deathClaimLeapParameter.getTdsType());
		}
        if(deathClaimLeapParameter.getTdsRate() !=null) {
			details.setTdsRate(deathClaimLeapParameter.getTdsRate());
		}
       
		detailsRepository.save(details);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		DeathClaimLeapParameter DeathClaimLeapParameter = detailsRepository.getActiveById(id);
		DeathClaimLeapParameter.setValidFlag(-1);
		detailsRepository.save(DeathClaimLeapParameter);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		detailsRepository.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<DeathClaimLeapParameter> search(String key){
		return detailsRepository.globalSearch(key);
	}

}
