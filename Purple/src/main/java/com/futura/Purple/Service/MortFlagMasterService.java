package com.futura.Purple.Service;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.MortFlagMaster;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.MortFlagMasterRepository;
@Service
public class MortFlagMasterService {
	
    @Autowired
    private MortFlagMasterRepository mortFlagMasterRepository;
	
	@Autowired
	private ErrorService errorService;
	
	public List<MortFlagMaster> getAll(){
		return mortFlagMasterRepository.getallActive();
	}
	
	public MortFlagMaster getById(Long id) {
		return mortFlagMasterRepository.getActiveById(id);
	}
	
	public String add(MortFlagMaster mortFlagMaster) {
		mortFlagMaster.setValidFlag(1);
		mortFlagMasterRepository.save(mortFlagMaster);
		return errorService.getErrorById("ER001");
		
	}
	
	public String update(Long id, MortFlagMaster mortFlagMaster) {
		MortFlagMaster mortFlagMaster1 = mortFlagMasterRepository.getActiveById(id);
        if(mortFlagMaster.getId() !=null) {
			mortFlagMaster1.setId(mortFlagMaster.getId());
		}
        if(mortFlagMaster.getCompanyId() !=null) {
			mortFlagMaster1.setCompanyId(mortFlagMaster.getCompanyId());
		}
        if(mortFlagMaster.getUinNumber() !=null) {
			mortFlagMaster1.setUinNumber(mortFlagMaster.getUinNumber());
		}
        if(mortFlagMaster.getCoverName() !=null) {
			mortFlagMaster1.setCoverName(mortFlagMaster.getCoverName());
		}
        if(mortFlagMaster.getMortFlag() !=null) {
			mortFlagMaster1.setMortFlag(mortFlagMaster.getMortFlag());
		}
        if(mortFlagMaster.getStartDate() !=null) {
			mortFlagMaster1.setStartDate(mortFlagMaster.getStartDate());
		}
        if(mortFlagMaster.getEndDate() !=null) {
			mortFlagMaster1.setEndDate(mortFlagMaster.getEndDate());
		}
        mortFlagMasterRepository.save(mortFlagMaster1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		MortFlagMaster mortflagmaster = mortFlagMasterRepository.getActiveById(id);
		mortflagmaster.setValidFlag(-1);
		mortFlagMasterRepository.save(mortflagmaster);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		mortFlagMasterRepository.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<MortFlagMaster> search(String key){
		return mortFlagMasterRepository.globalSearch(key);
	}
	
	
	public void save(MultipartFile file) {
		try {
			List<MortFlagMaster> mFlags = Helper.convertExcelToListOfMortMaster(file.getInputStream());
			mortFlagMasterRepository.saveAll(mFlags);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
