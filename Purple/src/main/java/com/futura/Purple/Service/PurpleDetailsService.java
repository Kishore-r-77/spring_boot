package com.futura.Purple.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.ClientDetailsPas;
import com.futura.Purple.Entity.CoverDetailsPas;
import com.futura.Purple.Entity.FundDetailsPas;
import com.futura.Purple.Entity.MortFlagMaster;
import com.futura.Purple.Entity.PolicyDetailsPas;
import com.futura.Purple.Entity.PurpleDetails;
import com.futura.Purple.Entity.PurpleFundDetails;
import com.futura.Purple.Entity.PurpleNav;
import com.futura.Purple.Entity.StamDutyMaster;
import com.futura.Purple.Entity.TransactionPas;
import com.futura.Purple.Entity.UinMaster;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.ClientDetailsPasRepository;
import com.futura.Purple.Repository.CoverDetailsPasRepository;
import com.futura.Purple.Repository.FundDetailsPasRepository;
import com.futura.Purple.Repository.MedicalDetailsRepository;
import com.futura.Purple.Repository.MortFlagMasterRepository;
import com.futura.Purple.Repository.MortalityRatesRepository;
import com.futura.Purple.Repository.PolicyDetailsPasRepository;
import com.futura.Purple.Repository.PurpleDetailsRepository;
import com.futura.Purple.Repository.PurpleFundDetailsRepository;
import com.futura.Purple.Repository.PurpleNavRepository;
import com.futura.Purple.Repository.StamDutyMasterRepository;
import com.futura.Purple.Repository.TransactionPasRepository;
import com.futura.Purple.Repository.UinMasterRepository;

@Service
public class PurpleDetailsService {

	@Autowired
	private PurpleDetailsRepository purpledetailsRepo;

	@Autowired
	private PolicyDetailsPasRepository policyDetailsPasRepository;

	@Autowired
	private ClientDetailsPasRepository clientDetailsPasRepository;

	@Autowired
	private TransactionPasRepository transactionPasRepository;

	@Autowired
	private CoverDetailsPasRepository coverDetailsPasRepository;

	@Autowired
	private MortalityRatesRepository mortalityRatesRepository;

	@Autowired
	private MedicalDetailsRepository medicalDetailsRepository;

	@Autowired
	private StamDutyMasterRepository stamDutyMasterRepository;

	@Autowired
	private MortFlagMasterRepository mortFlagMasterRepository;

	@Autowired
	private FundDetailsPasRepository fundDetailsPasRepo;

	@Autowired
	private PurpleFundDetailsRepository purpleFundDetailsRepo;

	@Autowired
	private PurpleNavRepository purpleNavRepo;
	
	@Autowired
	private UinMasterRepository uinMasterRepository;

	@Autowired
	private ErrorService errorService;

	public List<PurpleDetails> getAllQCPending() {
		return purpledetailsRepo.getAllQCPending();
	}

	public List<PurpleDetails> getAllFailed() {
		return purpledetailsRepo.getAllFailedRecord();
	}

	public List<PurpleDetails> getAllPassed() {
		return purpledetailsRepo.getAllPassedRecord();
	}

	public PurpleDetails getById(Long id) {
		return purpledetailsRepo.getActiveById(id);
	}

	public PurpleDetails getByPolicyNo(Long policyNo) {
		return purpledetailsRepo.getByPolicyNo(policyNo);
	}

	public PurpleDetails getByTransNo(Long policyNo) {
		TransactionPas trans = transactionPasRepository.findProcessedByPolicyNo(policyNo);
		return purpledetailsRepo.getByTransNo(trans.getFlcTransNo());
	}
	
	public String reIniiatedTrans(Long policyNo) {
		
		PolicyDetailsPas policy = policyDetailsPasRepository.getActiveByPolicyNo(policyNo);
		TransactionPas trans = transactionPasRepository.getByFlcPolicyNo(policyNo);
		PurpleDetails pd = purpledetailsRepo.getByPolicyNo(policyNo);
		
		if(policy.getUinNumber().contains("N")) {
			
			trans.setInterimStatus("Initiated");
			trans.setPurpleApprovalFlag(null);
			trans.setPurpleApprovalRemark(null);
			trans.setPurpleApprovalDate(null);
			trans.setApprovalQcUserId(null);
			
			transactionPasRepository.save(trans);
			pd.setValidFlag(-1);;
			purpledetailsRepo.save(pd);
		}else if(policy.getUinNumber().contains("L")) {
			
			trans.setInterimStatus("Initiated");
			trans.setPurpleApprovalFlag(null);
			trans.setPurpleApprovalRemark(null);
			trans.setPurpleApprovalDate(null);
			trans.setApprovalQcUserId(null);
			
			List<PurpleFundDetails> purpleFunds = purpleFundDetailsRepo.getallByPolicyNo(policyNo);
			
			for (PurpleFundDetails purpleFund : purpleFunds) {
				purpleFundDetailsRepo.delete(purpleFund);
			}
			
			transactionPasRepository.save(trans);
			pd.setValidFlag(-1);
			purpledetailsRepo.save(pd);
		}
		
		return errorService.getErrorById("ER003");
	}

