package com.futura.Purple.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.PolicyDetailsPas;
import com.futura.Purple.Entity.SurrenderPolicyDetails;
import com.futura.Purple.Entity.UinMaster;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.PolicyDetailsPasRepository;
import com.futura.Purple.Repository.SurrenderPolicyDetailsRepository;
import com.futura.Purple.Repository.UinMasterRepository;

@Service
public class UinMasterService {

	@Autowired
	private UinMasterRepository masterRepository;
	
	@Autowired
	private PolicyDetailsPasRepository flcPolicyDetailsRepository;
	
	@Autowired
	private SurrenderPolicyDetailsRepository surrenderPolicyDetailsRepository;
	
	@Autowired
	private ErrorService errorService;
	
	public List<UinMaster> getAll() {
		return masterRepository.getallActive();
	}
	
	public UinMaster getById(Long id) {
		return masterRepository.getActiveById(id);
	}
	
	public UinMaster getByFlcPolicyNo(Long policyNo) {
		PolicyDetailsPas flcPolicy = flcPolicyDetailsRepository.getActiveByPolicyNo(policyNo);	
		return masterRepository.getActiveByUIN(flcPolicy.getUinNumber());	
	}
	
	public UinMaster getBySurrenderPolicyNo(Long policyNo) {
		SurrenderPolicyDetails surrenderPolicy = surrenderPolicyDetailsRepository.getActiveByPolicyNo(policyNo);
		return masterRepository.getActiveByUIN(surrenderPolicy.getUinNumber());	
	}
	
	public UinMaster getActiveByUIN(String uinNo) {
		return masterRepository.getActiveByUIN(uinNo);
	}
	
	public String add(UinMaster uinMaster) {
		uinMaster.setValidFlag(1);
		masterRepository.save(uinMaster);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, UinMaster uinMaster) {
		UinMaster master = masterRepository.getActiveById(id);
		if (uinMaster.getId() != null) {
			master.setId(uinMaster.getId());
		}
		if (uinMaster.getCompanyId() != null) {
			master.setCompanyId(uinMaster.getCompanyId());
		}
		if (uinMaster.getUinNumber() != null) {
			master.setUinNumber(uinMaster.getUinNumber());
		}
		if (uinMaster.getPlanName() != null) {
			master.setPlanName(uinMaster.getPlanName());
		}
		if (uinMaster.getPlanCode() != null) {
			master.setPlanCode(uinMaster.getPlanCode());
		}
		if (uinMaster.getProductType() != null) {
			master.setProductType(uinMaster.getProductType());
		}
		if (uinMaster.getGsvFactor() != null) {
			master.setGsvFactor(uinMaster.getGsvFactor());
		}
		if (uinMaster.getSsvFactor() != null) {
			master.setSsvFactor(uinMaster.getSsvFactor());
		}
		if (uinMaster.getGsvCashValue() != null) {
			master.setGsvCashValue(uinMaster.getGsvCashValue());
		}
		if (uinMaster.getRevesionaryBonus() != null) {
			master.setRevesionaryBonus(uinMaster.getRevesionaryBonus());
		}
		if (uinMaster.getInterimBonus() != null) {
			master.setInterimBonus(uinMaster.getInterimBonus());
		}
		if (uinMaster.getGuaranteedBonus() != null) {
			master.setGuaranteedBonus(uinMaster.getGuaranteedBonus());
		}
		if (uinMaster.getTerminalBonus() != null) {
			master.setTerminalBonus(uinMaster.getTerminalBonus());
		}
		if (uinMaster.getLoyaltyBonus() != null) {
			master.setLoyaltyBonus(uinMaster.getLoyaltyBonus());
		}
		if (uinMaster.getFlcEligibility() != null) {
			master.setFlcEligibility(uinMaster.getFlcEligibility());
		}
		if (uinMaster.getSurrenderChargeRate() != null) {
			master.setSurrenderChargeRate(uinMaster.getSurrenderChargeRate());
		}
		if (uinMaster.getStartDate() != null) {
			master.setStartDate(uinMaster.getStartDate());
		}
		if (uinMaster.getEndDate() != null) {
			master.setEndDate(uinMaster.getEndDate());
		}
		masterRepository.save(master);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		UinMaster master = masterRepository.getActiveById(id);
		master.setValidFlag(-1);
		masterRepository.save(master);
		return errorService.getErrorById("ER012");
	}

	public String hardDelete(Long id) {
		masterRepository.deleteById(id);
		return errorService.getErrorById("ER012");
	}

	public List<UinMaster> search(String key) {
		return masterRepository.globalSearch(key);
	}
	
	public void save(MultipartFile file) {
		try {
			List<UinMaster> uins = Helper.convertExcelToListOfUINMaster(file.getInputStream());
			masterRepository.saveAll(uins);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
