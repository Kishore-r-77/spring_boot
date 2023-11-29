package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.LoyaltyAdditionRate;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.LoyaltyAdditionRateRepository;

@Service
public class LoyaltyAdditionRateService {
	
	@Autowired
	private LoyaltyAdditionRateRepository loyaltyAdditionRateRepo;

	@Autowired
	private ErrorService errorService;

	public List<LoyaltyAdditionRate> getAll() {
		return loyaltyAdditionRateRepo.getallActive();
	}

	public LoyaltyAdditionRate getById(Long id) {
		return loyaltyAdditionRateRepo.getActiveById(id);
	}

	public String add(LoyaltyAdditionRate loyaltyAdditionRate) {
		loyaltyAdditionRate.setValidFlag(1);
		loyaltyAdditionRateRepo.save(loyaltyAdditionRate);
		return errorService.getErrorById("ER001");
	}

	public String update(Long id, LoyaltyAdditionRate loyaltyAdditionRate) {
		LoyaltyAdditionRate loyaltyAdditionRate1 = loyaltyAdditionRateRepo.getActiveById(id);

		if (loyaltyAdditionRate.getCompanyId() != null) {
			loyaltyAdditionRate1.setCompanyId(loyaltyAdditionRate.getCompanyId());
		}
		if (loyaltyAdditionRate.getUinNumber() != null) {
			loyaltyAdditionRate1.setUinNumber(loyaltyAdditionRate.getUinNumber());
		}
		if (loyaltyAdditionRate.getPlanName() != null) {
			loyaltyAdditionRate1.setPlanName(loyaltyAdditionRate.getPlanName());
		}
		if (loyaltyAdditionRate.getPlanCode() != null) {
			loyaltyAdditionRate1.setPlanCode(loyaltyAdditionRate.getPlanCode());
		}
		if (loyaltyAdditionRate.getFinancialYear() != null) {
			loyaltyAdditionRate1.setFinancialYear(loyaltyAdditionRate.getFinancialYear());
		}
		if (loyaltyAdditionRate.getBonusRate() != null) {
			loyaltyAdditionRate1.setBonusRate(loyaltyAdditionRate.getBonusRate());
		}
		if (loyaltyAdditionRate.getBonusType() != null) {
			loyaltyAdditionRate1.setBonusType(loyaltyAdditionRate.getBonusType());
		}
		if (loyaltyAdditionRate.getStartDate() != null) {
			loyaltyAdditionRate1.setStartDate(loyaltyAdditionRate.getStartDate());
		}
		if (loyaltyAdditionRate.getEndDate() != null) {
			loyaltyAdditionRate1.setEndDate(loyaltyAdditionRate.getEndDate());
		}

		loyaltyAdditionRateRepo.save(loyaltyAdditionRate1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		LoyaltyAdditionRate loyaltyAdditionRate = loyaltyAdditionRateRepo.getActiveById(id);
		loyaltyAdditionRate.setValidFlag(-1);
		loyaltyAdditionRateRepo.save(loyaltyAdditionRate);
		return errorService.getErrorById("ER003");
	}

	public String hardDelete(Long id) {
		loyaltyAdditionRateRepo.deleteById(id);
		return errorService.getErrorById("ER003");
	}

	public List<LoyaltyAdditionRate> search(String key) {
		return loyaltyAdditionRateRepo.globalSearch(key);
	}
	
//	public void save(MultipartFile file) {
//		try {
//			List<loyaltyAdditionRate> bonuses = Helper.convertExcelToListOfloyaltyAdditionRate(file.getInputStream());
//			loyaltyAdditionRateRepository.saveAll(bonuses);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
