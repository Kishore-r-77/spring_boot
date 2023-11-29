package com.futura.Purple.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.IPCAFundDetails;
import com.futura.Purple.Entity.IPCASurrender;
import com.futura.Purple.Entity.PurpleDetails;
import com.futura.Purple.Entity.PurpleNav;
import com.futura.Purple.Entity.SurrenderClientDetails;
import com.futura.Purple.Entity.SurrenderCoverDetails;
import com.futura.Purple.Entity.SurrenderFundDetailsPas;
import com.futura.Purple.Entity.SurrenderPolicyDetails;
import com.futura.Purple.Entity.SurrenderTransactionPas;
import com.futura.Purple.Entity.UinMaster;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.BonusRateRepository;
import com.futura.Purple.Repository.GSVCashValueRepository;
import com.futura.Purple.Repository.GsvFactorRepository;
import com.futura.Purple.Repository.IPCAFundDetailsRepository;
import com.futura.Purple.Repository.IPCASurrenderRepository;
import com.futura.Purple.Repository.PurpleNavRepository;
import com.futura.Purple.Repository.SsvFactorRepository;
import com.futura.Purple.Repository.SurrenderClientDetailsRepository;
import com.futura.Purple.Repository.SurrenderCoverDetailsRepository;
import com.futura.Purple.Repository.SurrenderFundDetailsPasRepository;
import com.futura.Purple.Repository.SurrenderPolicyDetailsRepository;
import com.futura.Purple.Repository.SurrenderTransactionPasRepository;
import com.futura.Purple.Repository.UinMasterRepository;

@Service
public class IPCASurrenderService {

	double cvbrate = 0f;
	double noOfDues = 0f;
	double gsvRate = 0f;
	double ssvRate = 0f;
	double paidUpVal = 0f;
	double IB = 0f;
	double RB = 0f;
	double ssvGross = 0f;
	double premGsv = 0f;

	@Autowired
	private IPCASurrenderRepository ipcaSurrenderRepository;

	@Autowired
	private SurrenderPolicyDetailsRepository policyDetailsRepository;

	@Autowired
	private SurrenderTransactionPasRepository surrenderTransactionPasRepository;

	@Autowired
	private SurrenderCoverDetailsRepository coverDetailsRepo;

	@Autowired
	private SsvFactorRepository ssvFactorRepository;

	@Autowired
	private GsvFactorRepository gsvFactorRepository;

	@Autowired
	private GSVCashValueRepository gsvCashValueRepository;

	@Autowired
	private UinMasterRepository uinMasterRepository;

	@Autowired
	private BonusRateRepository bonusRateRepository;

	@Autowired
	private SurrenderClientDetailsRepository clientDetailsRepository;

	@Autowired
	private SurrenderFundDetailsPasRepository fundDetailsPasRepo;

	@Autowired
	private IPCAFundDetailsRepository ipcaFundDetailsRepo;

	@Autowired
	private PurpleNavRepository purpleNavRepo;

	@Autowired
	private ErrorService errorService;

	public List<IPCASurrender> getAll() {
		return ipcaSurrenderRepository.getallActive();
	}

	public List<IPCASurrender> getAllQCPending() {
		return ipcaSurrenderRepository.getAllQCPending();
	}

	public IPCASurrender getById(Long id) {
		return ipcaSurrenderRepository.getActiveById(id);
	}

	public IPCASurrender getByPolicyNo(Long policyNo) {
		return ipcaSurrenderRepository.getByPolicyNo(policyNo);
	}

	public List<IPCASurrender> getAllFailed() {
		return ipcaSurrenderRepository.getAllFailedRecord();
	}

	public List<IPCASurrender> getAllPassed() {
		return ipcaSurrenderRepository.getAllPassedRecord();
	}

