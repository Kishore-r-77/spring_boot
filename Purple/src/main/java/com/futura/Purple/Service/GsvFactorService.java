package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.GsvFactor;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.GsvFactorRepository;

@Service
public class GsvFactorService {
	@Autowired
	private GsvFactorRepository gsvRepository;

	@Autowired
	private ErrorService errorService;

	public List<GsvFactor> getall() {
		return gsvRepository.getallActive();
	}

	public GsvFactor getById(Long id) {
		return gsvRepository.getActiveById(id);
	}

	public String add(GsvFactor entity) {
		entity.setValidFlag(1);
		gsvRepository.save(entity);
		return errorService.getErrorById("ER001");

	}

	public String update(Long id, GsvFactor entity) {
		GsvFactor gsv = gsvRepository.getActiveById(id);
		if (entity.getUinNumber() != null) {
			gsv.setUinNumber(entity.getUinNumber());
			;
		}
		if (entity.getPlanName() != null) {
			gsv.setPlanName(entity.getPlanName());
		}
		if (entity.getPlanCode() != null) {
			gsv.setPlanCode(entity.getPlanCode());
		}
		if (entity.getStartDate() != null) {
			gsv.setStartDate(entity.getStartDate());
		}
		if (entity.getEndDate() != null) {
			gsv.setEndDate(entity.getEndDate());
		}

		if (entity.getPolicyTerm() != null) {
			gsv.setPolicyTerm(entity.getPolicyTerm());
		}
		if (entity.getPremiumTerm() != null) {
			gsv.setPremiumTerm(entity.getPremiumTerm());
		}
		if (entity.getPremiumType() != null) {
			gsv.setPremiumType(entity.getPremiumType());
		}
		if (entity.getPolicyDuration() != null) {
			gsv.setPolicyDuration(entity.getPolicyDuration());
		}
		if (entity.getRate() != null) {
			gsv.setRate(entity.getRate());
		}

		gsvRepository.save(gsv);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		GsvFactor gsv = gsvRepository.getActiveById(id);
		gsv.setValidFlag(-1);
		gsvRepository.save(gsv);
		return errorService.getErrorById("ER008");
	}

	public String hardDelete(Long id) {
		gsvRepository.deleteById(id);
		return errorService.getErrorById("ER008");
	}

	public List<GsvFactor> search(String key) {
		return gsvRepository.globalSearch(key);
	}
	
	public void save(MultipartFile file) {
		try {
			List<GsvFactor> gsvs = Helper.convertExcelToListOfGSVFactor(file.getInputStream());
			gsvRepository.saveAll(gsvs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
