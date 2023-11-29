package com.futura.Purple.Service;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.MortalityRates;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.MortalityRatesRepository;
@Service
public class MortalityRatesService {
	
    @Autowired
    private MortalityRatesRepository mortalityratesRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<MortalityRates> getAll(){
		return mortalityratesRepo.getallActive();
	}
	
	public MortalityRates getById(Long id) {
		return mortalityratesRepo.getActiveById(id);
	}
	
	public String add(MortalityRates mortalityrates) {
		mortalityrates.setValidFlag(1);
		mortalityratesRepo.save(mortalityrates);
		return errorService.getErrorById("ER001");
		
	}
	
	public String update(Long id, MortalityRates mortalityrates) {
		MortalityRates mortalityrates1 = mortalityratesRepo.getActiveById(id);
        if(mortalityrates.getCompanyId() !=null) {
			mortalityrates1.setCompanyId(mortalityrates.getCompanyId());
		}
        if(mortalityrates.getPlan() !=null) {
			mortalityrates1.setPlan(mortalityrates.getPlan());
		}
        if(mortalityrates.getPlanName() !=null) {
			mortalityrates1.setPlanName(mortalityrates.getPlanName());
		}
        if(mortalityrates.getPremTerm() !=null) {
			mortalityrates1.setPremTerm(mortalityrates.getPremTerm());
		}
        if(mortalityrates.getAge() !=0) {
			mortalityrates1.setAge(mortalityrates.getAge());
		}
        if(mortalityrates.getRates() !=null) {
			mortalityrates1.setRates(mortalityrates.getRates());
		}
        if(mortalityrates.getStartDate() !=null) {
			mortalityrates1.setStartDate(mortalityrates.getStartDate());
		}
		if(mortalityrates.getEndDate() !=null) {
			mortalityrates1.setEndDate(mortalityrates.getEndDate());
		}
        if(mortalityrates.getGender() !=null) {
			mortalityrates1.setGender(mortalityrates.getGender());
		}
        if(mortalityrates.getSmoker() !=null) {
			mortalityrates1.setSmoker(mortalityrates.getSmoker());
		}
		mortalityratesRepo.save(mortalityrates1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		MortalityRates mortalityrates = mortalityratesRepo.getActiveById(id);
		mortalityrates.setValidFlag(-1);
		mortalityratesRepo.save(mortalityrates);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		mortalityratesRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<MortalityRates> search(String key){
		return mortalityratesRepo.globalSearch(key);
	}
	
	public void save(MultipartFile file) {
		try {
			List<MortalityRates> mRates = Helper.convertExcelToListOfMortalityRates(file.getInputStream());
			mortalityratesRepo.saveAll(mRates);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
