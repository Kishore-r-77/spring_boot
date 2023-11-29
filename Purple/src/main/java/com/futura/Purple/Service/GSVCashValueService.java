package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.GSVCashValue;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.GSVCashValueRepository;

@Service
public class GSVCashValueService {

	@Autowired
	private GSVCashValueRepository gsvCashValueRepository;

	@Autowired
	private ErrorService errorService;

	public List<GSVCashValue> getAll() {
		return gsvCashValueRepository.getallActive();
	}

	public GSVCashValue getById(Long id) {
		return gsvCashValueRepository.getActiveById(id);
	}

	public String add(GSVCashValue cashValue) {
		cashValue.setValidFlag(1);
		gsvCashValueRepository.save(cashValue);
		return errorService.getErrorById("ER001");
	}

	public String update(Long id, GSVCashValue cashValue) {
		GSVCashValue cashValue1 = gsvCashValueRepository.getActiveById(id);

		if (cashValue.getCompanyId() != null) {
			cashValue1.setCompanyId(cashValue.getCompanyId());
		}
		if (cashValue.getUinNumber() != null) {
			cashValue1.setUinNumber(cashValue.getUinNumber());
		}
		if (cashValue.getPlanName() != null) {
			cashValue1.setPlanName(cashValue.getPlanName());
		}
		if (cashValue.getPlanCode() != null) {
			cashValue1.setPlanCode(cashValue.getPlanCode());
		}
		if (cashValue.getYearsToMaturity() != null) {
			cashValue1.setYearsToMaturity(cashValue.getYearsToMaturity());
		}
		if (cashValue.getCvbRate() != null) {
			cashValue1.setCvbRate(cashValue.getCvbRate());
		}
		if (cashValue.getStartDate() != null) {
			cashValue1.setStartDate(cashValue.getStartDate());
		}
		if (cashValue.getEndDate() != null) {
			cashValue1.setEndDate(cashValue.getEndDate());
		}

		gsvCashValueRepository.save(cashValue1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		GSVCashValue cashValue = gsvCashValueRepository.getActiveById(id);
		cashValue.setValidFlag(-1);
		gsvCashValueRepository.save(cashValue);
		return errorService.getErrorById("ER003");
	}

	public String hardDelete(Long id) {
		gsvCashValueRepository.deleteById(id);
		return errorService.getErrorById("ER003");
	}

	public List<GSVCashValue> search(String key) {
		return gsvCashValueRepository.globalSearch(key);
	}
	
	public void save(MultipartFile file) {
		try {
			List<GSVCashValue> cvbs = Helper.convertExcelToListOfCashValueBonus(file.getInputStream());
			gsvCashValueRepository.saveAll(cvbs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
