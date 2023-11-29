package com.futura.Purple.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.DeathClaimTransactionPas;
import com.futura.Purple.Entity.SurrenderTransactionPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimTransactionPasRepository;

@Service
public class DeathClaimTransactionPasService {

	@Autowired
	private ErrorService errorService;
	
	@Autowired
	private DeathClaimTransactionPasRepository deathClaimTransRepo;
	
	public List<DeathClaimTransactionPas> getAll() {
		return deathClaimTransRepo.getallActive();
	}

	public List<Long> getAllPolicyNo() {

		return deathClaimTransRepo.findListOfPolicyNo();
	}

	public DeathClaimTransactionPas getById(Long id) {
		return deathClaimTransRepo.getActiveById(id);
	}

	public DeathClaimTransactionPas findByUinNumber(String  uinNumber) {
		return deathClaimTransRepo.findByUinNumber(uinNumber);
	}

	public DeathClaimTransactionPas findProcessedByUinNo(Long uinNo) {
		return deathClaimTransRepo.findProcessedByUinNo(uinNo);
	}
	
	public String add(DeathClaimTransactionPas transaction) {
		transaction.setValidFlag(1);
		transaction.setLeapApprovalDate(null);
		
		double totalBonus = transaction.getReversionaryBonus()+transaction.getInterimBonus();
		transaction.setTotalBonus(totalBonus);
		deathClaimTransRepo.save(transaction);
		return errorService.getErrorById("ER001");
	}
	
