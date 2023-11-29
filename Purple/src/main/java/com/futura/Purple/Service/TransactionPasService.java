package com.futura.Purple.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.TransactionPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.TransactionPasRepository;

@Service
public class TransactionPasService {

	@Autowired
	private TransactionPasRepository transactionRepo;

	@Autowired
	private ErrorService errorService;

	public List<TransactionPas> getAll() {
		return transactionRepo.getallActive();
	}
	
	public List<Long> getAllPolicyNo() {
		
		return transactionRepo.findFlcPolicyNo();
	}


	public TransactionPas getById(Long id) {
		return transactionRepo.getActiveById(id);
	}
	
	public TransactionPas findByPolicyNo(Long policyNo) {
		return transactionRepo.findByFlcPolicyNo(policyNo);
	}
	
	public TransactionPas findProcessedByPolicy(Long policyNo) {
		return transactionRepo.findProcessedByPolicyNo(policyNo);
	}

	public String add(TransactionPas transaction) {
		transaction.setValidFlag(1);
		transaction.setPurpleApprovalDate(null);
		
		if(transaction.getMedicalFee() == null) {
			transaction.setMedicalFee(0l);
		}
		transactionRepo.save(transaction);
		return errorService.getErrorById("ER001");

	}

	public String update(Long id, TransactionPas transaction) {
		TransactionPas transaction1 = transactionRepo.getActiveById(id);
        if(transaction.getCompanyId() !=null) {
			transaction1.setCompanyId(transaction.getCompanyId());
		}
        if(transaction.getFlcPolicyNo() !=null) {
			transaction1.setFlcPolicyNo(transaction.getFlcPolicyNo());
		}
        if(transaction.getFlcTransNo() !=null) {
			transaction1.setFlcTransNo(transaction.getFlcTransNo());
		}
        if(transaction.getFlcReqDate() !=null) {
			transaction1.setFlcReqDate(transaction.getFlcReqDate());
		}
        if(transaction.getFlcLogDate() !=null) {
			transaction1.setFlcLogDate(transaction.getFlcLogDate());
		}
        if(transaction.getFlcPremRefund() !=null) {
			transaction1.setFlcPremRefund(transaction.getFlcPremRefund());
		}
        if(transaction.getFlcTotalPrem() !=null) {
			transaction1.setFlcTotalPrem(transaction.getFlcTotalPrem());
		}
        if(transaction.getFlcPolicyDop() !=null) {
			transaction1.setFlcPolicyDop(transaction.getFlcPolicyDop());
		}
        if(transaction.getPenalIntrest() !=null) {
			transaction1.setPenalIntrest(transaction.getPenalIntrest());
		}
        if(transaction.getGrossFlcPay() !=null) {
			transaction1.setGrossFlcPay(transaction.getGrossFlcPay());
		}
        if(transaction.getMedicalFee() !=null) {
			transaction1.setMedicalFee(transaction.getMedicalFee());
		}
        if(transaction.getStamDuty() !=null) {
			transaction1.setStamDuty(transaction.getStamDuty());
		}
        if(transaction.getRiskPremRecov() !=null) {
			transaction1.setRiskPremRecov(transaction.getRiskPremRecov());
		}
        if(transaction.getMortChargeRefund() !=null) {
			transaction1.setMortChargeRefund(transaction.getMortChargeRefund());
		}
        if(transaction.getTotalRecov() !=null) {
			transaction1.setTotalRecov(transaction.getTotalRecov());
		}
        if(transaction.getNetFlcPay() !=null) {
			transaction1.setNetFlcPay(transaction.getNetFlcPay());
		}
        if(transaction.getEffDate() !=null) {
			transaction1.setEffDate(transaction.getEffDate());
		}
        if(transaction.getFlcApprovalDate() !=null) {
			transaction1.setFlcApprovalDate(transaction.getFlcApprovalDate());
		}
        if(transaction.getMedicalCategory() !=null) {
			transaction1.setMedicalCategory(transaction.getMedicalCategory());
		}
        if(transaction.getMedicalCenter() !=null) {
			transaction1.setMedicalCenter(transaction.getMedicalCenter());
		}
        if(transaction.getMedicatTpaCode() !=null) {
			transaction1.setMedicatTpaCode(transaction.getMedicatTpaCode());
		}
        if(transaction.getMakerFlag() !=null) {
			transaction1.setMakerFlag(transaction.getMakerFlag());
		}
        if(transaction.getCheckerFlag()!=null) {
        	transaction1.setCheckerFlag(transaction.getCheckerFlag());
        }
        if(transaction.getPurpleApprovalFlag()!=null) {
        	transaction1.setPurpleApprovalFlag(transaction.getPurpleApprovalFlag());
        }
        if(transaction.getPurpleApprovalDate()!=null) {
        	transaction1.setPurpleApprovalDate(transaction.getPurpleApprovalDate());
        }
        if(transaction.getPurpleApprovalRemark()!=null) {
        	transaction1.setPurpleApprovalRemark(transaction.getPurpleApprovalRemark());
        }
        if(transaction.getApprovalQcUserId()!=null) {
        	transaction1.setApprovalQcUserId(transaction.getApprovalQcUserId());
        }
        if(transaction.getInterimStatus()!=null) {
        	transaction1.setInterimStatus(transaction.getInterimStatus());
        }
		transactionRepo.save(transaction1);
		return errorService.getErrorById("ER003");
	}
	public String deactivate(Long id) {
		TransactionPas transaction = transactionRepo.getActiveById(id);
		transaction.setValidFlag(-1);
		transactionRepo.save(transaction);
		return errorService.getErrorById("ER012");
	}

	public String hardDelete(Long id) {
		transactionRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}

	public List<TransactionPas> search(String key) {
		return transactionRepo.globalSearch(key);
	}

}