	public String update(Long id, IPCASurrender ipca) {
		IPCASurrender ipca1 = ipcaSurrenderRepository.getActiveById(id);

		if (ipca.getCompanyId() != null) {
			ipca1.setCompanyId(ipca.getCompanyId());
		}
		if (ipca.getPolicyNo() != null) {
			ipca1.setPolicyNo(ipca.getPolicyNo());
		}
		if (ipca.getUinNumber() != null) {
			ipca1.setUinNumber(ipca.getUinNumber());
		}
		if (ipca.getReqDate() != null) {
			ipca1.setReqDate(ipca.getReqDate());
		}
		if (ipca.getLogDate() != null) {
			ipca1.setLogDate(ipca.getLogDate());
		}
		if (ipca.getNoOfDues() != null) {
			ipca1.setNoOfDues(ipca.getNoOfDues());
		}
		if (ipca.getTotalPremium() != null) {
			ipca1.setTotalPremium(ipca.getTotalPremium());
		}
		if (ipca.getValueOfbonus() != null) {
			ipca1.setValueOfbonus(ipca.getValueOfbonus());
		}
		if (ipca.getCvbFactor() != null) {
			ipca1.setCvbFactor(ipca.getCvbFactor());
		}
		if (ipca.getGsvFactor() != null) {
			ipca1.setGsvFactor(ipca.getGsvFactor());
		}
		if (ipca.getGsvGross() != null) {
			ipca1.setGsvGross(ipca.getGsvGross());
		}
		if (ipca.getSbPaid() != null) {
			ipca1.setSbPaid(ipca.getSbPaid());
		}
		if (ipca.getGsvNet() != null) {
			ipca1.setGsvNet(ipca.getGsvNet());
		}
		if (ipca.getPaidUpValue() != null) {
			ipca1.setPaidUpValue(ipca.getPaidUpValue());
		}
		if (ipca.getReversionaryBonus() != null) {
			ipca1.setReversionaryBonus(ipca.getReversionaryBonus());
		}
		if (ipca.getCashValueBonus() != null) {
			ipca1.setCashValueBonus(ipca.getCashValueBonus());
		}
		if (ipca.getTerminalBonus() != null) {
			ipca1.setTerminalBonus(ipca.getTerminalBonus());
		}
		if (ipca.getSsvGrossAmount() != null) {
			ipca1.setSsvGrossAmount(ipca.getSsvGrossAmount());
		}
		if (ipca.getSsvFactor() != null) {
			ipca1.setSsvFactor(ipca.getSsvFactor());
		}
		if (ipca.getSsvNet() != null) {
			ipca1.setSsvNet(ipca.getSsvNet());
		}
		if (ipca.getSsvOrGsv() != null) {
			ipca1.setSsvOrGsv(ipca.getSsvOrGsv());
		}
		if (ipca.getFundValue() != null) {
			ipca1.setFundValue(ipca.getFundValue());
		}
		if (ipca.getEffDate() != null) {
			ipca1.setEffDate(ipca.getEffDate());
		}
		if (ipca.getPolicyDeposite() != null) {
			ipca1.setPolicyDeposite(ipca.getPolicyDeposite());
		}
		if (ipca.getPenalIntrest() != null) {
			ipca1.setPenalIntrest(ipca.getPenalIntrest());
		}
		if (ipca.getGrossPay() != null) {
			ipca1.setGrossPay(ipca.getGrossPay());
		}
		if (ipca.getCdaCharge() != null) {
			ipca1.setCdaCharge(ipca.getCdaCharge());
		}
		if (ipca.getUlipSurrenderCharges() != null) {
			ipca1.setUlipSurrenderCharges(ipca.getUlipSurrenderCharges());
		}
		if (ipca.getTds() != null) {
			ipca1.setTds(ipca.getTds());
		}
		if (ipca.getTotalRecovery() != null) {
			ipca1.setTotalRecovery(ipca.getTotalRecovery());
		}
		if (ipca.getNetPayable() != null) {
			ipca1.setNetPayable(ipca.getNetPayable());
		}
		if (ipca.getMakerFlag() != null) {
			ipca1.setMakerFlag(ipca.getMakerFlag());
		}
		if (ipca.getCheckerFlag() != null) {
			ipca1.setCheckerFlag(ipca.getCheckerFlag());
		}
		if (ipca.getPfFlag() != null) {
			ipca1.setPfFlag(ipca.getPfFlag());
		}
		if (ipca.getPfRemarks() != null) {
			ipca1.setPfRemarks(ipca.getPfRemarks());
		}

		ipcaSurrenderRepository.save(ipca1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		IPCASurrender ipca = ipcaSurrenderRepository.getActiveById(id);
		ipca.setValidFlag(-1);
		ipcaSurrenderRepository.save(ipca);
		return errorService.getErrorById("ER003");
	}

	public String reInitiatedTrans(Long policyNo) {

		SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(policyNo);
		IPCASurrender ipca = ipcaSurrenderRepository.getByPolicyNo(policyNo);
		UinMaster uin = uinMasterRepository.getActiveByUIN(policy.getUinNumber());
		SurrenderTransactionPas trans = surrenderTransactionPasRepository.getByPolicyNo(policyNo);

		if (uin.getProductType().contains("N")) {
			trans.setInterimStatus("Initiated");
			trans.setIpcaApprovalFlag(null);
			trans.setIpcaApprovalRemarks(null);
			trans.setIpcaApprovalDate(null);
			trans.setQcUserId(null);

			surrenderTransactionPasRepository.save(trans);
			ipca.setValidFlag(-1);
			ipcaSurrenderRepository.save(ipca);
		} else if (uin.getProductType().contains("L")) {

			trans.setInterimStatus("Initiated");
			trans.setIpcaApprovalFlag(null);
			trans.setIpcaApprovalRemarks(null);
			trans.setIpcaApprovalDate(null);
			trans.setQcUserId(null);

			List<IPCAFundDetails> ipcaFunds = ipcaFundDetailsRepo.getallByPolicyNo(policyNo);

			for (IPCAFundDetails ipcaFund : ipcaFunds) {
				ipcaFundDetailsRepo.delete(ipcaFund);
			}
			surrenderTransactionPasRepository.save(trans);
			ipca.setValidFlag(-1);
			ipcaSurrenderRepository.save(ipca);
		}

		return errorService.getErrorById("ER003");
	}

	public Double getBonusRate(String docDate, String planCode, String planName, String uinNumber) {
		return bonusRateRepository.getRateForDoc(docDate, planCode, planName, uinNumber);
	}

	public Double calculateBonusValue(Long policyNo) throws ParseException {
		Double bonusValue = 0.0;
		double newNoOfDues = 0;
		SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(policyNo);
		List<SurrenderCoverDetails> covers = coverDetailsRepo.getAllByPolicyNo(policyNo);

		String doc = policy.getDocDate();
		String fup = policy.getFup();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		Date Docdate = inputFormat.parse(doc);
		Date Fupdate = inputFormat.parse(fup);
		String docDate = sdf.format(Docdate);
		String fupDate = sdf.format(Fupdate);

		long difference_In_Time = Fupdate.getTime() - Docdate.getTime();

		double difference_In_Years = TimeUnit.MILLISECONDS.toDays(difference_In_Time) / 365f;

		BigDecimal years = new BigDecimal(difference_In_Years).setScale(2, RoundingMode.HALF_UP);

		long month = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(docDate)),
				YearMonth.from(LocalDate.parse(fupDate)));
		long noOfDues = month / Integer.parseInt(policy.getBillFreq());

		System.out.println("********  fup ********** " + fupDate);
		System.out.println("********  Doc ********** " + docDate);
		System.out.println("********  Months ********** " + month);
		System.out.println("********  Bill Freq ********** " + policy.getBillFreq());
		System.out.println("********  noOfDues ********** " + noOfDues);

