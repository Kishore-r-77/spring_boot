package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.StamDutyMaster;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.StamDutyMasterRepository;

@Service
public class StamDutyMasterService {

	@Autowired
	private StamDutyMasterRepository stamDutyMasterRepo;

	@Autowired
	private ErrorService errorService;

	public List<StamDutyMaster> getAll() {
		return stamDutyMasterRepo.getallActive();
	}

	public StamDutyMaster getById(Long id) {
		return stamDutyMasterRepo.getActiveById(id);
	}

	public String add(StamDutyMaster stamDutyMaster) {
		stamDutyMaster.setValidFlag(1);
		stamDutyMasterRepo.save(stamDutyMaster);
		return errorService.getErrorById("ER001");

	}

	public String update(Long id, StamDutyMaster stamDutyMaster) {
		StamDutyMaster stamDutyMaster1 = stamDutyMasterRepo.getActiveById(id);
		if (stamDutyMaster.getId() != null) {
			stamDutyMaster1.setId(stamDutyMaster.getId());
		}
		if (stamDutyMaster.getCompanyId() != null) {
			stamDutyMaster1.setCompanyId(stamDutyMaster.getCompanyId());
		}
		if (stamDutyMaster.getUinNumber() != null) {
			stamDutyMaster1.setUinNumber(stamDutyMaster.getUinNumber());
		}
		if (stamDutyMaster.getCoverName() != null) {
			stamDutyMaster1.setCoverName(stamDutyMaster.getCoverName());
		}
		if (stamDutyMaster.getSdRate() != null) {
			stamDutyMaster1.setSdRate(stamDutyMaster.getSdRate());
		}
		if (stamDutyMaster.getStartDate() != null) {
			stamDutyMaster1.setStartDate(stamDutyMaster.getStartDate());
		}
		if (stamDutyMaster.getEndDate() != null) {
			stamDutyMaster1.setEndDate(stamDutyMaster.getEndDate());
		}
		stamDutyMasterRepo.save(stamDutyMaster1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		StamDutyMaster stamDutyMaster = stamDutyMasterRepo.getActiveById(id);
		stamDutyMaster.setValidFlag(-1);
		stamDutyMasterRepo.save(stamDutyMaster);
		return errorService.getErrorById("ER012");
	}

	public String hardDelete(Long id) {
		stamDutyMasterRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}

	public List<StamDutyMaster> search(String key) {
		return stamDutyMasterRepo.globalSearch(key);
	}
	
	public void save(MultipartFile file) {
		try {
			List<StamDutyMaster> sds = Helper.convertExcelToListOfStampDuty(file.getInputStream());
			stamDutyMasterRepo.saveAll(sds);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
