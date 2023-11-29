package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.PurpleNav;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.PurpleNavRepository;

@Service
public class PurpleNavService {
	
	@Autowired
	private PurpleNavRepository purpleNavRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<PurpleNav> getAll(){
		return purpleNavRepo.getallActive();
	}
	
	public PurpleNav getById(Long id) {
		return purpleNavRepo.getActiveById(id);
	}
	
	public String add(PurpleNav purpleNav) {
		purpleNav.setValidFlag(1);
		purpleNavRepo.save(purpleNav);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, PurpleNav purpleNav) {
		PurpleNav navTable1 = purpleNavRepo.getActiveById(id);
		if(purpleNav.getCompanyId()!=null) {
			navTable1.setCompanyId(purpleNav.getCompanyId());
		}
		if(purpleNav.getFundCode()!=null) {
			navTable1.setFundCode(purpleNav.getFundCode());
		}
		if(purpleNav.getFundName()!=null) {
			navTable1.setFundName(purpleNav.getFundName());
		}
		if(purpleNav.getNavDate()!=null) {
			navTable1.setNavDate(purpleNav.getNavDate());
		}
		if(purpleNav.getRate()!=null) {
			navTable1.setRate(purpleNav.getRate());
		}
		
		purpleNavRepo.save(navTable1);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		PurpleNav purpleNav = purpleNavRepo.getActiveById(id);
		purpleNav.setValidFlag(-1);
		purpleNavRepo.save(purpleNav);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		purpleNavRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<PurpleNav> search(String key){
		return purpleNavRepo.globalSearch(key);
	}
	
	public void save(MultipartFile file) {
		try {
			List<PurpleNav> navs = Helper.convertExcelToListOfPurpleNav(file.getInputStream());
			purpleNavRepo.saveAll(navs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