		if (Integer.parseInt(policy.getBillFreq()) == 12) {
			newNoOfDues = noOfDues / 1;
		}

		if (Integer.parseInt(policy.getBillFreq()) == 6) {

			newNoOfDues = noOfDues / 2f;
			System.out.println("********  Inside If Bill Freq no of dues ********** " + newNoOfDues);
		}

		if (Integer.parseInt(policy.getBillFreq()) == 3) {

			newNoOfDues = noOfDues / 4f;
			System.out.println("********  Inside If Bill Freq no of dues ********** " + newNoOfDues);
		}

		if (Integer.parseInt(policy.getBillFreq()) == 1) {

			newNoOfDues = noOfDues / 12f;
			System.out.println("********  Inside If Bill Freq no of dues for 01 ********** " + newNoOfDues);
		}

		Double ceilYear = Math.floor(years.doubleValue());

		String nextDate = sdf.format(Docdate);

		for (SurrenderCoverDetails coverPas : covers) {

			String previous = "";
			String next = "";
			double i = 0;

			for (i = 1; i <= newNoOfDues; i++) {

				if (i > 1) {

					LocalDate nextDate1 = LocalDate.parse(previous, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
							.plusYears(1);

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					next = nextDate1.format(formatter);

					double bonus = bonusRateRepository.getRateForDoc(next, coverPas.getPlanCode(),
							coverPas.getPlanName(), coverPas.getUinNumber());

					if (bonus != 0) {

						double value = (coverPas.getSumAssured() * bonus) / 100;

						bonusValue += value;
						RB = bonusValue;

						System.out.println("******** Inside If ********* ");
						System.out.println("******** Date ********* " + next);
						System.out.println("******** Bonus ********* " + bonus);
						System.out.println("******** SA ********* " + coverPas.getSumAssured());
						System.out.println("******** Value ********* " + value);

						previous = next;
					} else {

						bonusValue = -1.0;

						previous = next;
					}

				} else {

					Double bonus = bonusRateRepository.getRateForDoc(docDate, coverPas.getPlanCode(),
							coverPas.getPlanName(), coverPas.getUinNumber());

					if (bonus != 0) {
						System.out.println("Err If 1: ");
						double value = (coverPas.getSumAssured() * bonus) / 100;

						System.out.println("******** Inside Else ********* ");
						System.out.println("******** Date ********* " + docDate);
						System.out.println("******** Bonus ********* " + bonus);
						System.out.println("******** SA ********* " + coverPas.getSumAssured());
						System.out.println("******** Value ********* " + value);
						bonusValue += value;
						previous = docDate;
					} else {

						bonusValue = -1.0;
						previous = docDate;
					}

				}

			}

			if (i > newNoOfDues) {

				System.out.println("******** Value of I ********* " + i);
				System.out.println("******** new No Of Dues ********* " + newNoOfDues);

				LocalDate nextDate1 = LocalDate.parse(previous, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusYears(1);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				next = nextDate1.format(formatter);
				double bonus = bonusRateRepository.getRateForDoc(next, coverPas.getPlanCode(), coverPas.getPlanName(),
						coverPas.getUinNumber());

				if (bonus != 0) {
					double fraction = newNoOfDues - (i - 1);
					double value = ((coverPas.getSumAssured() * bonus) / 100) * fraction;

					System.out.println("******** Inside If No od Dues ********* ");
					System.out.println("******** Date ********* " + next);
					System.out.println("******** Bonus ********* " + bonus);
					System.out.println("******** SA ********* " + coverPas.getSumAssured());
					System.out.println("******** Value ********* " + value);
					System.out.println("******** fraction ********* " + fraction);

					IB = value;
					bonusValue += value;
				} else {
					bonusValue = -1.0;
				}
			}
		}

		BigDecimal bd = new BigDecimal(bonusValue).setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public Double calculateTotalPremiumPaid(Long policyNo) throws ParseException {

		SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(policyNo);

		String doc = policy.getDocDate();
		String fup = policy.getFup();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		Date Docdate = inputFormat.parse(doc);
		Date Fupdate = inputFormat.parse(fup);
		String docDate = sdf.format(Docdate);
		String fupDate = sdf.format(Fupdate);

		long month = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(docDate)),
				YearMonth.from(LocalDate.parse(fupDate)));

		noOfDues = (month / Integer.parseInt(policy.getBillFreq()));
		Double tp = noOfDues * policy.getInstallmentPremium();

		return tp;
	}

	public double calculateGSV(Long policyNo) throws ParseException {

		Double Gsv = 0.0;
		SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(policyNo);
		SurrenderTransactionPas trans = surrenderTransactionPasRepository.findByPolicyNo(policyNo);
		List<SurrenderCoverDetails> covers = coverDetailsRepo.getAllByPolicyNo(policyNo);

		String doc = policy.getDocDate();
		String reqDate = trans.getSvReqDate();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		Date Docdate = inputFormat.parse(doc);
		Date svReqDate = inputFormat.parse(reqDate);

		System.out.println("****** Req Date *******  " + svReqDate);
		System.out.println("****** Doc Date *******  " + Docdate);

		long diffInTime = svReqDate.getTime() - Docdate.getTime();

		double diffInYears = TimeUnit.MILLISECONDS.toDays(diffInTime) / 365f;

		BigDecimal years = new BigDecimal(diffInYears).setScale(2, RoundingMode.HALF_UP);

		Double ceilYear = Math.ceil(years.doubleValue());

		System.out.println("******** Years ******** " + years);
		System.out.println("****** Ceil Year *******  " + ceilYear);

		for (SurrenderCoverDetails coverPas : covers) {

			UinMaster uin = uinMasterRepository.getActiveByUIN(coverPas.getUinNumber());

			System.out.println("****** Gsv Fact *******  " + uin.getGsvFactor());

			if (uin.getGsvFactor().equalsIgnoreCase("yes")) {

				Double rate = gsvFactorRepository.getRate(coverPas.getUinNumber(), ceilYear);

				if (rate != null) {
					gsvRate = rate;

					Double tpp = calculateTotalPremiumPaid(policyNo);
					System.out.println("****** Rate *******  " + rate);
					System.out.println("****** Tpp *******  " + tpp);
					Double gsv = (rate / 100) * tpp;

					premGsv = gsv;
					System.out.println("****** gsv *******  " + gsv);
					Gsv += gsv;
				} else {
					Gsv = -1.0;
				}
			}
		}

		return Math.ceil(Gsv);
	}

