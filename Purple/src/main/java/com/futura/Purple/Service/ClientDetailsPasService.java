package com.futura.Purple.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.ClientDetailsPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.ClientDetailsPasRepository;
@Service
public class ClientDetailsPasService {
	
    @Autowired
    private ClientDetailsPasRepository ClientDetailPasRepo;
	
	@Autowired
	private ErrorService errorService;
	
	public List<ClientDetailsPas> getAll(){
		return ClientDetailPasRepo.getallActive();
	}
	
	public ClientDetailsPas getById(Long id) {
		return ClientDetailPasRepo.getActiveById(id);
	}
	
	public String add(ClientDetailsPas ClientDetailsPas) {
		ClientDetailsPas.setValidFlag(1);
		ClientDetailPasRepo.save(ClientDetailsPas);
		return errorService.getErrorById("ER001");
		
	}
	
	public String update(Long id, ClientDetailsPas ClientDetailsPas) {
		ClientDetailsPas ClientDetailsPas1 = ClientDetailPasRepo.getActiveById(id);
        if(ClientDetailsPas.getCompanyId() !=null) {
			ClientDetailsPas1.setCompanyId(ClientDetailsPas.getCompanyId());
		}
        if(ClientDetailsPas.getClntNum() !=null) {
			ClientDetailsPas1.setClntNum(ClientDetailsPas.getClntNum());
		}
        if(ClientDetailsPas.getLaName() !=null) {
			ClientDetailsPas1.setLaName(ClientDetailsPas.getLaName());
		}
        if(ClientDetailsPas.getLaDob() !=null) {
			ClientDetailsPas1.setLaDob(ClientDetailsPas.getLaDob());
		}
        if(ClientDetailsPas.getNationality() !=null) {
			ClientDetailsPas1.setNationality(ClientDetailsPas.getNationality());
		}
        if(ClientDetailsPas.getResidentStatus() !=null) {
			ClientDetailsPas1.setResidentStatus(ClientDetailsPas.getResidentStatus());
		}
        if(ClientDetailsPas.getGender() !=null) {
			ClientDetailsPas1.setGender(ClientDetailsPas.getGender());
		}
        if(ClientDetailsPas.getContactNum() !=null) {
			ClientDetailsPas1.setContactNum(ClientDetailsPas.getContactNum());
		}
        if(ClientDetailsPas.getEmailId() !=null) {
			ClientDetailsPas1.setEmailId(ClientDetailsPas.getEmailId());
		}
       
		ClientDetailPasRepo.save(ClientDetailsPas1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		ClientDetailsPas ClientDetailsPas = ClientDetailPasRepo.getActiveById(id);
		ClientDetailsPas.setValidFlag(-1);
		ClientDetailPasRepo.save(ClientDetailsPas);
		return errorService.getErrorById("ER012");
	}
	
	public String hardDelete(Long id) {
		ClientDetailPasRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<ClientDetailsPas> search(String key){
		return ClientDetailPasRepo.globalSearch(key);
	}
	
	

}
