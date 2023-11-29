package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.SurrenderTransactionPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.SurrenderTransactionPasRepository;

@Service
public class SurrenderTransactionPasService {

	@Autowired
	private ErrorService errorService;

	@Autowired
	private SurrenderTransactionPasRepository surrenderTransactionPasRepository;
	
	public List<SurrenderTransactionPas> getAll() {
		return surrenderTransactionPasRepository.getallActive();
	}

	public List<Long> getAllPolicyNo() {

		return surrenderTransactionPasRepository.findListOfPolicyNo();
	}

	public SurrenderTransactionPas getById(Long id) {
		return surrenderTransactionPasRepository.getActiveById(id);
	}

	public SurrenderTransactionPas findByPolicyNo(Long policyNo) {
		return surrenderTransactionPasRepository.findByPolicyNo(policyNo);
	}

	public SurrenderTransactionPas findProcessedByPolicy(Long policyNo) {
		return surrenderTransactionPasRepository.findProcessedByPolicyNo(policyNo);
	}

	public String add(SurrenderTransactionPas transaction) {
		transaction.setValidFlag(1);
		transaction.setIpcaApprovalDate(null);
		
		double totalBonus = transaction.getReversionaryBonus()+transaction.getInterimBonus();
		transaction.setTotalBonus(totalBonus);
		surrenderTransactionPasRepository.save(transaction);
		return errorService.getErrorById("ER001");

	}

	public String update(Long id, SurrenderTransactionPas transaction) {
		SurrenderTransactionPas transaction1 = surrenderTransactionPasRepository.getActiveById(id);
		if (transaction.getCompanyId() != null) {
			transaction1.setCompanyId(transaction.getCompanyId());
		}
		if (transaction.getPolicyNo() != null) {
			transaction1.setPolicyNo(transaction.getPolicyNo());
		}
		if (transaction.getTransNo() != null) {
			transaction1.setTransNo(transaction.getTransNo());
		}
		if (transaction.getSvReqDate() != null) {
			transaction1.setSvReqDate(transaction.getSvReqDate());
		}
		if (transaction.getLogDate() != null) {
			transaction1.setLogDate(transaction.getLogDate());
		}
		if(transaction.getUinNumber()!=null) {
			transaction1.setUinNumber(transaction.getUinNumber());
		}
		if(transaction.getGsv()!=null) {
			transaction1.setGsv(transaction.getGsv());
		}
		if(transaction.getGsvPayble()!=null) {
			transaction1.setGsvPayble(transaction.getGsvPayble());
		}
		if(transaction.getSsv()!=null) {
			transaction1.setSsv(transaction.getSsv());
		}
		if (transaction.getPolicyDeposit() != null) {
			transaction1.setPolicyDeposit(transaction.getPolicyDeposit());
		}
		if (transaction.getPenalInterest() != null) {
			transaction1.setPenalInterest(transaction.getPenalInterest());
		}
		if (transaction.getGrossPay() != null) {
			transaction1.setGrossPay(transaction.getGrossPay());
		}
		if(transaction.getCdaCharges()!=null) {
			transaction1.setCdaCharges(transaction.getCdaCharges());
		}
		if (transaction.getTds() != null) {
			transaction1.setTds(transaction.getTds());
		}
		
		if (transaction.getCashValueBonus() != null) {
			transaction1.setCashValueBonus(transaction.getCashValueBonus());
		}
		
		if (transaction.getSurrenderCharge() != null) {
			transaction1.setSurrenderCharge(transaction.getSurrenderCharge());
		}
		
		if (transaction.getPaidUpValue() != null) {
			transaction1.setPaidUpValue(transaction.getPaidUpValue());
		}
		
		if (transaction.getReversionaryBonus() != null) {
			transaction1.setReversionaryBonus(transaction.getReversionaryBonus());
		}
		
		if (transaction.getInterimBonus() != null) {
			transaction1.setInterimBonus(transaction.getInterimBonus());
		}
		
		double totalBonus = transaction1.getReversionaryBonus()+transaction1.getInterimBonus();
		transaction1.setTotalBonus(totalBonus);
	
		
		if (transaction.getTotalRecovery() != null) {
			transaction1.setTotalRecovery(transaction.getTotalRecovery());
		}
		
		if (transaction.getOtherRecovery() != null) {
			transaction1.setOtherRecovery(transaction.getOtherRecovery());
		}
		if (transaction.getNetPayable() != null) {
			transaction1.setNetPayable(transaction.getNetPayable());
		}
		
		if (transaction.getApprovDate() != null) {
			transaction1.setApprovDate(transaction.getApprovDate());
		}
		
		if (transaction.getNetPayable() != null) {
			transaction1.setNetPayable(transaction.getNetPayable());
		}
		if (transaction.getFundValue() != null) {
			transaction1.setFundValue(transaction.getFundValue());
		}
		
		if (transaction.getEffectiveDate() != null) {
			transaction1.setEffectiveDate(transaction.getEffectiveDate());
		}
		
		if (transaction.getPolicyLoan() != null) {
			transaction1.setPolicyLoan(transaction.getPolicyLoan());
		}
		
		if (transaction.getLoanInterest() != null) {
			transaction1.setLoanInterest(transaction.getLoanInterest());
		}
		
		if (transaction.getMakerFlag() != null) {
			transaction1.setMakerFlag(transaction.getMakerFlag());
		}
		if (transaction.getCheckerFlag() != null) {
			transaction1.setCheckerFlag(transaction.getCheckerFlag());
		}
		if (transaction.getIpcaApprovalFlag() != null) {
			transaction1.setIpcaApprovalFlag(transaction.getIpcaApprovalFlag());
		}
		if (transaction.getIpcaApprovalDate() != null) {
			transaction1.setIpcaApprovalDate(transaction.getIpcaApprovalDate());
		}
		if (transaction.getIpcaApprovalRemarks() != null) {
			transaction1.setIpcaApprovalRemarks(transaction.getIpcaApprovalRemarks());
		}
		if (transaction.getQcUserId() != null) {
			transaction1.setQcUserId(transaction.getQcUserId());
		}
		if (transaction.getInterimStatus() != null) {
			transaction1.setInterimStatus(transaction.getInterimStatus());
		}
		surrenderTransactionPasRepository.save(transaction1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		SurrenderTransactionPas transaction = surrenderTransactionPasRepository.getActiveById(id);
		transaction.setValidFlag(-1);
		surrenderTransactionPasRepository.save(transaction);
		return errorService.getErrorById("ER012");
	}

	public String hardDelete(Long id) {
		surrenderTransactionPasRepository.deleteById(id);
		return errorService.getErrorById("ER012");
	}

	public List<SurrenderTransactionPas> search(String key) {
		return surrenderTransactionPasRepository.globalSearch(key);
	}

}