	public String update(Long id, DeathClaimTransactionPas transaction) {
		DeathClaimTransactionPas transaction1 = deathClaimTransRepo.getActiveById(id);
		if (transaction.getCompanyId() != null) {
			transaction1.setCompanyId(transaction.getCompanyId());
		}
		if (transaction.getPolicyNo() != null) {
			transaction1.setPolicyNo(transaction.getPolicyNo());
		}
		if (transaction.getTransNo() != null) {
			transaction1.setTransNo(transaction.getTransNo());
		}
		if(transaction.getUinNumber()!=null) {
			transaction1.setUinNumber(transaction.getUinNumber());
		}
		if(transaction.getProductType()!=null) {
			transaction1.setProductType(transaction.getProductType());
		}
		if(transaction.getContractType()!=null) {
			transaction1.setContractType(transaction.getContractType());
		}
		if(transaction.getPlanName()!=null) {
			transaction1.setPlanName(transaction.getPlanName());
		}
		if(transaction.getPlanCode()!=null) {
			transaction1.setPlanCode(transaction.getPlanCode());
		}
		if(transaction.getDoc()!=null) {
			transaction1.setDoc(transaction.getDoc());
		}
		
		
		if(transaction.getRiskComDate()!=null) {
			transaction1.setRiskComDate(transaction.getRiskComDate());
		}
		if(transaction.getPolicyTerm()!=null) {
			transaction1.setPolicyTerm(transaction.getPolicyTerm());
		}
		if(transaction.getPremiumTerm()!=null) {
			transaction1.setPremiumTerm(transaction.getPremiumTerm());
		}
		if(transaction.getInstallmentPremium()!=null) {
			transaction1.setInstallmentPremium(transaction.getInstallmentPremium());
		}
		if(transaction.getBillFreq()!=null) {
			transaction1.setBillFreq(transaction.getBillFreq());
		}
		

		if(transaction.getPolicyStatus()!=null) {
			transaction1.setPolicyStatus(transaction.getPolicyStatus());
		}
		if(transaction.getFup()!=null) {
			transaction1.setFup(transaction.getFup());
		}
		if(transaction.getDateOfDeath()!=null) {
			transaction1.setDateOfDeath(transaction.getDateOfDeath());
		}
		if(transaction.getDateOfRevival()!=null) {
			transaction1.setDateOfRevival(transaction.getDateOfRevival());
		}
		if(transaction.getDateOfIntimation()!=null) {
			transaction1.setDateOfIntimation(transaction.getDateOfIntimation());
		}
		

		if(transaction.getDateOfLogin()!=null) {
			transaction1.setDateOfLogin(transaction.getDateOfLogin());
		}
		if(transaction.getCauseOfDeath()!=null) {
			transaction1.setCauseOfDeath(transaction.getCauseOfDeath());
		}
		if(transaction.getDeathReasonCode()!=null) {
			transaction1.setDeathReasonCode(transaction.getDeathReasonCode());
		}
		if(transaction.getBasicSumAssured()!=null) {
			transaction1.setBasicSumAssured(transaction.getBasicSumAssured());
		}
		if(transaction.getAdditionalSumAssured()!=null) {
			transaction1.setAdditionalSumAssured(transaction.getAdditionalSumAssured());
		}
		

		if(transaction.getTermRiderSumAssured()!=null) {
			transaction1.setTermRiderSumAssured(transaction.getTermRiderSumAssured());
		}
		if(transaction.getInbuiltRiderSumAssured()!=null) {
			transaction1.setInbuiltRiderSumAssured(transaction.getInbuiltRiderSumAssured());
		}
		if(transaction.getReversionaryBonus()!=null) {
			transaction1.setReversionaryBonus(transaction.getReversionaryBonus());
		}
		if(transaction.getInterimBonus()!=null) {
			transaction1.setInterimBonus(transaction.getInterimBonus());
		}
		if(transaction.getGuranteedBonus()!=null) {
			transaction1.setGuranteedBonus(transaction.getGuranteedBonus());
		}
		

		if(transaction.getLoyaltyAddition()!=null) {
			transaction1.setLoyaltyAddition(transaction.getLoyaltyAddition());
		}
		if(transaction.getOtherRider()!=null) {
			transaction1.setOtherRider(transaction.getOtherRider());
		}
		if(transaction.getTerminalBonus()!=null) {
			transaction1.setTerminalBonus(transaction.getTerminalBonus());
		}
		if(transaction.getRefundOfPurchasePrice()!=null) {
			transaction1.setRefundOfPurchasePrice(transaction.getRefundOfPurchasePrice());
		}
		if(transaction.getTotalBonus()!=null) {
			double totalBonus = transaction1.getReversionaryBonus()+transaction1.getInterimBonus();
			transaction1.setTotalBonus(totalBonus);
		}
		

		if(transaction.getFundValue()!=null) {
			transaction1.setFundValue(transaction.getFundValue());
		}
		if(transaction.getEffectiveDate()!=null) {
			transaction1.setEffectiveDate(transaction.getEffectiveDate());
		}
		if(transaction.getApprovalDate()!=null) {
			transaction1.setApprovalDate(transaction.getApprovalDate());
		}
		if(transaction.getTotalDeathClaim()!=null) {
			transaction1.setTotalDeathClaim(transaction.getTotalDeathClaim());
		}
		if(transaction.getPolicyDeposit()!=null) {
			transaction1.setPolicyDeposit(transaction.getPolicyDeposit());
		}
		if(transaction.getPenalInterest()!=null) {
			transaction1.setPenalInterest(transaction.getPenalInterest());
		}
		if(transaction.getGrossPay()!=null) {
			transaction1.setGrossPay(transaction.getGrossPay());
		}
		

		if(transaction.getTerminalPremRecov()!=null) {
			transaction1.setTerminalPremRecov(transaction.getTerminalPremRecov());
		}
		if(transaction.getInterestOnPrem()!=null) {
			transaction1.setInterestOnPrem(transaction.getInterestOnPrem());
		}
		if(transaction.getGstOnPrem()!=null) {
			transaction1.setGstOnPrem(transaction.getGstOnPrem());
		}
		if(transaction.getCdaCharges()!=null) {
			transaction1.setCdaCharges(transaction.getCdaCharges());
		}
		if(transaction.getOtherCharges()!=null) {
			transaction1.setOtherCharges(transaction.getOtherCharges());
		}
		

		if(transaction.getPolicyLoan()!=null) {
			transaction1.setPolicyLoan(transaction.getPolicyLoan());
		}
		if(transaction.getPolicyLoanInterest()!=null) {
			transaction1.setPolicyLoanInterest(transaction.getPolicyLoanInterest());
		}
		if(transaction.getMoneybackPaidRecov()!=null) {
			transaction1.setMoneybackPaidRecov(transaction.getMoneybackPaidRecov());
		}
		if(transaction.getAnnuityPaidRecov()!=null) {
			transaction1.setAnnuityPaidRecov(transaction.getAnnuityPaidRecov());
		}
		if(transaction.getMortalityChargeRefund()!=null) {
			transaction1.setMortalityChargeRefund(transaction.getMortalityChargeRefund());
		}
		
		if(transaction.getYearlyAnnuityAmunt()!=null) {
			transaction1.setYearlyAnnuityAmunt(transaction.getYearlyAnnuityAmunt());
		}
		if(transaction.getAnnuityStartDate()!=null) {
			transaction1.setAnnuityStartDate(transaction.getAnnuityStartDate());
		}
		if(transaction.getAnnuityGuranteedYears()!=null) {
			transaction1.setAnnuityGuranteedYears(transaction.getAnnuityGuranteedYears());
		}
		
		
		if(transaction.getAdminFeeRefund()!=null) {
			transaction1.setAdminFeeRefund(transaction.getAdminFeeRefund());
		}
		if(transaction.getGuranteedAdditionCharges()!=null) {
			transaction1.setGuranteedAdditionCharges(transaction.getGuranteedAdditionCharges());
		}
		if(transaction.getTds()!=null) {
			transaction1.setTds(transaction.getTds());
		}
		if(transaction.getTotalRecovery()!=null) {
			transaction1.setTotalRecovery(transaction.getTotalRecovery());
		}
		if(transaction.getNetPayable()!=null) {
			transaction1.setNetPayable(transaction.getNetPayable());
		}
		
		
		if(transaction.getNominationFlag()!=null) {
			transaction1.setNominationFlag(transaction.getNominationFlag());
		}
		if(transaction.getNomineeClientId()!=null) {
			transaction1.setNomineeClientId(transaction.getNomineeClientId());
		}
		if(transaction.getAssignementFlag()!=null) {
			transaction1.setAssignementFlag(transaction.getAssignementFlag());
		}
		if(transaction.getAssigneeClientId()!=null) {
			transaction1.setAssigneeClientId(transaction.getAssigneeClientId());
		}
		if(transaction.getDateOfAssignment()!=null) {
			transaction1.setDateOfAssignment(transaction.getDateOfAssignment());
		}
		if(transaction.getClaimId()!=null) {
			transaction1.setClaimId(transaction.getClaimId());
		}
		if(transaction.getDeathClaimPayoutDate()!=null) {
			transaction1.setDeathClaimPayoutDate(transaction.getDeathClaimPayoutDate());
		}
		
		if (transaction.getMakerFlag() != null) {
			transaction1.setMakerFlag(transaction.getMakerFlag());
		}
		if (transaction.getCheckerFlag() != null) {
			transaction1.setCheckerFlag(transaction.getCheckerFlag());
		}
		if (transaction.getLeapApprovalFlag() != null) {
			transaction1.setLeapApprovalFlag(transaction.getLeapApprovalFlag());
		}
		if (transaction.getLeapApprovalDate() != null) {
			transaction1.setLeapApprovalDate(transaction.getLeapApprovalDate());
		}
		if (transaction.getLeapApprovalRemarks() != null) {
			transaction1.setLeapApprovalRemarks(transaction.getLeapApprovalRemarks());
		}
		if (transaction.getQcUserId() != null) {
			transaction1.setQcUserId(transaction.getQcUserId());
		}
		if (transaction.getInterimStatus() != null) {
			transaction1.setInterimStatus(transaction.getInterimStatus());
		}
		
		deathClaimTransRepo.save(transaction1);
		return errorService.getErrorById("ER003");
	}
	
	
	public String deactivate(Long id) {
		DeathClaimTransactionPas transaction = deathClaimTransRepo.getActiveById(id);
		transaction.setValidFlag(-1);
		deathClaimTransRepo.save(transaction);
		return errorService.getErrorById("ER012");
	}

	public String hardDelete(Long id) {
		deathClaimTransRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}

	public List<DeathClaimTransactionPas> search(String key) {
		return deathClaimTransRepo.globalSearch(key);
	}

}