	public Double calculateSsv(Long policyNo) throws ParseException {
		Double Ssv = 0.0;

		SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(policyNo);

		List<SurrenderCoverDetails> covers = coverDetailsRepo.getAllByPolicyNo(policyNo);

		SurrenderTransactionPas trans = surrenderTransactionPasRepository.findByPolicyNo(policyNo);

		String doc = policy.getDocDate();
		String fup = policy.getFup();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		Date Docdate = inputFormat.parse(doc);
		Date Fupdate = inputFormat.parse(fup);
		String docDate = sdf.format(Docdate);
		String fupDate = sdf.format(Fupdate);

		long month = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(docDate)),
				YearMonth.from(LocalDate.parse(fupDate)));

		double noOfYearsPaid = (month / 12f);

//		BigDecimal noOfYearsPaidRound = new BigDecimal(noOfYearsPaid).setScale(2, RoundingMode.HALF_UP);

		System.out.println("********* Month FUP/DOC ********** " + month);
		System.out.println("********* noOfYearsPaid ********** " + noOfYearsPaid);

		String reqDate = trans.getSvReqDate();

		Date svReqDate = inputFormat.parse(reqDate);
		String svreqDate = sdf.format(svReqDate);

		long diffInTime = svReqDate.getTime() - Docdate.getTime();

		double diffInYears = TimeUnit.MILLISECONDS.toDays(diffInTime) / 365f;

		BigDecimal years = new BigDecimal(diffInYears).setScale(2, RoundingMode.HALF_UP);

		Double ceilYear = Math.ceil(years.doubleValue());

		long months = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(docDate)),
				YearMonth.from(LocalDate.parse(svreqDate)));

