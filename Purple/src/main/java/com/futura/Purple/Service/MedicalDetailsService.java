package com.futura.Purple.Service;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.MedicalDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.MedicalDetailsRepository;
@Service
public class MedicalDetailsService {
	
    @Autowired
    private MedicalDetailsRepository medicaldetailsRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<MedicalDetails> getAll(){
		return medicaldetailsRepo.getallActive();
	}
	
	public MedicalDetails getById(Long id) {
		return medicaldetailsRepo.getActiveById(id);
	}
	
	public String add(MedicalDetails MedicalDetails) {
		MedicalDetails.setValidFlag(1);
		medicaldetailsRepo.save(MedicalDetails);
		return errorService.getErrorById("ER001");
		
	}
	
	public String update(Long id, MedicalDetails MedicalDetails) {
		MedicalDetails MedicalDetails1 = medicaldetailsRepo.getActiveById(id);
        if(MedicalDetails.getCompanyId() !=null) {
			MedicalDetails1.setCompanyId(MedicalDetails.getCompanyId());
		}
        if(MedicalDetails.getCompanyName() !=null) {
			MedicalDetails1.setCompanyName(MedicalDetails.getCompanyName());
		}
        if(MedicalDetails.getTpaCode() !=null) {
			MedicalDetails1.setTpaCode(MedicalDetails.getTpaCode());
		}
        if(MedicalDetails.getProdName() !=null) {
			MedicalDetails1.setProdName(MedicalDetails.getProdName());
		}
        if(MedicalDetails.getMedicalCategory() !=null) {
			MedicalDetails1.setMedicalCategory(MedicalDetails.getMedicalCategory());
		}
        if(MedicalDetails.getMedicalCenter() !=null) {
			MedicalDetails1.setMedicalCenter(MedicalDetails.getMedicalCenter());
		}
        if(MedicalDetails.getMfRate() !=null) {
			MedicalDetails1.setMfRate(MedicalDetails.getMfRate());
		}
        if(MedicalDetails.getStartDate() !=null) {
			MedicalDetails1.setStartDate(MedicalDetails.getStartDate());
		}
        if(MedicalDetails.getEndDate() !=null) {
			MedicalDetails1.setEndDate(MedicalDetails.getEndDate());
		}
		medicaldetailsRepo.save(MedicalDetails1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		MedicalDetails MedicalDetails = medicaldetailsRepo.getActiveById(id);
		MedicalDetails.setValidFlag(-1);
		medicaldetailsRepo.save(MedicalDetails);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		medicaldetailsRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<MedicalDetails> search(String key){
		return medicaldetailsRepo.globalSearch(key);
	}
	
	
	public void save(MultipartFile file) {
		try {
			List<MedicalDetails> meds = Helper.convertExcelToListOfMedicalDetails(file.getInputStream());
			medicaldetailsRepo.saveAll(meds);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
