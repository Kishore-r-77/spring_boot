package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.SsvFactor;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.SsvFactorRepository;

@Service
public class SsvFactorService {
	
	@Autowired
	private SsvFactorRepository ssvFactorRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<SsvFactor> getAll() {
		return ssvFactorRepo.getallActive();
	}
	
	public SsvFactor getById(Long id) {
		return ssvFactorRepo.getActiveById(id);
	}
	
	public String add(SsvFactor ssvfactor) {
		ssvfactor.setValidFlag(1);
		ssvFactorRepo.save(ssvfactor);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, SsvFactor ssvfactor) {
		SsvFactor ssvfactor1 = ssvFactorRepo.getActiveById(id);
		if(ssvfactor.getCompanyId()!=null)
		{
			ssvfactor1.setCompanyId(ssvfactor.getCompanyId());
		}
		if(ssvfactor.getUinNumber()!=null)
		{
			ssvfactor1.setUinNumber(ssvfactor.getUinNumber());
		}
		if(ssvfactor.getPlanName()!=null)
		{
			ssvfactor1.setPlanName(ssvfactor.getPlanName());
		}
		if(ssvfactor.getPlanCode()!=null)
		{
			ssvfactor1.setPlanCode(ssvfactor.getPlanCode());
		}
		if(ssvfactor.getPremiumType()!=null)
		{
			ssvfactor1.setPremiumType(ssvfactor.getPremiumType());
		}
		if(ssvfactor.getPremiumTerm()!=null)
		{
			ssvfactor1.setPremiumTerm(ssvfactor.getPremiumTerm());
		}
		if(ssvfactor.getPolicyTerm()!=null)
		{
			ssvfactor1.setPolicyTerm(ssvfactor.getPolicyTerm());
		}
		if(ssvfactor.getPolicyDuration()!=null)
		{
			ssvfactor1.setPolicyDuration(ssvfactor.getPolicyDuration());
		}
		if(ssvfactor.getRate()!=null)
		{
			ssvfactor1.setRate(ssvfactor.getRate());
		}
		if(ssvfactor.getStartDate()!=null)
		{
			ssvfactor1.setStartDate(ssvfactor.getStartDate());
		}
		if(ssvfactor.getEndDate()!=null)
		{
			ssvfactor1.setEndDate(ssvfactor.getEndDate());
		}
		
		ssvFactorRepo.save(ssvfactor1);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		SsvFactor ssvfactor = ssvFactorRepo.getActiveById(id);
		ssvfactor.setValidFlag(-1);
		ssvFactorRepo.save(ssvfactor);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		ssvFactorRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<SsvFactor> search(String key) {
		return ssvFactorRepo.globalSearch(key);
	}
	
	public void save(MultipartFile file) {
		try {
			List<SsvFactor> ssvs = Helper.convertExcelToListOfSSVFactor(file.getInputStream());
			ssvFactorRepo.saveAll(ssvs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