//		Long duration = (month / Integer.parseInt(policy.getBillFreq()));

		double duration = (month / 12);

		System.out.println("****** Req Date ********* " + svreqDate);
		System.out.println("****** Doc ********* " + docDate);
		System.out.println("****** months SR Date********* " + months);
		System.out.println("****** ceil Year ********* " + ceilYear);
		System.out.println("****** Duration ********* " + duration);

		for (SurrenderCoverDetails coverPas : covers) {
			UinMaster uinMaster = uinMasterRepository.getActiveByUIN(coverPas.getUinNumber());

			if (uinMaster.getSsvFactor().equalsIgnoreCase("yes")) {

				Double paidupValue = (noOfYearsPaid / coverPas.getPremiumTerm()) * coverPas.getSumAssured();
				System.out.println("********* Paid Up Value ********** " + paidupValue);
				BigDecimal paidUpValRound = new BigDecimal(paidupValue).setScale(2, RoundingMode.HALF_UP);
				paidUpVal = paidUpValRound.doubleValue();
				System.out.println("********* Rounded Paid Up Value ********** " + paidUpVal);
				Double bonusVal = calculateBonusValue(policyNo);
				Double premSsv = bonusVal + paidupValue + trans.getInterimBonus();

				ssvGross = premSsv;

				System.out.println("********* Duration ********** " + duration);
				Double rate = ssvFactorRepository.getRate(coverPas.getUinNumber(), ceilYear);

				if (rate != null) {
					ssvRate = rate;

					Double ssv = premSsv * (rate / 100);
					Ssv += ssv;
				} else {
					Ssv = -1.0;
				}
			}

		}

		return Math.ceil(Ssv);
	}

	public Double calculateGsvCashValue(Long policyNo) throws ParseException {

		SurrenderTransactionPas pas = surrenderTransactionPasRepository.findByPolicyNo(policyNo);
		String sur = pas.getSvReqDate();
		List<SurrenderCoverDetails> covers = coverDetailsRepo.getAllByPolicyNo(policyNo);
		Double cashValue = 0.0;

		for (SurrenderCoverDetails coverPas : covers) {
			UinMaster uin = uinMasterRepository.getActiveByUIN(coverPas.getUinNumber());
			if (uin.getGsvCashValue().equalsIgnoreCase("yes")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
				Date Docdate = inputFormat.parse(coverPas.getDocDate());
				Date SurrDate = inputFormat.parse(sur);
				String docDate = sdf.format(Docdate);

//				LocalDate nextDate1 = LocalDate.parse(docDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusYears(1);
//
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//				String next = nextDate1.format(formatter);
//				System.out.println("Next: " + next);
//				double bonus = bonusRateRepository.getRateForDoc(docDate, coverPas.getPlanCode(), coverPas.getPlanName(),
//						coverPas.getUinNumber());
//				System.out.println("Bonus: " + bonus);
//				double value = (coverPas.getSumAssured() * bonus) / 100;

				double value = calculateBonusValue(policyNo);
				System.out.println("Value: " + value);

				long difference_In_Time = SurrDate.getTime() - Docdate.getTime();
				double difference_In_Years = TimeUnit.MILLISECONDS.toDays(difference_In_Time) / 365f;
				BigDecimal years = new BigDecimal(difference_In_Years).setScale(2, RoundingMode.HALF_UP);
				Double ceilYear = Math.ceil(years.doubleValue());
				Double ymat = coverPas.getPolicyTerm() - ceilYear;
				System.out.println("Years to Maturity " + ymat);
				Double rate = gsvCashValueRepository.getRate(coverPas.getUinNumber(), ymat);
				System.out.println("Rate: " + rate);

				if (rate != null) {
					cvbrate = rate;
					Double cashVal = value * (rate / 100);
					System.out.println("Cash Value: " + cashVal);
					cashValue += cashVal;
				} else {
					cashValue = -1.0;
				}
			}

		}

		return cashValue;
	}

	public Double calculatePenalIntrst(SurrenderTransactionPas trans, Double netPayable) throws ParseException {

		System.out.println("******** Inside Penal Interest *************** ");
		System.out.println("******** Inside Penal Interest Approv Date *************** " + trans.getApprovDate());
		System.out.println("******** Inside Penal Interest Req Date *************** " + trans.getSvReqDate());
		String approv = trans.getApprovDate();
		String req = trans.getSvReqDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date approvDate = sdf.parse(approv);
		Date reqDate = sdf.parse(req);

		long timeDiff = Math.abs(approvDate.getTime() - reqDate.getTime());
		long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

		System.out.println("******** Days Diff *************** " + daysDiff);

		Double penalInt = 0.0;

		if (daysDiff > 15) {

			double val1 = (0.09 / 365);
			double val2 = val1 * daysDiff;

			Double penal = (netPayable * val2);

			penalInt = penal;
			System.out.println("******** Outside Penal Interest 1 *************** ");

		} else if (daysDiff <= 15) {
			Double penal = 0.0;
			penalInt = penal;
			System.out.println("******** Outside Penal Interest 2 *************** ");
		}

		return penalInt;
	}

	public double calculateFundValue(Long tranno) {
		SurrenderTransactionPas trans = surrenderTransactionPasRepository.getInitiatedTransaction(tranno);
		List<SurrenderFundDetailsPas> fdss = fundDetailsPasRepo.getallByPolicy(trans.getPolicyNo());
		double fundval = 0f;
		for (SurrenderFundDetailsPas fds : fdss) {
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
			IPCAFundDetails pfds = new IPCAFundDetails();
			pfds.setValidFlag(1);
			pfds.setCompanyId(trans.getCompanyId());
			pfds.setPolicyNo(trans.getPolicyNo());
			pfds.setPurpleFundCode(fds.getFundCode());
			pfds.setPurpleFundName(fds.getFundName());
			pfds.setPurpleNavDate(trans.getEffectiveDate());
			pfds.setPurpleUnits(fds.getUnits());
			pfds.setPurpleRateApp(nav.getRate());
			pfds.setPurpleFundValue(value);
			ipcaFundDetailsRepo.save(pfds);
			SurrenderfundValueValidation(fds, pfds);
		}
		BigDecimal bd1 = new BigDecimal(fundval).setScale(2, RoundingMode.HALF_UP);
		System.out.println(
				"********Overall Value ***********" + new BigDecimal(fundval).setScale(2, RoundingMode.HALF_UP));
		return bd1.doubleValue();
	}

	public void SurrenderfundValueValidation(SurrenderFundDetailsPas fds, IPCAFundDetails pfds) {

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
		ipcaFundDetailsRepo.save(pfds);
	}

	public double calculateSurrenderCharge(double fundValue, Long policyNo) {
		SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(policyNo);

		UinMaster uinMaster = uinMasterRepository.getActiveByUIN(policy.getUinNumber());

		double surrenderCharge = fundValue * (uinMaster.getSurrenderChargeRate() / 100);

		return surrenderCharge;

	}

	public String addNonUlipSurrender(Long policyNo, Long userId) throws ParseException {
		long val = 0;
		String msg = "";
		String flag = "";
		double sbpaid = 0f;
		double netpay = 0f;

		SurrenderTransactionPas trans = surrenderTransactionPasRepository.findByPolicyNo(policyNo);
		SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(policyNo);

		double totalPrem = calculateTotalPremiumPaid(policyNo);
		double totalBonusValue = calculateBonusValue(policyNo);
		double gsvcashValue = calculateGsvCashValue(policyNo);
		double gsv = calculateGSV(policyNo);
		double ssv = calculateSsv(policyNo);

		if (totalBonusValue != -1 && gsvcashValue != -1 && gsv != -1 && ssv != -1) {
			IPCASurrender ipca = new IPCASurrender();

			ipca.setValidFlag(1);
			ipca.setCreatedBy(userId);
			ipca.setModifiedBy(userId);
			ipca.setCompanyId(trans.getCompanyId());
			ipca.setPolicyNo(policyNo);
			ipca.setTransNo(trans.getTransNo());
			ipca.setUinNumber(trans.getUinNumber());
			ipca.setLogDate(trans.getLogDate());
			ipca.setReqDate(trans.getSvReqDate());
			ipca.setNoOfDues(noOfDues);
			ipca.setTotalPremium(totalPrem);
			ipca.setValueOfbonus(totalBonusValue);
			ipca.setCvbFactor(cvbrate);
			ipca.setGsvFactor(gsvRate);

			ipca.setGsv(premGsv);
			double grossGsv = gsv + gsvcashValue;

			ipca.setGsvGross(grossGsv);
			ipca.setSbPaid(0.0);
			ipcaSurrenderRepository.save(ipca);

			double gsvNet = grossGsv - ipca.getSbPaid();
			ipca.setGsvNet(gsvNet);

//			ipca.setGsvNet(grossGsv);
			ipca.setPaidUpValue(paidUpVal);
			ipca.setInterimBonus(IB);
			ipca.setReversionaryBonus(RB);
			ipca.setCashValueBonus(gsvcashValue);
			ipca.setTerminalBonus(0.0);

//			double grossSsv = paidUpVal + totalBonusValue + trans.getInterimBonus();

			ipca.setSsvGrossAmount(ssvGross);
			ipca.setSsvFactor(ssvRate);

			ipca.setSsvNet(ssv);

			if (grossGsv > ssv) {
				ipca.setSsvOrGsv("GSV Payable");
				netpay = grossGsv;
			} else {
				ipca.setSsvOrGsv("SSV Payable");
				netpay = ssv;
			}

			ipca.setPolicyDeposite(trans.getPolicyDeposit());
			Double penalInterest = calculatePenalIntrst(trans, netpay);
			ipca.setPenalIntrest(penalInterest);
			Double grossPay = netpay + trans.getPolicyDeposit() + penalInterest;
			ipca.setGrossPay(grossPay);
			ipca.setCdaCharge(trans.getCdaCharges());
			ipca.setUlipSurrenderCharges(0.0);
			ipca.setTds(trans.getTds());

			ipca.setOtherRecovery(trans.getOtherRecovery());
			ipcaSurrenderRepository.save(ipca);

			Double tr = trans.getCdaCharges() + ipca.getOtherRecovery() + trans.getTds();
			ipca.setTotalRecovery(tr);

			Double totalNetPay = grossPay - tr;
			ipca.setNetPayable(totalNetPay);
			ipca.setPolicyLoan(trans.getPolicyLoan());
			ipca.setLoanInterest(trans.getLoanInterest());
			ipca.setMakerFlag(trans.getMakerFlag());
			ipca.setCheckerFlag(trans.getCheckerFlag());
			ipcaSurrenderRepository.save(ipca);

			if (!trans.getCashValueBonus().equals(ipca.getCashValueBonus())) {
				flag = "Fail";
				String msg1 = "Bonus Value";
				msg = msg1;
			}

			if (!trans.getGsvPayble().equals(ipca.getGsvNet())) {
				flag = "Fail";
				String msg1 = "GSV";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getPaidUpValue().equals(ipca.getPaidUpValue())) {
				flag = "Fail";
				String msg1 = "Paid Up Value";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getReversionaryBonus().equals(ipca.getReversionaryBonus())) {
				flag = "Fail";
				String msg1 = "Reversionary Bonus";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getInterimBonus().equals(ipca.getInterimBonus())) {
				flag = "Fail";
				String msg1 = "Interim Bonus";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getSsv().equals(ipca.getSsvNet())) {
				flag = "Fail";
				String msg1 = "SSV";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getTds().equals(ipca.getTds())) {
				flag = "Fail";
				String msg1 = "TDS";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getPolicyLoan().equals(ipca.getPolicyLoan())) {
				flag = "Fail";
				String msg1 = "Policy Loan";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getLoanInterest().equals(ipca.getLoanInterest())) {
				flag = "Fail";
				String msg1 = "Loan Interest";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getPolicyDeposit().equals(ipca.getPolicyDeposite())) {
				flag = "Fail";
				String msg1 = "Policy Deposit";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getPenalInterest().equals(ipca.getPenalIntrest())) {
				flag = "Fail";
				String msg1 = "Penal Interest";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getOtherRecovery().equals(ipca.getOtherRecovery())) {
				flag = "Fail";
				String msg1 = "Other Recovery";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getTotalRecovery().equals(ipca.getTotalRecovery())) {
				flag = "Fail";
				String msg1 = "Total Recovery";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getGrossPay().equals(ipca.getGrossPay())) {
				flag = "Fail";
				String msg1 = "Gross Pay";
				if (msg.equals("")) {
					msg = msg1;
				} else {
					msg = msg + "," + msg1;
				}
			}

			if (!trans.getNetPayable().equals(ipca.getNetPayable())) {
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

			ipca.setPfFlag(flag);
			ipca.setPfRemarks(msg);
			ipcaSurrenderRepository.save(ipca);
			trans.setInterimStatus("Processed");
			surrenderTransactionPasRepository.save(trans);

		}

		else {
			double value = 0.0;
			IPCASurrender ipca = new IPCASurrender();

			ipca.setValidFlag(1);
			ipca.setCreatedBy(userId);
			ipca.setModifiedBy(userId);
			ipca.setCompanyId(trans.getCompanyId());
			ipca.setPolicyNo(policyNo);
			ipca.setTransNo(trans.getTransNo());
			ipca.setUinNumber(trans.getUinNumber());
			ipca.setLogDate(trans.getLogDate());
			ipca.setReqDate(trans.getSvReqDate());
			ipca.setNoOfDues(noOfDues);
			ipca.setTotalPremium(totalPrem);
			ipca.setValueOfbonus(value);
			ipca.setCvbFactor(cvbrate);
			ipca.setGsvFactor(gsvRate);
			ipca.setGsvGross(value);
			ipca.setSbPaid(value);

//			double gsvNet = gsv - sbpaid;
//			ipca.setGsvNet(gsvNet);

			ipca.setGsvNet(value);
			ipca.setPaidUpValue(paidUpVal);
			ipca.setReversionaryBonus(value);
			ipca.setInterimBonus(value);
			ipca.setCashValueBonus(value);
			ipca.setTerminalBonus(value);
			ipca.setSsvGrossAmount(value);
			ipca.setSsvFactor(ssvRate);
			ipca.setSsvNet(value);
			ipca.setSsvOrGsv("Invalid");
			ipca.setPolicyDeposite(trans.getPolicyDeposit());
			ipca.setPenalIntrest(value);
			ipca.setGrossPay(value);
			ipca.setCdaCharge(value);
			ipca.setUlipSurrenderCharges(null);
			ipca.setTds(value);
			ipca.setOtherRecovery(value);
			ipca.setTotalRecovery(value);
			ipca.setNetPayable(value);
			ipca.setPolicyLoan(value);
			ipca.setLoanInterest(value);
			ipca.setMakerFlag(trans.getMakerFlag());
			ipca.setCheckerFlag(trans.getCheckerFlag());
			ipca.setPfFlag("Fail");

			if (totalBonusValue != -1 && gsvcashValue != -1 && gsv != -1 && ssv == -1) {
				ipca.setPfRemarks("Values are missing in SSV");
			} else if (totalBonusValue != -1 && gsvcashValue != -1 && gsv == -1 && ssv != -1) {
				ipca.setPfRemarks("Values are missing in GSV");
			} else if (totalBonusValue != -1 && gsvcashValue != -1 && gsv == -1 && ssv == -1) {
				ipca.setPfRemarks("Values are missing in SSV and GSV");
			} else if (totalBonusValue != -1 && gsvcashValue == -1 && gsv != -1 && ssv != -1) {
				ipca.setPfRemarks("Values are missing in Cash Value");
			} else if (totalBonusValue != -1 && gsvcashValue == -1 && gsv != -1 && ssv == -1) {
				ipca.setPfRemarks("Values are missing in Cash Value and SSV");
			} else if (totalBonusValue != -1 && gsvcashValue == -1 && gsv == -1 && ssv != -1) {
				ipca.setPfRemarks("Values are missing in Cash Value and GSV");
			} else if (totalBonusValue != -1 && gsvcashValue == -1 && gsv == -1 && ssv == -1) {
				ipca.setPfRemarks("Values are missing in Cash Value, GSV and SSV");
			} else if (totalBonusValue == -1 && gsvcashValue != -1 && gsv != -1 && ssv != -1) {
				ipca.setPfRemarks("Values are missing in Bonus Value");
			} else if (totalBonusValue == -1 && gsvcashValue != -1 && gsv != -1 && ssv == -1) {
				ipca.setPfRemarks("Values are missing in Bonus Value and SSV");
			} else if (totalBonusValue == -1 && gsvcashValue != -1 && gsv == -1 && ssv != -1) {
				ipca.setPfRemarks("Values are missing in Bonus Value and GSV");
			} else if (totalBonusValue == -1 && gsvcashValue != -1 && gsv == -1 && ssv == -1) {
				ipca.setPfRemarks("Values are missing in Bonus Value, GSV and SSV");
			} else if (totalBonusValue == -1 && gsvcashValue == -1 && gsv != -1 && ssv != -1) {
				ipca.setPfRemarks("Values are missing in Bonus Value and Cash Value");
			} else if (totalBonusValue == -1 && gsvcashValue == -1 && gsv != -1 && ssv == -1) {
				ipca.setPfRemarks("Values are missing in Bonus Value, Cash Value and SSV");
			} else if (totalBonusValue == -1 && gsvcashValue == -1 && gsv == -1 && ssv != -1) {
				ipca.setPfRemarks("Values are missing in Bonus Value, Cash Value and GSV");
			} else if (totalBonusValue == -1 && gsvcashValue == -1 && gsv == -1 && ssv == -1) {
				ipca.setPfRemarks("Values are missing in Bonus Value, Cash Value, SSV and GSV");
			}

			ipcaSurrenderRepository.save(ipca);
			trans.setInterimStatus("Processed");
			surrenderTransactionPasRepository.save(trans);

		}

		return errorService.getErrorById("");
	}

	public String addUlipSurrender(Long policyNo, Long userId) throws ParseException {
		double val = 0;
		String msg = "";
		String flag = "";
		double sbpaid = 0f;
		double netpay = 0f;

		SurrenderTransactionPas trans = surrenderTransactionPasRepository.findByPolicyNo(policyNo);
		SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(policyNo);

		double totalPrem = calculateTotalPremiumPaid(policyNo);

		Double fundValue = calculateFundValue(trans.getTransNo());

		IPCASurrender ipca = new IPCASurrender();

		ipca.setValidFlag(1);
		ipca.setCreatedBy(userId);
		ipca.setModifiedBy(userId);
		ipca.setCompanyId(trans.getCompanyId());
		ipca.setPolicyNo(policyNo);
		ipca.setTransNo(trans.getTransNo());
		ipca.setUinNumber(trans.getUinNumber());
		ipca.setLogDate(trans.getLogDate());
		ipca.setReqDate(trans.getSvReqDate());
		ipca.setNoOfDues(val);
		ipca.setTotalPremium(totalPrem);
		ipca.setValueOfbonus(val);
		ipca.setCvbFactor(val);
		ipca.setGsvFactor(val);
		ipca.setGsvGross(val);
		ipca.setSbPaid(0.0);

//			double gsvNet = gsv - sbpaid;
//			ipca.setGsvNet(gsvNet);

		ipca.setGsvNet(val);
		ipca.setPaidUpValue(val);
		ipca.setInterimBonus(val);
		ipca.setReversionaryBonus(val);
		ipca.setCashValueBonus(0.0);
		ipca.setTerminalBonus(0.0);
		ipca.setSsvGrossAmount(val);
		ipca.setSsvFactor(val);
		ipca.setSsvNet(val);

//			if (gsv > ssv) {
//				ipca.setSsvOrGsv("GSV Payable");
//				netpay = gsv;
//			} else {
//				ipca.setSsvOrGsv("SSV Payable");
//				netpay = ssv;
//			}

		ipca.setPolicyDeposite(trans.getPolicyDeposit());

		Double grossPay = fundValue + trans.getPolicyDeposit() + val;
		ipca.setGrossPay(grossPay);
		ipca.setCdaCharge(trans.getCdaCharges());

		double surrenderCharge = calculateSurrenderCharge(fundValue, policyNo);
		ipca.setUlipSurrenderCharges(surrenderCharge);

		ipca.setTds(trans.getTds());
		ipca.setOtherRecovery(trans.getOtherRecovery());
		ipcaSurrenderRepository.save(ipca);
		Double tr = trans.getCdaCharges() + surrenderCharge + trans.getTds() + ipca.getOtherRecovery();
		ipca.setTotalRecovery(tr);

		Double netPay = grossPay - tr;
		ipca.setNetPayable(netPay);
		ipcaSurrenderRepository.save(ipca);

		Double penalInterest = calculatePenalIntrst(trans, netPay);
		ipca.setPenalIntrest(penalInterest);

		Double newGrossPay = fundValue + trans.getPolicyDeposit() + penalInterest;
		ipca.setGrossPay(newGrossPay);

		Double newNetPay = newGrossPay - tr;
		ipca.setNetPayable(newNetPay);

		ipca.setEffDate(trans.getEffectiveDate());
		ipca.setFundValue(fundValue);

		ipca.setPolicyLoan(trans.getPolicyLoan());
		ipca.setLoanInterest(trans.getLoanInterest());
		ipca.setMakerFlag(trans.getMakerFlag());
		ipca.setCheckerFlag(trans.getCheckerFlag());
		ipcaSurrenderRepository.save(ipca);

		if (!trans.getFundValue().equals(ipca.getFundValue())) {
			flag = "Fail";
			String msg1 = "Fund Value";
			msg = msg1;
		}

		if (!trans.getPenalInterest().equals(ipca.getPenalIntrest())) {
			flag = "Fail";
			String msg1 = "Penal Interest";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		}
		if (!trans.getOtherRecovery().equals(ipca.getOtherRecovery())) {
			flag = "Fail";
			String msg1 = "Other Recovery";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		}
		if (!trans.getSurrenderCharge().equals(ipca.getUlipSurrenderCharges())) {
			flag = "Fail";
			String msg1 = "Surrender Charge";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		}

		if (!trans.getTotalRecovery().equals(ipca.getTotalRecovery())) {
			flag = "Fail";
			String msg1 = "Total Recovery";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		}

		if (!trans.getGrossPay().equals(ipca.getGrossPay())) {
			flag = "Fail";
			String msg1 = "Gross Payable";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		}

		if (!trans.getNetPayable().equals(ipca.getNetPayable())) {
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

		ipca.setPfFlag(flag);
		ipca.setPfRemarks(msg);
		ipcaSurrenderRepository.save(ipca);
		trans.setInterimStatus("Processed");
		surrenderTransactionPasRepository.save(trans);

		return errorService.getErrorById("");
	}

	public String qcUpdate(IPCASurrender ipca, Long policyNo, Long userId) {

		SurrenderTransactionPas transactions = surrenderTransactionPasRepository.findProcessedByPolicyNo(policyNo);
		IPCASurrender ipca1 = ipcaSurrenderRepository.getByTransNo(transactions.getTransNo());

		ipca1.setPurpleApprovalRemark(ipca.getPurpleApprovalRemark());
		ipca1.setPurpleApprovalFlag(ipca.getPurpleApprovalFlag());
		ipca1.setApprovalQcUserId(userId);
		ipcaSurrenderRepository.save(ipca1);
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String approvalDate = dateFormat.format(date);

		ipca1.setPurpleApprovalDate(approvalDate);

		ipcaSurrenderRepository.save(ipca1);
//		System.out.println("*********** Transaction Approv Flag ***********" + purpleDetails.getApprovFlag());
		transactions.setIpcaApprovalFlag(ipca1.getPurpleApprovalFlag());
		transactions.setIpcaApprovalRemarks(ipca1.getPurpleApprovalRemark());
		transactions.setIpcaApprovalDate(ipca1.getPurpleApprovalDate());
		transactions.setQcUserId(userId);
		transactions.setInterimStatus("QC Completed");
		surrenderTransactionPasRepository.save(transactions);

//		if (transactions.getIpcaApprovalFlag().equals(ipca1.getPurpleApprovalFlag())) {
//			pd.setPfFlagUpdate("Transaction Updated");
//		}
//		purpledetailsRepo.save(pd);

		if (transactions.getIpcaApprovalFlag().equals("Fail")) {

			System.out.println("Inside If Statement ");

			SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(transactions.getPolicyNo());
			List<SurrenderCoverDetails> covers = coverDetailsRepo.getAllByPolicyNo(policy.getChdrNum());

			System.out.println("Client Number " + policy.getClntNum());
			SurrenderClientDetails client = clientDetailsRepository.getActiveByClientNo(policy.getClntNum());

			System.out.println("Client Number " + client.getClntNum());

			policy.setValidFlag(-1);
			policyDetailsRepository.save(policy);

			for (SurrenderCoverDetails cover : covers) {
				cover.setValidFlag(-1);
				coverDetailsRepo.save(cover);
			}
			client.setValidFlag(-1);
			clientDetailsRepository.save(client);

		}
		return errorService.getErrorById("ER003");
	}

	public String assignMultipleTrans(List<Long> policyNums, Long userId) {

		policyNums.forEach((policyNo) -> {
			try {
				SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(policyNo);

				if (policy.getUinNumber().contains("N")) {
					addNonUlipSurrender(policyNo, userId);
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
		});

		return errorService.getErrorById("ER001");
	}

	public String assignSingleTrans(Long policyNo, Long userId) {

		try {
			SurrenderPolicyDetails policy = policyDetailsRepository.getActiveByPolicyNo(policyNo);

			UinMaster uinMaster = uinMasterRepository.getActiveByUIN(policy.getUinNumber());

			if (uinMaster.getProductType().contains("N")) {
				addNonUlipSurrender(policyNo, userId);
			} else if (uinMaster.getProductType().contains("L")) {
				addUlipSurrender(policyNo, userId);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return errorService.getErrorById("ER001");
	}

}
