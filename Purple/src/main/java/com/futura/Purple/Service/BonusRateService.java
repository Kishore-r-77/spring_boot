package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.BonusRate;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.BonusRateRepository;

@Service
public class BonusRateService {

	@Autowired
	private BonusRateRepository bonusRateRepository;

	@Autowired
	private ErrorService errorService;

	public List<BonusRate> getAll() {
		return bonusRateRepository.getallActive();
	}

	public BonusRate getById(Long id) {
		return bonusRateRepository.getActiveById(id);
	}

	public String add(BonusRate bonusRate) {
		bonusRate.setValidFlag(1);
		bonusRateRepository.save(bonusRate);
		return errorService.getErrorById("ER001");
	}

	public String update(Long id, BonusRate bonusRate) {
		BonusRate bonusRate1 = bonusRateRepository.getActiveById(id);

		if (bonusRate.getCompanyId() != null) {
			bonusRate1.setCompanyId(bonusRate.getCompanyId());
		}
		if (bonusRate.getUinNumber() != null) {
			bonusRate1.setUinNumber(bonusRate.getUinNumber());
		}
		if (bonusRate.getPlanName() != null) {
			bonusRate1.setPlanName(bonusRate.getPlanName());
		}
		if (bonusRate.getPlanCode() != null) {
			bonusRate1.setPlanCode(bonusRate.getPlanCode());
		}
		if (bonusRate.getFinancialYear() != null) {
			bonusRate1.setFinancialYear(bonusRate.getFinancialYear());
		}
		if (bonusRate.getBonusRate() != null) {
			bonusRate1.setBonusRate(bonusRate.getBonusRate());
		}
		if (bonusRate.getBonusType() != null) {
			bonusRate1.setBonusType(bonusRate.getBonusType());
		}
		if (bonusRate.getStartDate() != null) {
			bonusRate1.setStartDate(bonusRate.getStartDate());
		}
		if (bonusRate.getEndDate() != null) {
			bonusRate1.setEndDate(bonusRate.getEndDate());
		}

		bonusRateRepository.save(bonusRate1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		BonusRate bonusRate = bonusRateRepository.getActiveById(id);
		bonusRate.setValidFlag(-1);
		bonusRateRepository.save(bonusRate);
		return errorService.getErrorById("ER003");
	}

	public String hardDelete(Long id) {
		bonusRateRepository.deleteById(id);
		return errorService.getErrorById("ER003");
	}

	public List<BonusRate> search(String key) {
		return bonusRateRepository.globalSearch(key);
	}
	
	public void save(MultipartFile file) {
		try {
			List<BonusRate> bonuses = Helper.convertExcelToListOfBonusRate(file.getInputStream());
			bonusRateRepository.saveAll(bonuses);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
