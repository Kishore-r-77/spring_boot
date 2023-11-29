package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.InterestPremiumRate;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.InterestPremiumRateRepository;

@Service
public class InterestPremiumRateService {
 
	@Autowired
	private InterestPremiumRateRepository interestPremiumRateRepository;

	@Autowired
	private ErrorService errorService;

	public List<InterestPremiumRate> getAll() {
		return interestPremiumRateRepository.getallActive();
	}

	public InterestPremiumRate getById(Long id) {
		return interestPremiumRateRepository.getActiveById(id);
	}

	public String add(InterestPremiumRate interestPremiumRate) {
		interestPremiumRate.setValidFlag(1);
		interestPremiumRateRepository.save(interestPremiumRate);
		return errorService.getErrorById("ER001");
	}

	public String update(Long id, InterestPremiumRate interestPremiumRate) {
		InterestPremiumRate interestPremiumRate1 = interestPremiumRateRepository.getActiveById(id);

		if (interestPremiumRate.getCompanyId() != null) {
			interestPremiumRate1.setCompanyId(interestPremiumRate.getCompanyId());
		}
		if (interestPremiumRate.getUinNumber() != null) {
			interestPremiumRate1.setUinNumber(interestPremiumRate.getUinNumber());
		}
		if (interestPremiumRate.getFromDate() != null) {
			interestPremiumRate1.setFromDate(interestPremiumRate.getFromDate());
		}
		if (interestPremiumRate.getToDate() != null) {
			interestPremiumRate1.setToDate(interestPremiumRate.getToDate());
		}
		if (interestPremiumRate.getRateOfInterest() != null) {
			interestPremiumRate1.setRateOfInterest(interestPremiumRate.getRateOfInterest());;
		}
		

		interestPremiumRateRepository.save(interestPremiumRate1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		InterestPremiumRate interestPremiumRate = interestPremiumRateRepository.getActiveById(id);
		interestPremiumRate.setValidFlag(-1);
		interestPremiumRateRepository.save(interestPremiumRate);
		return errorService.getErrorById("ER003");
	}

	public String hardDelete(Long id) {
		interestPremiumRateRepository.deleteById(id);
		return errorService.getErrorById("ER003");
	}

	public List<InterestPremiumRate> search(String key) {
		return interestPremiumRateRepository.globalSearch(key);
	}
}
