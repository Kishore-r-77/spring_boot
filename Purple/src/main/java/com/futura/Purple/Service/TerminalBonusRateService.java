package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.BonusRate;
import com.futura.Purple.Entity.TerminalBonusRate;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.BonusRateRepository;
import com.futura.Purple.Repository.TerminalBonusRateRepository;

@Service
public class TerminalBonusRateService {
	
	@Autowired
	private TerminalBonusRateRepository termBonusRateRepo;

	@Autowired
	private ErrorService errorService;

	public List<TerminalBonusRate> getAll() {
		return termBonusRateRepo.getallActive();
	}

	public TerminalBonusRate getById(Long id) {
		return termBonusRateRepo.getActiveById(id);
	}

	public String add(TerminalBonusRate bonusRate) {
		bonusRate.setValidFlag(1);
		termBonusRateRepo.save(bonusRate);
		return errorService.getErrorById("ER001");
	}

	public String update(Long id, TerminalBonusRate termBonusRate) {
		TerminalBonusRate termBonusRate1 = termBonusRateRepo.getActiveById(id);

		if (termBonusRate.getCompanyId() != null) {
			termBonusRate1.setCompanyId(termBonusRate.getCompanyId());
		}
		if (termBonusRate.getUinNumber() != null) {
			termBonusRate1.setUinNumber(termBonusRate.getUinNumber());
		}
		if (termBonusRate.getPlanName() != null) {
			termBonusRate1.setPlanName(termBonusRate.getPlanName());
		}
		if (termBonusRate.getPlanCode() != null) {
			termBonusRate1.setPlanCode(termBonusRate.getPlanCode());
		}
		if (termBonusRate.getFinancialYear() != null) {
			termBonusRate1.setFinancialYear(termBonusRate.getFinancialYear());
		}
		if (termBonusRate.getBonusRate() != null) {
			termBonusRate1.setBonusRate(termBonusRate.getBonusRate());
		}
		if (termBonusRate.getBonusType() != null) {
			termBonusRate1.setBonusType(termBonusRate.getBonusType());
		}
		if (termBonusRate.getStartDate() != null) {
			termBonusRate1.setStartDate(termBonusRate.getStartDate());
		}
		if (termBonusRate.getEndDate() != null) {
			termBonusRate1.setEndDate(termBonusRate.getEndDate());
		}

		termBonusRateRepo.save(termBonusRate1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		TerminalBonusRate bonusRate = termBonusRateRepo.getActiveById(id);
		bonusRate.setValidFlag(-1);
		termBonusRateRepo.save(bonusRate);
		return errorService.getErrorById("ER003");
	}

	public String hardDelete(Long id) {
		termBonusRateRepo.deleteById(id);
		return errorService.getErrorById("ER003");
	}

	public List<TerminalBonusRate> search(String key) {
		return termBonusRateRepo.globalSearch(key);
	}
	
//	public void save(MultipartFile file) {
//		try {
//			List<BonusRate> bonuses = Helper.convertExcelToListOfBonusRate(file.getInputStream());
//			bonusRateRepository.saveAll(bonuses);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