	public String addNonUlip(Long policyNo, Long userId) throws ParseException {

		long val = 0;
		String msg = "";
		String flag = "";

		PolicyDetailsPas policyPas = policyDetailsPasRepository.getActiveByPolicyNo(policyNo);
		TransactionPas trans = transactionPasRepository.findByFlcPolicyNo(policyNo);
		System.out.println("********** trans No ********** " + trans.getFlcTransNo());
		Long tp = calculateTotalPremium(policyNo);
	//	Double tp1 = calculateTotalPremium1(policyPas.getPremToDate(), policyPas.getDocDate(), policyPas.getInstallmentPremium().doubleValue(), Integer.parseInt(policyPas.getBillFreq()));
		Long policyDeposite = trans.getFlcPolicyDop();

		Double stamDuty = calculateStampDuty(policyNo);
		Double mortRate = calculateNonUlipMortalityRates(policyNo);

		Long mfRate = calculateMFRate(policyNo);

		if (mortRate != -1 && mfRate != -1) {

			PurpleDetails purpledetails1 = new PurpleDetails();

			purpledetails1.setValidFlag(1);
			purpledetails1.setCompanyId(policyPas.getCompanyId());
			purpledetails1.setPolicyNo(policyNo);
			purpledetails1.setTranDate(trans.getFlcLogDate());
			purpledetails1.setTranNo(trans.getFlcTransNo());
			purpledetails1.setUinNumber(trans.getUinNumber());
			purpledetails1.setTotlPremium(tp);
			purpledetails1.setAvalSuspense(policyDeposite);
			purpledetails1.setPenalInterest(val);
			purpledetails1.setMedicalFee(mfRate);
			purpledetails1.setStampDuty(stamDuty.doubleValue());
			purpledetails1.setMortCharge(mortRate);

			Double grossPay = tp + trans.getFlcPolicyDop().doubleValue() + val;
			purpledetails1.setGrossPayable(grossPay);

			Double recoveries = val + mortRate + stamDuty;
			purpledetails1.setRecoveries(recoveries);

			Double netPay = purpledetails1.getGrossPayable() - purpledetails1.getRecoveries();
			purpledetails1.setNetPayable(netPay);

			purpledetails1.setCreatedBy(userId);
			purpledetails1.setModifiedBy(userId);
			purpledetailsRepo.save(purpledetails1);

			trans.setInterimStatus("Processed");
			transactionPasRepository.save(trans);

			long penalInt = calculatePenalIntrst(trans, purpledetails1);
			
			if(policyPas.getMedicalFlag().equalsIgnoreCase("yes")) {
				purpledetails1.setMedicalFee(mfRate);
			}else {
				purpledetails1.setMedicalFee(val);
			}
			
			
			purpledetails1.setPenalInterest(penalInt);

			Double grossPay1 = tp + trans.getFlcPolicyDop().doubleValue() + penalInt;

			BigDecimal bd2 = new BigDecimal(grossPay1).setScale(2, RoundingMode.HALF_UP);

			purpledetails1.setGrossPayable(bd2.doubleValue());

			Double recoveries1 = mfRate + mortRate + stamDuty;
			
			BigDecimal bd3 = new BigDecimal(recoveries1).setScale(2, RoundingMode.HALF_UP);
			purpledetails1.setRecoveries(bd3.doubleValue());

			Double netPay1 = purpledetails1.getGrossPayable() - purpledetails1.getRecoveries();
			BigDecimal bd1 = new BigDecimal(netPay1).setScale(2, RoundingMode.HALF_UP);
			purpledetails1.setNetPayable(bd1.doubleValue());

			purpledetailsRepo.save(purpledetails1);

			if (!trans.getFlcPremRefund().equals(purpledetails1.getTotlPremium())) {
				flag = "Fail";
				String msg1 = "Premium Refund";
				msg = msg1;
			}

			if (!trans.getFlcPolicyDop().equals(purpledetails1.getAvalSuspense())) {
				flag = "Fail";
				String msg1 = "Aval Suspense";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getPenalIntrest().equals(purpledetails1.getPenalInterest())) {
				flag = "Fail";
				String msg1 = "Penal Intrest";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}
			if (!trans.getMedicalFee().equals(purpledetails1.getMedicalFee())) {
				flag = "Fail";
				String msg1 = "Medical Fee";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getStamDuty().equals(purpledetails1.getStampDuty())) {
				flag = "Fail";
				String msg1 = "Stam Duty";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}
			if (!trans.getRiskPremRecov().equals(purpledetails1.getMortCharge())) {
				flag = "Fail";
				String msg1 = "Mortality Charge";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}
			if (!trans.getGrossFlcPay().equals(purpledetails1.getGrossPayable())) {
				flag = "Fail";
				String msg1 = "Gross Payable";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}
			if (!trans.getTotalRecov().equals(purpledetails1.getRecoveries())) {
				flag = "Fail";
				String msg1 = "Recoveries";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}
			if (!trans.getNetFlcPay().equals(purpledetails1.getNetPayable())) {
				flag = "Fail";
				String msg1 = "Net Payable";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (flag != "Fail") {
				flag = "Pass";
				msg = "Calculation Matching";
			}
			purpledetails1.setPfFlag(flag);
			purpledetails1.setPfRemarks(msg);

			purpledetailsRepo.save(purpledetails1);

			return errorService.getErrorById("ER001");

		} else {

			Long value = (long) 0;

			PurpleDetails purpledetails1 = new PurpleDetails();

			purpledetails1.setValidFlag(1);
			purpledetails1.setCompanyId(policyPas.getCompanyId());
			purpledetails1.setPolicyNo(policyNo);
			purpledetails1.setTranDate(trans.getFlcLogDate());
			purpledetails1.setTranNo(trans.getFlcTransNo());
			purpledetails1.setUinNumber(trans.getUinNumber());
			purpledetails1.setTotlPremium(value);
			purpledetails1.setAvalSuspense(value);
			purpledetails1.setPenalInterest(value);
			purpledetails1.setMedicalFee(value);
			purpledetails1.setStampDuty(0.0);
			purpledetails1.setMortCharge(0.0);
			purpledetails1.setGrossPayable(0.0);
			purpledetails1.setRecoveries(0.0);
			purpledetails1.setNetPayable(0.0);
			purpledetails1.setPfFlag("Fail");

			if (mortRate == -1 && mfRate == -1) {
				purpledetails1.setPfRemarks("Values are missing in mortality table and medical table ");

			} else if (mortRate == -1 && mfRate != -1) {
				purpledetails1.setPfRemarks("Values are missing in mortality table ");

			} else if (mortRate != -1 && mfRate == -1) {
				purpledetails1.setPfRemarks("Values are missing in medical table ");

			}

			purpledetailsRepo.save(purpledetails1);

			trans.setInterimStatus("Processed");
			transactionPasRepository.save(trans);
			return errorService.getErrorById("ER001");
		}

	}

	public String addUlip(Long policyNo, Long userId) throws ParseException {

		long val = 0;
		String msg = "";
		String flag = "";

		PolicyDetailsPas policyPas = policyDetailsPasRepository.getActiveByPolicyNo(policyNo);
		TransactionPas trans = transactionPasRepository.findByFlcPolicyNo(policyNo);
		System.out.println("********** trans No ********** " + trans.getFlcTransNo());
		Long tp = calculateTotalPremium(policyNo);
		Long policyDeposite = trans.getFlcPolicyDop();
		Double stamDuty = calculateStampDuty(policyNo);
		Double mortRate = calculateUlipMortalityRates(policyNo);
		Long mfRate = calculateMFRate(policyNo);
		Double fundValue = calculateFundValue(trans.getFlcTransNo());

		if (mortRate != -1 && mfRate != -1) {

			PurpleDetails purpledetails1 = new PurpleDetails();

			purpledetails1.setValidFlag(1);
			purpledetails1.setCompanyId(policyPas.getCompanyId());
			purpledetails1.setPolicyNo(policyNo);
			purpledetails1.setTranDate(trans.getFlcLogDate());
			purpledetails1.setTranNo(trans.getFlcTransNo());
			purpledetails1.setUinNumber(trans.getUinNumber());
			purpledetails1.setTotlPremium(tp);
			purpledetails1.setEffDate(trans.getEffDate());
			purpledetails1.setFundValue(fundValue);
			purpledetails1.setAvalSuspense(policyDeposite);
			purpledetails1.setPenalInterest(val);
			
			if(policyPas.getMedicalFlag().equalsIgnoreCase("Yes")) {
				purpledetails1.setMedicalFee(mfRate);
			}else {
				purpledetails1.setMedicalFee(val);
			}
			
			purpledetails1.setStampDuty(stamDuty.doubleValue());
			purpledetails1.setMortCharge(mortRate);

			Double grossPay = fundValue.longValue() + trans.getFlcPolicyDop().doubleValue() + val + mortRate;
			purpledetails1.setGrossPayable(grossPay);

			Double recoveries = mfRate + stamDuty;
			purpledetails1.setRecoveries(recoveries);

			Double netPay = purpledetails1.getGrossPayable() - purpledetails1.getRecoveries();
			purpledetails1.setNetPayable(netPay);

			purpledetails1.setCreatedBy(userId);
			purpledetails1.setModifiedBy(userId);
			purpledetailsRepo.save(purpledetails1);

			trans.setInterimStatus("Processed");
			transactionPasRepository.save(trans);

			long penalInt = calculatePenalIntrst(trans, purpledetails1);

			purpledetails1.setMedicalFee(mfRate);
			purpledetails1.setPenalInterest(penalInt);

			Double grossPay1 = fundValue + trans.getFlcPolicyDop() + penalInt + mortRate;

			BigDecimal bd2 = new BigDecimal(grossPay1).setScale(2, RoundingMode.HALF_UP);

			purpledetails1.setGrossPayable(bd2.doubleValue());

			Double recoveries1 = mfRate + stamDuty;
			purpledetails1.setRecoveries(recoveries1);

			Double netPay1 = purpledetails1.getGrossPayable() - purpledetails1.getRecoveries();
			BigDecimal bd1 = new BigDecimal(netPay1).setScale(2, RoundingMode.HALF_UP);
			purpledetails1.setNetPayable(bd1.doubleValue());

			purpledetailsRepo.save(purpledetails1);

			if (!trans.getFlcTotalPrem().equals(purpledetails1.getTotlPremium())) {
				flag = "Fail";
				String msg1 = "Total Premium ";
				msg = msg1;
			}

			if (!trans.getFundValue().equals(purpledetails1.getFundValue())) {
				flag = "Fail";
				String msg1 = "Fund Value";
				msg = msg1;
			}

			if (!trans.getFlcPolicyDop().equals(purpledetails1.getAvalSuspense())) {
				flag = "Fail";
				String msg1 = "Aval Suspense";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getPenalIntrest().equals(purpledetails1.getPenalInterest())) {
				flag = "Fail";
				String msg1 = "Penal Intrest";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}
			if (!trans.getMedicalFee().equals(purpledetails1.getMedicalFee())) {
				flag = "Fail";
				String msg1 = "Medical Fee";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getStamDuty().equals(purpledetails1.getStampDuty())) {
				flag = "Fail";
				String msg1 = "Stam Duty";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}
			if (!trans.getMortChargeRefund().equals(purpledetails1.getMortCharge())) {
				flag = "Fail";
				String msg1 = "Mortality Charge";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}
			if (!trans.getGrossFlcPay().equals(purpledetails1.getGrossPayable())) {
				flag = "Fail";
				String msg1 = "Gross Payable";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}
			if (!trans.getTotalRecov().equals(purpledetails1.getRecoveries())) {
				flag = "Fail";
				String msg1 = "Recoveries";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}
			if (!trans.getNetFlcPay().equals(purpledetails1.getNetPayable())) {
				flag = "Fail";
				String msg1 = "Net Payable";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (flag != "Fail") {
				flag = "Pass";
				msg = "Calculation Matching";
			}
			purpledetails1.setPfFlag(flag);
			purpledetails1.setPfRemarks(msg);

			purpledetailsRepo.save(purpledetails1);

			return errorService.getErrorById("ER001");

		} else {

			Long value = (long) 0;

			PurpleDetails purpledetails1 = new PurpleDetails();

			purpledetails1.setValidFlag(1);
			purpledetails1.setCompanyId(policyPas.getCompanyId());
			purpledetails1.setPolicyNo(policyNo);
			purpledetails1.setTranDate(trans.getFlcLogDate());
			purpledetails1.setTranNo(trans.getFlcTransNo());
			purpledetails1.setUinNumber(trans.getUinNumber());
			purpledetails1.setTotlPremium(value);
			purpledetails1.setAvalSuspense(value);
			purpledetails1.setPenalInterest(value);
			purpledetails1.setMedicalFee(value);
			purpledetails1.setStampDuty(0.0);
			purpledetails1.setMortCharge(0.0);
			purpledetails1.setGrossPayable(0.0);
			purpledetails1.setRecoveries(0.0);
			purpledetails1.setNetPayable(0.0);
			purpledetails1.setPfFlag("Fail");

			if (mortRate == -1 && mfRate == -1) {
				purpledetails1.setPfRemarks("Values are missing in mortality table and medical table ");

			} else if (mortRate == -1 && mfRate != -1) {
				purpledetails1.setPfRemarks("Values are missing in mortality table ");

			} else if (mortRate != -1 && mfRate == -1) {
				purpledetails1.setPfRemarks("Values are missing in medical table ");

			}

			purpledetailsRepo.save(purpledetails1);

			trans.setInterimStatus("Processed");
			transactionPasRepository.save(trans);
			return errorService.getErrorById("ER001");
		}

	}

	public String qcUpdate(PurpleDetails purpleDetails, Long policyNo, Long userId) {

		TransactionPas transactions = transactionPasRepository.findProcessedByPolicyNo(policyNo);
		PurpleDetails pd = purpledetailsRepo.getByTransNo(transactions.getFlcTransNo());

		pd.setApprovRemarks(purpleDetails.getApprovRemarks());
		pd.setApprovFlag(purpleDetails.getApprovFlag());
		purpledetailsRepo.save(pd);
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String approvalDate = dateFormat.format(date);

		pd.setApprovDate(approvalDate);

		purpledetailsRepo.save(pd);
		System.out.println("*********** Transaction Approv Flag ***********" + purpleDetails.getApprovFlag());
		transactions.setPurpleApprovalFlag(pd.getApprovFlag());
		transactions.setPurpleApprovalRemark(pd.getApprovRemarks());
		transactions.setPurpleApprovalDate(pd.getApprovDate());
		transactions.setApprovalQcUserId(userId);
		transactions.setInterimStatus("QC Completed");
		transactionPasRepository.save(transactions);
		if (transactions.getPurpleApprovalFlag().equals(pd.getApprovFlag())) {
			pd.setPfFlagUpdate("Transaction Updated");
		}
		purpledetailsRepo.save(pd);

		if (transactions.getPurpleApprovalFlag().equals("Fail")) {

			System.out.println("Inside If Statement ");

			PolicyDetailsPas policy = policyDetailsPasRepository.getActiveByPolicyNo(transactions.getFlcPolicyNo());
			List<CoverDetailsPas> covers = coverDetailsPasRepository.getAllByPolicyNo(policy.getChdrNum());

			System.out.println("Client Number " + policy.getClntNum());
			ClientDetailsPas client = clientDetailsPasRepository.getActiveByClientNo(policy.getClntNum());

			System.out.println("Client Number " + client.getClntNum());

			policy.setValidFlag(-1);
			policyDetailsPasRepository.save(policy);

			for (CoverDetailsPas cover : covers) {
				cover.setValidFlag(-1);
				coverDetailsPasRepository.save(cover);
			}
			client.setValidFlag(-1);
			clientDetailsPasRepository.save(client);

		}
		return errorService.getErrorById("ER003");
	}

	public String update(Long id, PurpleDetails purpledetails) {
		PurpleDetails purpledetails1 = purpledetailsRepo.getActiveById(id);
		if (purpledetails.getId() != null) {
			purpledetails1.setId(purpledetails.getId());
		}
		if (purpledetails.getCompanyId() != null) {
			purpledetails1.setCompanyId(purpledetails.getCompanyId());
		}
		if (purpledetails.getTranDate() != null) {
			purpledetails1.setTranDate(purpledetails.getTranDate());
		}
		if (purpledetails.getPolicyNo() != null) {
			purpledetails1.setPolicyNo(purpledetails.getPolicyNo());
		}
		if (purpledetails.getTranNo() != null) {
			purpledetails1.setTranNo(purpledetails.getTranNo());
		}
		if (purpledetails.getTotlPremium() != null) {
			purpledetails1.setTotlPremium(purpledetails.getTotlPremium());
		}
		if (purpledetails.getAvalSuspense() != null) {
			purpledetails1.setAvalSuspense(purpledetails.getAvalSuspense());
		}
		if (purpledetails.getPenalInterest() != null) {
			purpledetails1.setPenalInterest(purpledetails.getPenalInterest());
		}
		if (purpledetails.getMedicalFee() != null) {
			purpledetails1.setMedicalFee(purpledetails.getMedicalFee());
		}
		if (purpledetails.getStampDuty() != null) {
			purpledetails1.setStampDuty(purpledetails.getStampDuty());
		}
		if (purpledetails.getMortCharge() != null) {
			purpledetails1.setMortCharge(purpledetails.getMortCharge());
		}
		if (purpledetails.getGrossPayable() != null) {
			purpledetails1.setGrossPayable(purpledetails.getGrossPayable());
		}
		if (purpledetails.getRecoveries() != null) {
			purpledetails1.setRecoveries(purpledetails.getRecoveries());
		}
		if (purpledetails.getNetPayable() != null) {
			purpledetails1.setNetPayable(purpledetails.getNetPayable());
		}
		if (purpledetails.getPfFlag() != null) {
			purpledetails1.setPfFlag(purpledetails.getPfFlag());
		}
		if (purpledetails.getPfRemarks() != null) {
			purpledetails1.setPfRemarks(purpledetails.getPfRemarks());
		}
		if (purpledetails.getApprovFlag() != null) {
			purpledetails1.setApprovFlag(purpledetails.getApprovFlag());
		}
		if (purpledetails.getApprovRemarks() != null) {
			purpledetails1.setApprovRemarks(purpledetails.getApprovRemarks());
		}
		if (purpledetails.getApprovDate() != null) {
			purpledetails1.setApprovDate(purpledetails.getApprovDate());
		}
		if (purpledetails.getPfFlagUpdate() != null) {
			purpledetails1.setPfFlagUpdate(purpledetails.getPfFlagUpdate());
		}
		purpledetailsRepo.save(purpledetails1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		PurpleDetails purpledetails = purpledetailsRepo.getActiveById(id);
		purpledetails.setValidFlag(-1);
		purpledetailsRepo.save(purpledetails);
		return errorService.getErrorById("ER012");
	}

	public String hardDelete(Long id) {
		purpledetailsRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}

	public List<PurpleDetails> search(String key) {
		return purpledetailsRepo.globalSearch(key);
	}

	public long calculatePenalIntrst(TransactionPas trans, PurpleDetails purpledetails1) throws ParseException {
		String approv = trans.getFlcApprovalDate();
		String req = trans.getFlcReqDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date approvDate = sdf.parse(approv);
		Date reqDate = sdf.parse(req);
		long timeDiff = Math.abs(approvDate.getTime() - reqDate.getTime());
		long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

		long penalInt = 0;

		if (daysDiff > 15) {

			double val1 = (0.09 / 365);
			double val2 = val1 * daysDiff;

			System.out.println("************ Net Payble *************  " + purpledetails1.getNetPayable());

			long penal = (long) (purpledetails1.getNetPayable() * val2);

			penalInt = penal;

		} else if (daysDiff <= 15) {
			long penal = 0;
			penalInt = penal;
		}
		return penalInt;
	}

	public Long calculateTotalPremium(Long policyNo) throws ParseException {

		PolicyDetailsPas policyPas = policyDetailsPasRepository.getActiveByPolicyNo(policyNo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date premToDate = sdf.parse(policyPas.getPremToDate());
		Date docDate = sdf.parse(policyPas.getDocDate());

		long timeDiff = Math.abs(premToDate.getTime() - docDate.getTime());

		double daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

//		double months =Math.ceil(((daysDiff) / 30f));

		double months = Math.floor(((daysDiff) / 30f));

		Double val = (double) (months / Integer.parseInt(policyPas.getBillFreq()));

		double Premium = policyPas.getInstallmentPremium() * val;

		System.out.println("********* FUP ******** " + policyPas.getPremToDate());
		System.out.println("********* DOC ******** " + policyPas.getDocDate());
		System.out.println("********* MONTHS ******** " + months);
		System.out.println("********* months / bill freq ******** " + val);
		System.out.println("********* Premium ******** " + Premium);

		Long totalPremium = (long) Premium;

		return totalPremium;
	}
	
//	public Double calculateTotalPremium1(String premToDate , String docDate, Double installmentPremium, Integer billFreq) throws ParseException {
//		
//		Long months = getDiffInMonth(premToDate, docDate);
//
//		Double val = (months.doubleValue() /billFreq);
//
//		Double totalPremium = installmentPremium * val;
//
//		return totalPremium;
//	}
//	
//	public Long getDiffInMonth(String startDate, String endDate) throws ParseException {
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
//		Date startDate1 = inputFormat.parse(startDate);
//		Date endDate1 = inputFormat.parse(endDate);
//		String StartDate = sdf.format(startDate1);
//		String EndDate = sdf.format(endDate1);
//
//		Long month = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(StartDate)),
//				YearMonth.from(LocalDate.parse(EndDate)));
//
//		return month;
//	}

	public Long calculateMFRate(Long policyNo) {

		PolicyDetailsPas policyPas = policyDetailsPasRepository.getActiveByPolicyNo(policyNo);
		TransactionPas trans = transactionPasRepository.findByFlcPolicyNo(policyNo);

		long mfRate;

		if (policyPas.getMedicalFlag().equalsIgnoreCase("Yes")) {

			System.out.println("Inside medical flag data ");
			Long medicalfeeRate = medicalDetailsRepository.getMfRate(trans.getMedicalCategory(),
					trans.getMedicalCenter(), trans.getMedicatTpaCode());

			if (medicalfeeRate != null) {
				mfRate = medicalfeeRate;
			} else {
				mfRate = -1;
			
			}

		} else {
			mfRate = 0;
		}
		return mfRate;
	}

	public Double calculateNonUlipMortalityRates(Long policyNum) throws ParseException {
		TransactionPas trans = transactionPasRepository.findByFlcPolicyNo(policyNum);

		Integer age = policyDetailsPasRepository.getByAge(policyNum);

		List<CoverDetailsPas> covers = coverDetailsPasRepository.getAllByPolicyNo(policyNum);

		double val = 0;

		for (CoverDetailsPas coverPas : covers) {

			MortFlagMaster mortFlagMaster = mortFlagMasterRepository.getByUniqueNo(coverPas.getUinNumber());

			if (mortFlagMaster.getMortFlag().equalsIgnoreCase("Yes")) {

				Float mortalityRates = mortalityRatesRepository.findByCCCT(coverPas.getUinNumber(), age,
						coverPas.getPremCessTerm());

				if (mortalityRates != null) {

					String flcReqDate = trans.getFlcReqDate();

					String coverStartDate = coverPas.getRiskComDate();

					System.out.println("************** Req Date *************** " + flcReqDate);
					System.out.println("************** Risk Com date *************** " + coverStartDate);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

					Date d1 = sdf.parse(flcReqDate);

					Date d2 = sdf.parse(coverStartDate);

					long diffInTime = d1.getTime() - d2.getTime();

					long difference_In_Days = (diffInTime / (1000 * 60 * 60 * 24)) % 365;

					double difference;

					if (difference_In_Days < 0) {

						difference = (float) (difference_In_Days * -1);

					} else {
						difference = (float) (difference_In_Days);
					}

					double rates = ((mortalityRates * coverPas.getSumAssured()) / 1000f);
					System.out.println("************** Rates *************** " + rates);
					System.out.println("************** Difference in days *************** " + difference);

					double diffInDaysplusOne = difference + 1;
					double dateDiff = diffInDaysplusOne / 365;

					System.out.println(
							"** Difference in days +1 ** " + diffInDaysplusOne + "   *** /365 ***  " + dateDiff);

					double mortalityCharge = rates * dateDiff;
//					Long mortalityCharges = (long) mortalityCharge;

					System.out.println("************** MC without gst *************** " + mortalityCharge);

					double gstCharge = (mortalityCharge * (mortFlagMaster.getGstRate() / 100));

					System.out.println("************** Gst Charge *************** " + gstCharge);

					double mortChargeWithGst = (mortalityCharge + gstCharge);

					System.out.println("************** MC with gst *************** " + mortChargeWithGst);
					val = val + mortChargeWithGst;

				} else {
					val = -1;
					break;
				}

			}

		}

		BigDecimal bd1 = new BigDecimal(val).setScale(2, RoundingMode.HALF_UP);

		return bd1.doubleValue();

	}

	public Double calculateUlipMortalityRates(Long policyNum) throws ParseException {
		TransactionPas trans = transactionPasRepository.findByFlcPolicyNo(policyNum);

		Integer age = policyDetailsPasRepository.getByAge(policyNum);

		List<CoverDetailsPas> covers = coverDetailsPasRepository.getAllByPolicyNo(policyNum);

		double val = 0;
		double monthMortAmount = 0.0;

		for (CoverDetailsPas coverPas : covers) {

			MortFlagMaster mortFlagMaster = mortFlagMasterRepository.getByUniqueNo(coverPas.getUinNumber());

			if (mortFlagMaster.getMortFlag().equalsIgnoreCase("Yes")) {

				Float mortalityRates = mortalityRatesRepository.findByCCCT(coverPas.getUinNumber(), age,
						coverPas.getPremCessTerm());

				System.out.println("***************** Mortality rate************** " + mortalityRates);

				if (mortalityRates != null) {

					String flcReqDate = trans.getFlcReqDate();

					System.out.println("****** ulip req date **********" + flcReqDate);

					String coverStartDate = coverPas.getRiskComDate();
					System.out.println("****** ulip Risk date **********" + coverStartDate);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

					Date d1 = sdf.parse(flcReqDate);

					Date d2 = sdf.parse(coverStartDate);

					long diffInTime = d1.getTime() - d2.getTime();

					long difference_In_Days = (diffInTime / (1000 * 60 * 60 * 24)) % 365;

					double differenceInDays;

					if (difference_In_Days < 0) {

						differenceInDays = (float) (difference_In_Days * -1);

					} else {
						differenceInDays = (float) (difference_In_Days);
					}

					double noOfMonths = Math.ceil(((differenceInDays) / 30f));

					double ulipRates = ((mortalityRates * coverPas.getSumAssured()) / 1000f);
					double diffInDaysplusOne = differenceInDays + 1;

					double ulipMortalityChargesRecoverd = (ulipRates / 365) * diffInDaysplusOne;

					System.out.println("********* Mortality Rates ********* " + mortalityRates);
					System.out.println("********* Sum Assured ********* " + coverPas.getSumAssured());
					System.out.println("********* diff In Days ********* " + diffInDaysplusOne);
					System.out.println("********* Ulip Rates ********* " + ulipRates);
					System.out
							.println("********* Mortality Charge Recovered ********* " + ulipMortalityChargesRecoverd);

					double MortAmountPerDay = ((mortalityRates * coverPas.getSumAssured()) / (365 * 1000f));

					String monthForFlcReqdate = getMonth(trans.getFlcReqDate());

					System.out.println("********* Flc Req Month ********* " + monthForFlcReqdate);

					if (monthForFlcReqdate.equalsIgnoreCase("january") || monthForFlcReqdate.equalsIgnoreCase("march")
							|| monthForFlcReqdate.equalsIgnoreCase("may") || monthForFlcReqdate.equalsIgnoreCase("july")
							|| monthForFlcReqdate.equalsIgnoreCase("august")
							|| monthForFlcReqdate.equalsIgnoreCase("october")
							|| monthForFlcReqdate.equalsIgnoreCase("december")) {
						System.out.println("******* inside month if ***********");
						monthMortAmount = MortAmountPerDay * 31;
					}

					if (monthForFlcReqdate.equalsIgnoreCase("april") || monthForFlcReqdate.equalsIgnoreCase("june")
							|| monthForFlcReqdate.equalsIgnoreCase("september")
							|| monthForFlcReqdate.equalsIgnoreCase("november")) {
						monthMortAmount = MortAmountPerDay * 30;
					}

					if (monthForFlcReqdate.equalsIgnoreCase("february")) {
						monthMortAmount = MortAmountPerDay * 28;
					}

					double mortAmountRefund = monthMortAmount - ulipMortalityChargesRecoverd;

					System.out.println("********* Per Day Mort Charges ********* " + MortAmountPerDay);
					System.out.println("********* Month Mort Charges ********* " + monthMortAmount);
					System.out.println("********* Amount Refund ********* " + mortAmountRefund);

					double gstCharge = (mortAmountRefund * (mortFlagMaster.getGstRate() / 100));

					System.out.println("********* Gst Charge ********* " + gstCharge);

					double mortChargeWithGst = (mortAmountRefund + gstCharge);

					System.out.println("********* Amount Refund With Gst ********* " + mortChargeWithGst);

					val = val + mortChargeWithGst;

				} else {
					val = -1;
					break;
				}

			}

		}

		BigDecimal bd1 = new BigDecimal(val).setScale(2, RoundingMode.HALF_UP);

		return bd1.doubleValue();

	}

	public String getMonth(String req) throws ParseException {

		SimpleDateFormat input = new SimpleDateFormat("yyyyMMdd");

		Date reqDate = input.parse(req);

		SimpleDateFormat month = new SimpleDateFormat("MMMM");

		String reqMon = month.format(reqDate);

		System.out.println("Month: " + reqMon);

		return reqMon;
	}

	public Double calculateStampDuty(Long policyNo) {
		double stamDuty = 0;
		List<CoverDetailsPas> covers = coverDetailsPasRepository.getAllByPolicyNo(policyNo);
		for (CoverDetailsPas cover : covers) {

			StamDutyMaster stamDutyMaster = stamDutyMasterRepository.getByUniqueNo(cover.getUinNumber());
			Long sum = cover.getSumAssured();
			double rate = stamDutyMaster.getSdRate();
			double stamp = sum * (rate / 1000);
			double stampWithoutGst = stamp * (stamDutyMaster.getGstRate() / 100);

			double dutyWithGst = (stamp + stampWithoutGst);

			System.out.println("***** SD rate ***** " + rate);
			System.out.println("***** Stamp ***** " + stamp);
			System.out.println("***** Stamp without gst ***** " + stampWithoutGst);
			System.out.println("***** Stamp with gst ***** " + dutyWithGst);

			stamDuty = stamDuty + dutyWithGst;
		}

		BigDecimal bd1 = new BigDecimal(stamDuty).setScale(2, RoundingMode.HALF_UP);
		return bd1.doubleValue();
	}

	public double calculateFundValue(Long tranno) {
		TransactionPas trans = transactionPasRepository.getInitiatedTransaction(tranno);
		List<FundDetailsPas> fdss = fundDetailsPasRepo.getallByPolicy(trans.getFlcPolicyNo());
		double fundval = 0f;
		for (FundDetailsPas fds : fdss) {
			System.out.println("******** Fund Code ***********" + fds.getFundCode());
			System.out.println("******** Nav Date ***********" + fds.getNavDate());
			PurpleNav nav = purpleNavRepo.getByFundCode(fds.getFundCode(), fds.getNavDate());
			double rate = 0f;
			rate = fds.getUnits() * nav.getRate();

			BigDecimal bd = new BigDecimal(rate).setScale(2, RoundingMode.HALF_UP);
			double value = bd.doubleValue();
			System.out.println("******** Rate ***********" + nav.getRate());
			System.out.println("******** Unit ***********" + fds.getUnits());
			System.out.println("******** Value ***********" + value);
			fundval = fundval + value;
			PurpleFundDetails pfds = new PurpleFundDetails();
			pfds.setValidFlag(1);
			pfds.setCompanyId(trans.getCompanyId());
			pfds.setPolicyNo(trans.getFlcPolicyNo());
			pfds.setPurpleFundCode(fds.getFundCode());
			pfds.setPurpleFundName(fds.getFundName());
			pfds.setPurpleNavDate(trans.getEffDate());
			pfds.setPurpleUnits(fds.getUnits());
			pfds.setPurpleRateApp(nav.getRate());
			pfds.setPurpleFundValue(value);
			purpleFundDetailsRepo.save(pfds);
			fundValueValidation(fds, pfds);
		}
		BigDecimal bd1 = new BigDecimal(fundval).setScale(2, RoundingMode.HALF_UP);
		System.out.println(
				"********Overall Value ***********" + new BigDecimal(fundval).setScale(2, RoundingMode.HALF_UP));
		return bd1.doubleValue();
	}

	public void fundValueValidation(FundDetailsPas fds, PurpleFundDetails pfds) {

		String flag = "";

		if (!fds.getNavDate().equals(pfds.getPurpleNavDate())) {
			flag = "Error";

		}

		if (!fds.getRateApp().equals(pfds.getPurpleRateApp())) {
			flag = "Error";

		}

		if (!fds.getValue().equals(pfds.getPurpleFundValue())) {
			flag = "Error";

		}

		if (flag != "Error") {
			flag = "Clear";
		}

		pfds.setStatus(flag);
		purpleFundDetailsRepo.save(pfds);
	}

	/*
	 * public String assignMultipleTrans(List<Long> policyNums, Long userId) {
	 * 
	 * // for (Long policyNo : policyNums) { // // add(policyNo,userId); // }
	 * 
	 * policyNums.forEach((policyNo) -> {
	 * 
	 * TransactionPas trans = transactionPasRepository.findByFlcPolicyNo(policyNo);
	 * 
	 * try { add(policyNo, userId); } catch (ParseException e) {
	 * e.printStackTrace(); } });
	 * 
	 * return errorService.getErrorById("ER001"); }
	 */

	public String assignMultipleTrans(List<Long> policyNums, Long userId) {

		policyNums.forEach((policyNo) -> {
			try {
				PolicyDetailsPas policy = policyDetailsPasRepository.getActiveByPolicyNo(policyNo);
				
				UinMaster uinMaster = uinMasterRepository.getActiveByUIN(policy.getUinNumber());

				if(uinMaster.getFlcEligibility().equalsIgnoreCase("Yes")) {
					if (uinMaster.getProductType().contains("N")) {
						addNonUlip(policyNo, userId);
					} else if (uinMaster.getProductType().contains("L")) {
						addUlip(policyNo, userId);
					}
				}
			

			} catch (ParseException e) {
				e.printStackTrace();
			}
		});

		return errorService.getErrorById("ER001");
	}

}
