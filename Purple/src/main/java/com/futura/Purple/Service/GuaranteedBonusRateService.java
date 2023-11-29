package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.GuaranteedBonusRate;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.GuaranteedBonusRateRepository;

@Service
public class GuaranteedBonusRateService {

	@Autowired
	private GuaranteedBonusRateRepository guaranteedBonusRateRepository;

	@Autowired
	private ErrorService errorService;

	public List<GuaranteedBonusRate> getAll() {
		return guaranteedBonusRateRepository.getallActive();
	}

	public GuaranteedBonusRate getById(Long id) {
		return guaranteedBonusRateRepository.getActiveById(id);
	}

	public String add(GuaranteedBonusRate bonusRate) {
		bonusRate.setValidFlag(1);
		guaranteedBonusRateRepository.save(bonusRate);
		return errorService.getErrorById("ER001");
	}

	public String update(Long id, GuaranteedBonusRate bonusRate) {
		GuaranteedBonusRate bonusRate1 = guaranteedBonusRateRepository.getActiveById(id);

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

		guaranteedBonusRateRepository.save(bonusRate1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		GuaranteedBonusRate bonusRate = guaranteedBonusRateRepository.getActiveById(id);
		bonusRate.setValidFlag(-1);
		guaranteedBonusRateRepository.save(bonusRate);
		return errorService.getErrorById("ER003");
	}

	public String hardDelete(Long id) {
		guaranteedBonusRateRepository.deleteById(id);
		return errorService.getErrorById("ER003");
	}

	public List<GuaranteedBonusRate> search(String key) {
		return guaranteedBonusRateRepository.globalSearch(key);
	}
	
	public void save(MultipartFile file) {
		try {
			List<GuaranteedBonusRate> bonuses = Helper.convertExcelToListOfGuaranteedBonusRate(file.getInputStream());
			guaranteedBonusRateRepository.saveAll(bonuses);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
