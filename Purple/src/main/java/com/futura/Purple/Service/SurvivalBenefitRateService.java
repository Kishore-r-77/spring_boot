package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.SurvivalBenefitRate;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.SurvivalBenefitRateRepository;

@Service
public class SurvivalBenefitRateService {

	@Autowired
	private SurvivalBenefitRateRepository survivalBenefitRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<SurvivalBenefitRate> getAll() {
		return survivalBenefitRepo.getallActive();
	}

	public SurvivalBenefitRate getById(Long id) {
		return survivalBenefitRepo.getActiveById(id);
	}

	public String add(SurvivalBenefitRate benefitRate) {
		benefitRate.setValidFlag(1);
		survivalBenefitRepo.save(benefitRate);
		return errorService.getErrorById("ER001");
	}

	public String update(Long id, SurvivalBenefitRate benefitRate) {
		SurvivalBenefitRate benefitRate1 = survivalBenefitRepo.getActiveById(id);

		if (benefitRate.getCompanyId()!= null) {
			benefitRate.setCompanyId(benefitRate.getCompanyId());
		}
		if (benefitRate.getProductCode()!= null) {
			benefitRate1.setProductCode(benefitRate.getProductCode());
		}
		if (benefitRate.getPolicyYears()!= null) {
			benefitRate1.setPolicyYears(benefitRate.getPolicyYears());
		}
		if (benefitRate.getBenefitRate()!= null) {
			benefitRate1.setBenefitRate(benefitRate.getBenefitRate());
		}

		survivalBenefitRepo.save(benefitRate1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		SurvivalBenefitRate benefitRate = survivalBenefitRepo.getActiveById(id);
		benefitRate.setValidFlag(-1);
		survivalBenefitRepo.save(benefitRate);
		return errorService.getErrorById("ER003");
	}

	public String hardDelete(Long id) {
		survivalBenefitRepo.deleteById(id);
		return errorService.getErrorById("ER003");
	}

	public List<SurvivalBenefitRate> search(String key) {
		return survivalBenefitRepo.globalSearch(key);
	}
	
//	public void save(MultipartFile file) {
//	try {
//		List<BonusRate> bonuses = Helper.convertExcelToListOfBonusRate(file.getInputStream());
//		bonusRateRepository.saveAll(bonuses);
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//}
}
