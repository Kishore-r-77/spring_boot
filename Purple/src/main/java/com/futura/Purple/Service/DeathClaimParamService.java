package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.DeathClaimParam;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.DeathClaimParamRepository;

@Service
public class DeathClaimParamService {
	
	@Autowired
	private DeathClaimParamRepository deathClaimParamRepo;
	
	@Autowired
	private ErrorService errorService;

	public List<DeathClaimParam> getAll() {
		return deathClaimParamRepo.getallActive();
	}

	public DeathClaimParam getById(Long id) {
		return deathClaimParamRepo.getActiveById(id);
	}

	public String add(DeathClaimParam deathClaimParam) {
		deathClaimParam.setValidFlag(1);
		deathClaimParamRepo.save(deathClaimParam);
		return errorService.getErrorById("ER001");
	}

	public String update(Long id, DeathClaimParam deathClaimParam) {
		DeathClaimParam deathClaimParam1 = deathClaimParamRepo.getActiveById(id);

		if (deathClaimParam.getCompanyId() != null) {
			deathClaimParam1.setCompanyId(deathClaimParam.getCompanyId());
		}
		if (deathClaimParam.getUinNumber() != null) {
			deathClaimParam1.setUinNumber(deathClaimParam.getUinNumber());
		}
		if (deathClaimParam.getCauseOfDeath() != null) {
			deathClaimParam1.setCauseOfDeath(deathClaimParam.getCauseOfDeath());
		}
		if (deathClaimParam.getFromMonth() != null) {
			deathClaimParam1.setFromMonth(deathClaimParam.getFromMonth());
		}
		if (deathClaimParam.getToMonth() != null) {
			deathClaimParam1.setToMonth(deathClaimParam.getToMonth());
		}
		if (deathClaimParam.getPolicyStatus() != null) {
			deathClaimParam1.setPolicyStatus(deathClaimParam.getPolicyStatus());
		}
		if (deathClaimParam.getStartDate() != null) {
			deathClaimParam1.setStartDate(deathClaimParam.getStartDate());
		}
		if (deathClaimParam.getEndDate() != null) {
			deathClaimParam1.setEndDate(deathClaimParam.getEndDate());
		}
		if (deathClaimParam.getFactor() != null) {
			deathClaimParam1.setFactor(deathClaimParam.getFactor());
		}
		if(deathClaimParam.getCalculationMethod()!=null) {
			deathClaimParam1.setCalculationMethod(deathClaimParam.getCalculationMethod());
		}

		deathClaimParamRepo.save(deathClaimParam1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		DeathClaimParam deathClaimParam = deathClaimParamRepo.getActiveById(id);
		deathClaimParam.setValidFlag(-1);
		deathClaimParamRepo.save(deathClaimParam);
		return errorService.getErrorById("ER003");
	}

	public String hardDelete(Long id) {
		deathClaimParamRepo.deleteById(id);
		return errorService.getErrorById("ER003");
	}

	public List<DeathClaimParam> search(String key) {
		return deathClaimParamRepo.globalSearch(key);
	}
	
	public void save(MultipartFile file) {
		try {
			List<DeathClaimParam> cvbs = Helper.convertExcelToListOfDeathClaimParam(file.getInputStream());
			deathClaimParamRepo.saveAll(cvbs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
