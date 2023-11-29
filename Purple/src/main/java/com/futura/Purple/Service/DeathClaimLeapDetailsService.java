package com.futura.Purple.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.DeathClaimClientDetails;
import com.futura.Purple.Entity.DeathClaimCoverDetails;
import com.futura.Purple.Entity.DeathClaimFundDetailsPas;
import com.futura.Purple.Entity.DeathClaimLeapDetails;
import com.futura.Purple.Entity.DeathClaimLeapFundDetails;
import com.futura.Purple.Entity.DeathClaimPolicyDetails;
import com.futura.Purple.Entity.DeathClaimTransactionPas;
import com.futura.Purple.Entity.InterestPremiumRate;
import com.futura.Purple.Entity.SurvivalBenefitRate;
import com.futura.Purple.Entity.UinMaster;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.AnnuityRatesRepository;
import com.futura.Purple.Repository.BonusRateRepository;
import com.futura.Purple.Repository.DeathClaimClientDetailsRepository;
import com.futura.Purple.Repository.DeathClaimCoverDetailsRepository;
import com.futura.Purple.Repository.DeathClaimFundDetailsPasRepository;
import com.futura.Purple.Repository.DeathClaimLeapDetailsRepository;
import com.futura.Purple.Repository.DeathClaimLeapFundDetailsRepository;
import com.futura.Purple.Repository.DeathClaimParamRepository;
import com.futura.Purple.Repository.DeathClaimPolicyDetailsRepository;
import com.futura.Purple.Repository.DeathClaimTransactionPasRepository;
import com.futura.Purple.Repository.GstRatesRepository;
import com.futura.Purple.Repository.GuaranteedBonusRateRepository;
import com.futura.Purple.Repository.InterestPremiumRateRepository;
import com.futura.Purple.Repository.InterimBonusRateRepository;
import com.futura.Purple.Repository.LoyaltyAdditionRateRepository;
import com.futura.Purple.Repository.MortalityRatesRepository;
import com.futura.Purple.Repository.PurpleNavRepository;
import com.futura.Purple.Repository.SurrenderFundDetailsPasRepository;
import com.futura.Purple.Repository.SurvivalBenefitRateRepository;
import com.futura.Purple.Repository.TerminalBonusRateRepository;
import com.futura.Purple.Repository.UinMasterRepository;

@Service
public class DeathClaimLeapDetailsService {

	@Autowired
	private DeathClaimLeapDetailsRepository deathClaimLeapDetailsRepo;

	@Autowired
	private DeathClaimCoverDetailsRepository dccdRepo;

	@Autowired
	private DeathClaimPolicyDetailsRepository dcpdRepo;

	@Autowired
	private DeathClaimTransactionPasRepository dctpRepo;

	@Autowired
	private BonusRateRepository bonusRateRepo;

	@Autowired
	private DeathClaimParamRepository deathClaimParamRepo;

	@Autowired
	private InterimBonusRateRepository interimBonusRepo;

	@Autowired
	private UinMasterRepository uinMasterRepository;

	@Autowired
	private GuaranteedBonusRateRepository guaranteedBonusRateRepository;

	@Autowired
	private LoyaltyAdditionRateRepository loyaltyAdditionRateRepo;

	@Autowired
	private TerminalBonusRateRepository termBonusRateRepo;

	@Autowired
	private InterestPremiumRateRepository interestPremiumRateRepo;

	@Autowired
	private GstRatesRepository gstRateRepo;

	@Autowired
	private SurrenderFundDetailsPasRepository surrenderFundDetailsPasRepo;

	@Autowired
	private PurpleNavRepository navRepo;

	@Autowired
	private SurvivalBenefitRateRepository survivalBenefitRateRepo;

	@Autowired
	private AnnuityRatesRepository annuityRatesRepo;

	@Autowired
	private DeathClaimFundDetailsPasRepository dcfdRepo;

	@Autowired
	private MortalityRatesRepository mortRateRepo;

	@Autowired
	private DeathClaimClientDetailsRepository deathClaimClientRepo;

	@Autowired
	private DeathClaimLeapFundDetailsRepository dclfdRepo;

	@Autowired
	private ErrorService errorService;

	public List<DeathClaimLeapDetails> getAll() {
		return deathClaimLeapDetailsRepo.getallActive();
	}

	public List<DeathClaimLeapDetails> getAllQCPending() {
		return deathClaimLeapDetailsRepo.getAllQCPending();
	}

	public DeathClaimLeapDetails getById(Long id) {
		return deathClaimLeapDetailsRepo.getActiveById(id);
	}

	public DeathClaimLeapDetails getByUinNo(String uinNo) {
		return deathClaimLeapDetailsRepo.getByUinNo(uinNo);
	}

	public List<DeathClaimLeapDetails> getAllFailed() {
		return deathClaimLeapDetailsRepo.getAllFailedRecord();
	}

	public List<DeathClaimLeapDetails> getAllPassed() {
		return deathClaimLeapDetailsRepo.getAllPassedRecord();
	}

	public String update(Long id, DeathClaimLeapDetails deathclaim) {
		DeathClaimLeapDetails deathclaim1 = deathClaimLeapDetailsRepo.getActiveById(id);

		if (deathclaim.getCompanyId() != null) {
			deathclaim1.setCompanyId(deathclaim.getCompanyId());
		}
		if (deathclaim.getPolicyNo() != null) {
			deathclaim1.setPolicyNo(deathclaim.getPolicyNo());
		}
		if (deathclaim.getUinNumber() != null) {
			deathclaim1.setUinNumber(deathclaim.getUinNumber());
		}
		if (deathclaim.getClientId() != null) {
			deathclaim1.setClientId(deathclaim.getClientId());
		}
		if (deathclaim.getContractType() != null) {
			deathclaim1.setContractType(deathclaim.getContractType());
		}
		if (deathclaim.getProductType() != null) {
			deathclaim1.setProductType(deathclaim.getProductType());
		}
		if (deathclaim.getPolicyStatus() != null) {
			deathclaim1.setPolicyStatus(deathclaim.getPolicyStatus());
		}
		if (deathclaim.getFup() != null) {
			deathclaim1.setFup(deathclaim.getFup());
		}
		if (deathclaim.getTotalPremium() != null) {
			deathclaim1.setTotalPremium(deathclaim.getTotalPremium());
		}
		if (deathclaim.getBasicSa() != null) {
			deathclaim1.setBasicSa(deathclaim.getBasicSa());
		}
		if (deathclaim.getAdditionalSa() != null) {
			deathclaim1.setAdditionalSa(deathclaim.getAdditionalSa());
		}
		if (deathclaim.getInbuiltRiderSa() != null) {
			deathclaim1.setInbuiltRiderSa(deathclaim.getInbuiltRiderSa());
		}
		if (deathclaim.getReversionaryBonus() != null) {
			deathclaim1.setReversionaryBonus(deathclaim.getReversionaryBonus());
		}
		if (deathclaim.getInterimBonus() != null) {
			deathclaim1.setInterimBonus(deathclaim.getInterimBonus());
		}
		if (deathclaim.getGuranteedAddition() != null) {
			deathclaim1.setGuranteedAddition(deathclaim.getGuranteedAddition());
		}
		if (deathclaim.getLoyaltyAddtion() != null) {
			deathclaim1.setLoyaltyAddtion(deathclaim.getLoyaltyAddtion());
		}
		if (deathclaim.getOtherRiderBenefit() != null) {
			deathclaim1.setOtherRiderBenefit(deathclaim.getOtherRiderBenefit());
		}
		if (deathclaim.getTerminalBonus() != null) {
			deathclaim1.setTerminalBonus(deathclaim.getTerminalBonus());
		}
		if (deathclaim.getTotalBonus() != null) {
			deathclaim1.setTotalBonus(deathclaim.getTotalBonus());
		}
		if (deathclaim.getAnnuityRefund() != null) {
			deathclaim1.setAnnuityRefund(deathclaim.getAnnuityRefund());
		}
		if (deathclaim.getFundValue() != null) {
			deathclaim1.setFundValue(deathclaim.getFundValue());
		}
		if (deathclaim.getTotalDeathClaim() != null) {
			deathclaim1.setTotalDeathClaim(deathclaim.getTotalDeathClaim());
		}
		if (deathclaim.getPolicyDeposit() != null) {
			deathclaim1.setPolicyDeposit(deathclaim.getPolicyDeposit());
		}
		if (deathclaim.getPenalInterest() != null) {
			deathclaim1.setPenalInterest(deathclaim.getPenalInterest());
		}
		if (deathclaim.getGrossPayable() != null) {
			deathclaim1.setGrossPayable(deathclaim.getGrossPayable());
		}
		if (deathclaim.getTermPremRecov() != null) {
			deathclaim1.setTermPremRecov(deathclaim.getTermPremRecov());
		}
		if (deathclaim.getInterestOnPremium() != null) {
			deathclaim1.setInterestOnPremium(deathclaim.getInterestOnPremium());
		}
		if (deathclaim.getGstOnPremium() != null) {
			deathclaim1.setGstOnPremium(deathclaim.getGstOnPremium());
		}
		if (deathclaim.getCdaCharges() != null) {
			deathclaim1.setCdaCharges(deathclaim.getCdaCharges());
		}
		if (deathclaim.getOtherCharges() != null) {
			deathclaim1.setOtherCharges(deathclaim.getOtherCharges());
		}
		if (deathclaim.getPolicyLoan() != null) {
			deathclaim1.setPolicyLoan(deathclaim.getPolicyLoan());
		}
		if (deathclaim.getPolicyLoanInterest() != null) {
			deathclaim1.setPolicyLoanInterest(deathclaim.getPolicyLoanInterest());
		}
		if (deathclaim.getMoneybackPaid() != null) {
			deathclaim1.setMoneybackPaid(deathclaim.getMoneybackPaid());
		}
		if (deathclaim.getAnnuityPaid() != null) {
			deathclaim1.setAnnuityPaid(deathclaim.getAnnuityPaid());
		}
		if (deathclaim.getMortChargesRefund() != null) {
			deathclaim1.setMortChargesRefund(deathclaim.getMortChargesRefund());
		}
		if (deathclaim.getAdminFeeRefund() != null) {
			deathclaim1.setAdminFeeRefund(deathclaim.getAdminFeeRefund());
		}
		if (deathclaim.getGuranteedAddCharges() != null) {
			deathclaim1.setGuranteedAddCharges(deathclaim.getGuranteedAddCharges());
		}
		if (deathclaim.getTds() != null) {
			deathclaim1.setTds(deathclaim.getTds());
		}
		if (deathclaim.getTotalRecovery() != null) {
			deathclaim1.setTotalRecovery(deathclaim.getTotalRecovery());
		}
		if (deathclaim.getNetPayable() != null) {
			deathclaim1.setNetPayable(deathclaim.getNetPayable());
		}

		deathClaimLeapDetailsRepo.save(deathclaim1);
		return errorService.getErrorById("ER003");
	}

	public String deactivate(Long id) {
		DeathClaimLeapDetails deathclaim = deathClaimLeapDetailsRepo.getActiveById(id);
		deathclaim.setValidFlag(-1);
		deathClaimLeapDetailsRepo.save(deathclaim);
		return errorService.getErrorById("ER003");
	}

	public Double getPremiumPaid(String docDate, String Fup, Integer billFreq, Double premium) throws ParseException {

		System.out.println("Calculation For Premium Paid");
		double noOfDues = getNoOfDues(docDate, Fup, billFreq);
		double premiumPaid = noOfDues * premium;
		System.out.println("doc " + docDate);
		System.out.println("fup " + Fup);
		System.out.println("No Of Dues " + noOfDues);
		System.out.println("Premium " + premium);
		System.out.println("Premium Paid " + premiumPaid);
		return premiumPaid;
	}

	public Double getPremiumRecovery(String dateOfDeath, String fup, Integer billFreq, Double premium)
			throws ParseException {

		System.out.println("Calculation For Premium Recovery");
		double noOfDues = getNoOfDues(fup, dateOfDeath, billFreq);
		double premiumRecovery = noOfDues * premium;
		System.out.println("date Of death " + dateOfDeath);
		System.out.println("fup " + fup);
		System.out.println("No Of Dues " + noOfDues);
		System.out.println("Premium " + premium);
		System.out.println("Premium Recovery " + premiumRecovery);
		return premiumRecovery;
	}

	public double getNoOfDues(String docDate, String Fup, Integer billFreq) throws ParseException {

		Long months = getDiffInMonth(docDate, Fup);

		Double noOfDues = (months.doubleValue() / billFreq);

		return noOfDues;
	}

	public double getYearlyNoOfDues(String docDate, String Fup, Integer billFreq) throws ParseException {

		Long months = getDiffInMonth(docDate, Fup);

		Double noOfDues = (months.doubleValue() / billFreq);
		Double noOfDues1 = 0.0;

		switch (billFreq) {
		case 12 -> noOfDues1 = noOfDues / 1;
		case 6 -> noOfDues1 = noOfDues / 2f;
		case 3 -> noOfDues1 = noOfDues / 4f;
		case 1 -> noOfDues1 = noOfDues / 12f;
		}

		noOfDues1 = getRoundedDoubleValueFifthDecimal(noOfDues1);

		return noOfDues1;
	}

	public Double getPaidUpValue(String docDate, String Fup, Double premiumTerm, Integer billFreq, Double sumAssured)
			throws ParseException {

		System.out.println(" Calculation For paid Up Value ");
		Double noOfInstallment1 = getYearlyNoOfDues(docDate, Fup, billFreq);
		Double noOfInstallment2 = premiumTerm;

		Double paidUpVal = (noOfInstallment1 / noOfInstallment2) * sumAssured;

		System.out.println("noOf Intallment 1 -: " + noOfInstallment1);
		System.out.println("noOf Intallment 2 -: " + noOfInstallment2);
		System.out.println("SA " + sumAssured);
		System.out.println("paid up val " + paidUpVal);

		Double paidUpVal1 = getRoundedDoubleValue(paidUpVal);

		return paidUpVal1;
	}

	public Double getRevRate(String startDate, String uinNunber, String planName, String planCode) {

		Double br = bonusRateRepo.getRateForDoc(startDate, planCode, planName, uinNunber);
		return br;
	}

	public Double getRevAmount(String fup, String doc, String planName, String planCode, String uinNumber,
			Double sumAssured, Double premiumTerm, Integer billFreq, String coverStatus, String revesionaryBonus)
			throws ParseException {

		System.out.println(" Calculation For Revesionary Amount ");

		Double revAmount = 0.0;
		Double finalRevAmount = 0.0;
		double i = 1;
		Double val = 0.0;

		Double paidUpVal = getPaidUpValue(doc, fup, premiumTerm, billFreq, sumAssured);

		if (coverStatus.equalsIgnoreCase("LA") || coverStatus.equalsIgnoreCase("PU")) {
			val = paidUpVal;
		} else if (coverStatus.equalsIgnoreCase("IF") || coverStatus.equalsIgnoreCase("SP")) {
			val = sumAssured;
		} else {
			val = 0.0;
		}

		Double noOfDues = getNoOfDues(doc, fup, billFreq);
		Double noOfDues1 = 0.0;

		switch (billFreq) {
		case 12 -> noOfDues1 = noOfDues / 1;
		case 6 -> noOfDues1 = noOfDues / 2f;
		case 3 -> noOfDues1 = noOfDues / 4f;
		case 1 -> noOfDues1 = noOfDues / 12f;
		}

		noOfDues1 = getRoundedDoubleValueFifthDecimal(noOfDues1);
		System.out.println("*******  noOfDues1 ******** " + noOfDues1);

		String stampDate = doc;

		if (revesionaryBonus.equalsIgnoreCase("yes")) {

			while (i <= noOfDues1) {

				double revRate = getRevRate(stampDate, uinNumber, planName, planCode);

				if (revRate != 0) {
					revAmount = val * (revRate / 100);

					Double revAmount1 = getRoundedDoubleValue(revAmount);
					finalRevAmount += revAmount1;

					System.out.println("*******  date ******** " + stampDate);
					System.out.println("******* Rev Amount ******* " + revAmount1);
					System.out.println("******* Final Rev Amount ******* " + finalRevAmount);
				} else {
					finalRevAmount = -1.0;
				}

				i++;
				stampDate = getNextDate(stampDate);
			}
		}

		return finalRevAmount;

	}

	public Double getInterimAmount(String fup, String doc, String planName, String planCode, String uinNumber,
			Double sumAssured, Double premiumTerm, Integer billFreq, String coverStatus, String interimBonus)
			throws ParseException {

		System.out.println(" Calculation For Interim Amount ");

		Double revAmount = 0.0;
		Double finalRevAmount = 0.0;
		double i = 1;
		Double interimAmount = 0.0;
		Double interimAmount1 = 0.0;
		Double val = 0.0;

		Double paidUpVal = getPaidUpValue(doc, fup, premiumTerm, billFreq, sumAssured);

		if (coverStatus.equalsIgnoreCase("LA") || coverStatus.equalsIgnoreCase("PU")) {
			val = paidUpVal;
		} else if (coverStatus.equalsIgnoreCase("IF") || coverStatus.equalsIgnoreCase("SP")) {
			val = sumAssured;
		} else {
			val = 0.0;
		}

		Double noOfDues = getNoOfDues(doc, fup, billFreq);
		Double noOfDues1 = 0.0;

		switch (billFreq) {
		case 12 -> noOfDues1 = noOfDues / 1;
		case 6 -> noOfDues1 = noOfDues / 2f;
		case 3 -> noOfDues1 = noOfDues / 4f;
		case 1 -> noOfDues1 = noOfDues / 12f;
		}

		noOfDues1 = getRoundedDoubleValueFifthDecimal(noOfDues1);
		System.out.println("*******  noOfDues1 ******** " + noOfDues1);

		String stampDate = doc;

		if (interimBonus.equalsIgnoreCase("yes")) {

			while (i <= noOfDues1) {

				double revRate = getRevRate(stampDate, uinNumber, planName, planCode);

				revAmount = val * (revRate / 100);
				Double revAmount1 = getRoundedDoubleValue(revAmount);
				finalRevAmount += revAmount1;

				System.out.println("*******  date ******** " + stampDate);
				System.out.println("******* Rev Amount ******* " + revAmount);
				System.out.println("******* Final Rev Amount ******* " + finalRevAmount);
				i++;
				stampDate = getNextDate(stampDate);
			}

			Double fraction = noOfDues1 - (i - 1);

			System.out.println("*******  no dues 1 ******** " + noOfDues1);
			Double fraction1 = getRoundedDoubleValueFifthDecimal(fraction);
			System.out.println("*******  Fraction 11/12  ******** " + fraction1);

			Double interimRate = interimBonusRepo.getRateForDoc(stampDate, planCode, planName, uinNumber);

			if (interimRate != 0) {
				interimAmount = (val * (interimRate / 100)) * fraction1;
				System.out.println("*******  Stamp Date ******** " + stampDate);
				System.out.println("*******  Interim Rate ******** " + interimRate);
				System.out.println("*******  Interim Amount ******** " + interimAmount);
				interimAmount1 = getRoundedDoubleValue(interimAmount);
			} else {
				interimAmount = -1.0;
			}

		}
		return interimAmount1;

	}

	public Double getGuaranteedRate(String startDate, String uinNunber, String planName, String planCode) {

		Double br = guaranteedBonusRateRepository.getRateForDoc(startDate, planCode, planName, uinNunber);
		return br;
	}

	public Double getGuaranteedAmount(String fup, String doc, String planName, String planCode, String uinNumber,
			Double sumAssured, Double premiumTerm, Integer billFreq, String coverStatus, String guaranteedBonus)
			throws ParseException {

		System.out.println(" Calculation For Guaranteed Amount ");

		Double gauranteedAmount = 0.0;
		Double finalgauranteedAmount = 0.0;
		double i = 1;

		Double val = 0.0;

		Double paidUpVal = getPaidUpValue(doc, fup, premiumTerm, billFreq, sumAssured);

		if (coverStatus.equalsIgnoreCase("LA") || coverStatus.equalsIgnoreCase("PU")) {
			val = paidUpVal;
		} else if (coverStatus.equalsIgnoreCase("IF") || coverStatus.equalsIgnoreCase("SP")) {
			val = sumAssured;
		} else {
			val = 0.0;
		}

		Double noOfDues = getNoOfDues(doc, fup, billFreq);
		Double noOfDues1 = 0.0;

		switch (billFreq) {
		case 12 -> noOfDues1 = noOfDues / 1;
		case 6 -> noOfDues1 = noOfDues / 2f;
		case 3 -> noOfDues1 = noOfDues / 4f;
		case 1 -> noOfDues1 = noOfDues / 12f;
		}

		noOfDues1 = getRoundedDoubleValueFifthDecimal(noOfDues1);
		System.out.println("******  noOfDues1 ******* " + noOfDues1);

		String stampDate = doc;

		if (guaranteedBonus.equalsIgnoreCase("yes")) {

			while (i <= noOfDues1) {

				double guaranteedRate = getGuaranteedRate(stampDate, uinNumber, planName, planCode);

				if (guaranteedRate != 0) {
					gauranteedAmount = (val * (guaranteedRate / 100));

					Double gauranteedAmount1 = getRoundedDoubleValue(gauranteedAmount);
					finalgauranteedAmount += gauranteedAmount1;

					System.out.println("******  date ******* " + stampDate);
					System.out.println("****** Guranteed Amount ****** " + gauranteedAmount);
					System.out.println("****** Final Guaranteed Amount ****** " + finalgauranteedAmount);
				} else {
					finalgauranteedAmount = -1.0;
				}

				i++;
				stampDate = getNextDate(stampDate);
			}

			Double fraction = noOfDues1 - (i - 1);

			Double fraction1 = getRoundedDoubleValueFifthDecimal(fraction);

			double guaranteedRate = getGuaranteedRate(stampDate, uinNumber, planName, planCode);

			if (guaranteedRate != 0) {
				gauranteedAmount = (val * (guaranteedRate / 100)) * fraction1;

				Double gauranteedAmount1 = getRoundedDoubleValue(gauranteedAmount);

				System.out.println("******  fraction ******* " + fraction1);
				System.out.println("******  rate ******* " + guaranteedRate);
				System.out.println("******  gua amt ******* " + gauranteedAmount1);
				finalgauranteedAmount += gauranteedAmount1;
			} else {
				finalgauranteedAmount = 1.0;
			}

		}

		return finalgauranteedAmount;

	}

	public Double getLoyalRate(String startDate, String uinNunber, String planName, String planCode) {

		Double br = loyaltyAdditionRateRepo.getRateForDoc(startDate, planCode, planName, uinNunber);
		return br;
	}

	public Double getLoyalAmount(String fup, String doc, String planName, String planCode, String uinNumber,
			Double sumAssured, Double premiumTerm, Integer billFreq, String coverStatus, String loyaltyBonus)
			throws ParseException {

		System.out.println(" Calculation For Loyalty Amount ");

		Double loyalAmount = 0.0;

		Double val = 0.0;

		Double paidUpVal = getPaidUpValue(doc, fup, premiumTerm, billFreq, sumAssured);

		if (coverStatus.equalsIgnoreCase("LA") || coverStatus.equalsIgnoreCase("PU")) {
			val = paidUpVal;
		} else if (coverStatus.equalsIgnoreCase("IF") || coverStatus.equalsIgnoreCase("SP")) {
			val = sumAssured;
		} else {
			val = 0.0;
		}

		Double noOfDues = getNoOfDues(doc, fup, billFreq);
		Double noOfDues1 = 0.0;

		switch (billFreq) {
		case 12 -> noOfDues1 = noOfDues / 1;
		case 6 -> noOfDues1 = noOfDues / 2f;
		case 3 -> noOfDues1 = noOfDues / 4f;
		case 1 -> noOfDues1 = noOfDues / 12f;
		}

		noOfDues1 = getCeilDoubleValue(noOfDues1);
		System.out.println("*******  noOfDues1 ******** " + noOfDues1);

		if (loyaltyBonus.equalsIgnoreCase("yes")) {

			double loyaltyRate = loyaltyAdditionRateRepo.getRate(uinNumber, noOfDues1);
			if (loyaltyRate != 0) {
				loyalAmount = (val * (loyaltyRate / 100));
				System.out.println("******* Loyalty Amount ******* " + loyalAmount);
			} else {
				loyalAmount = -1.0;
			}

		}

//			double revRate = getLoyalRate(stampDate, uinNumber, planName, planCode);

		return loyalAmount;

	}

	public Double getTermRate(String startDate, String uinNunber, String planName, String planCode) {

		Double br = termBonusRateRepo.getRateForDoc(startDate, planCode, planName, uinNunber);
		return br;
	}

	public Double getTermAmount(String fup, String doc, String planName, String planCode, String uinNumber,
			Double sumAssured, Double premiumTerm, Integer billFreq, String coverStatus, String terminalBonus)
			throws ParseException {

		System.out.println(" Calculation For Term Amount ");

		Double termAmount = 0.0;

		Double val = 0.0;

		Double paidUpVal = getPaidUpValue(doc, fup, premiumTerm, billFreq, sumAssured);

		if (coverStatus.equalsIgnoreCase("LA") || coverStatus.equalsIgnoreCase("PU")) {
			val = paidUpVal;
		} else if (coverStatus.equalsIgnoreCase("IF") || coverStatus.equalsIgnoreCase("SP")) {
			val = sumAssured;
		} else {
			val = 0.0;
		}

		Double noOfDues = getNoOfDues(doc, fup, billFreq);
		Double noOfDues1 = 0.0;

		switch (billFreq) {
		case 12 -> noOfDues1 = noOfDues / 1;
		case 6 -> noOfDues1 = noOfDues / 2f;
		case 3 -> noOfDues1 = noOfDues / 4f;
		case 1 -> noOfDues1 = noOfDues / 12f;
		}

		noOfDues1 = getCeilDoubleValue(noOfDues1);
		System.out.println("*******  noOfDues1 ******** " + noOfDues1);

		if (terminalBonus.equalsIgnoreCase("yes")) {

			double terminalRate = termBonusRateRepo.getRate(uinNumber, noOfDues1);
			if (terminalRate != 0) {
				termAmount = (val * (terminalRate / 100));
				System.out.println("******* Terminal Amount ******* " + termAmount);
			} else {
				termAmount = -1.0;
			}
		}
		return termAmount;

	}

	public Double getTotalBonus(Double revBonus, Double interimBonus, Double GuaranteedBonus, Double loyaltyBonus,
			Double terminalBonus) {

		Double totalBonus = revBonus + interimBonus + GuaranteedBonus + loyaltyBonus + terminalBonus;
		return totalBonus;

	}

	public Double getAnnuityRefund(String annuityStartDate, String dateOfDeath, Double yearlyAnnuityAmount,
			Double annuityGuranteedYears, String planCode) throws ParseException {
		Double annuityRefund = 0.0;

		System.out.println(" Calculation For Annuity Refund ");

		Double dueYears = getDiffInYear(dateOfDeath, annuityStartDate);

		Double pendingYears = annuityGuranteedYears - dueYears;

		System.out.println("****** Pending Years ***** " + pendingYears);

		Double i = 1.0;

		while (i <= pendingYears) {

			Double rate = annuityRatesRepo.getActiveByProductCode(planCode, i);
			Double value = (rate / 100) * yearlyAnnuityAmount;

			Double discountedValue = yearlyAnnuityAmount - value;

			System.out.println("****** Rate ***** " + rate);
			System.out.println("****** yearly annuity amount ***** " + yearlyAnnuityAmount);
			System.out.println("****** Value ***** " + value);
			System.out.println("****** Discounted Value ***** " + discountedValue);
			annuityRefund += discountedValue;

			i++;
		}

		return annuityRefund;
	}

	public Double getAnnuityPaid(String annuityStartDate, String dateOfDeath, Double yearlyAnnuityAmount)
			throws ParseException {

		System.out.println(" Calculation For Annuity Paid ");

		Double annuityPaid = 0.0;
		Double dueYears = getDiffInYear(dateOfDeath, annuityStartDate);
		annuityPaid = dueYears * yearlyAnnuityAmount;

		System.out.println("**** Due Years **** " + dueYears);
		System.out.println("**** Yearly annuity Amount **** " + yearlyAnnuityAmount);
		System.out.println("**** annuity paid *** " + annuityPaid);
		return annuityPaid;
	}

	public Double getInterestOnPremium(String fup, String dateOfDeath, String uinNumber, Double coverPremium,
			Integer billFreq) throws ParseException {

		System.out.println(" Calculation For Interest On Premium ");

		String nextCheckDate = "";
		Double instOnPrem1 = 0.0;
		LocalDate checkDate;

		nextCheckDate = fup;
		checkDate = getDateFormat(nextCheckDate);
		LocalDate DateOfDeath = getDateFormat(dateOfDeath);

		while (checkDate.isBefore(DateOfDeath)) {

			Double instOnPrem = getInterestForFup(nextCheckDate, uinNumber, dateOfDeath, coverPremium);

			System.out.println("***** Fup **** " + nextCheckDate);
			System.out.println("**** Inst on Prem Outer **** " + instOnPrem);
			instOnPrem1 += instOnPrem;
			nextCheckDate = getDateForPremCalc(nextCheckDate, billFreq);
			checkDate = getDateFormat(nextCheckDate);
		}

		return instOnPrem1;

	}

	public Double getInterestForFup(String fup, String uinNumber, String dateOfDeath, Double coverPremium)
			throws ParseException {
		String nextCheckDate = "";
		Double instOnPrem1 = 0.0;
		long daysDiff = 0;
		LocalDate checkDate;
		String toDate = "";

		nextCheckDate = fup;
		checkDate = getDateFormat(nextCheckDate);
		LocalDate DateOfDeath = getDateFormat(dateOfDeath);

		while (checkDate.isBefore(DateOfDeath)) {
			InterestPremiumRate ipr = interestPremiumRateRepo.getInterestPremiumRate(nextCheckDate, uinNumber);

			LocalDate ToDate = getDateFormat(ipr.getToDate());

			if (ToDate.isAfter(DateOfDeath) || ToDate.isEqual(DateOfDeath)) {
				toDate = getDecreaseYearByOneDay(dateOfDeath);
			} else {
				toDate = ipr.getToDate();
			}

			daysDiff = getDiffInDays(toDate, nextCheckDate);

			long newDaysDiff = daysDiff + 1;
			Double instOnPrem = getIntOnPrem(newDaysDiff, ipr.getRateOfInterest(), coverPremium);

			System.out.println("***** fup ***** " + nextCheckDate);
			System.out.println("***** toDate ***** " + toDate);
			System.out.println("****** days Diff ***** " + newDaysDiff);
			System.out.println("****** Int On Prem ***** " + instOnPrem);

			instOnPrem1 += instOnPrem;

			nextCheckDate = getIncreaseYearByOneDay(ipr.getToDate());
			checkDate = getDateFormat(nextCheckDate);
		}

		return instOnPrem1;
	}

	public Double getIntOnPrem(Long daysDiff, Double rate, Double IP) {

		Double newRate = rate / 100;
		Double daysDiff1 = daysDiff.doubleValue() / 365;
		Double value = newRate * daysDiff1 * IP;
		return value;
	}

	public Double getGstOnpremium(String riskComDate, String fup, Integer billFreq, Double coverPremium,
			String dateOfDeath, String planCode) throws ParseException {

		System.out.println(" Calculation For GST On Premium ");
		Double gstOnPrem = 0.0;
		LocalDate checkDate;
		String nextCheckDate = "";

		nextCheckDate = fup;
		checkDate = getDateFormat(nextCheckDate);
		LocalDate DateOfDeath = getDateFormat(dateOfDeath);

		Double noOfDues1 = 0.0;
		Double noOfDues = getNoOfDues(riskComDate, fup, billFreq);

		switch (billFreq) {
		case 12 -> noOfDues1 = noOfDues / 1;
		case 6 -> noOfDues1 = noOfDues / 2f;
		case 3 -> noOfDues1 = noOfDues / 4f;
		case 1 -> noOfDues1 = noOfDues / 12f;
		}
		noOfDues1 = getCeilDoubleValue(noOfDues1);

		while (checkDate.isBefore(DateOfDeath)) {

			Double rate = gstRateRepo.getGstOnPremRate(nextCheckDate, planCode, noOfDues1);
			Double gstPrem = (rate / 100) * coverPremium;
			gstOnPrem += gstPrem;

			System.out.println("**** No od Dues *** " + noOfDues1);
			System.out.println("**** Check Date *** " + nextCheckDate);
			System.out.println("**** plan Code *** " + planCode);
			System.out.println("**** Rate *** " + rate);
			System.out.println("**** gstPrem *** " + gstPrem);

			nextCheckDate = getDateForPremCalc(nextCheckDate, billFreq);
			checkDate = getDateFormat(nextCheckDate);
		}

		return gstOnPrem;
	}

	public String getDateForPremCalc(String startDate, Integer billFreq) throws ParseException {
		Date d1;
		String nextDate = "";

		if (billFreq == 1) {
			d1 = new SimpleDateFormat("yyyyMMdd").parse(startDate);
			LocalDate nextDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusMonths(1);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			nextDate = nextDate1.format(formatter);
		} else if (billFreq == 3) {
			d1 = new SimpleDateFormat("yyyyMMdd").parse(startDate);
			LocalDate nextDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusMonths(3);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			nextDate = nextDate1.format(formatter);
		} else if (billFreq == 6) {
			d1 = new SimpleDateFormat("yyyyMMdd").parse(startDate);
			LocalDate nextDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusMonths(6);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			nextDate = nextDate1.format(formatter);
		} else if (billFreq == 12) {
			d1 = new SimpleDateFormat("yyyyMMdd").parse(startDate);
			LocalDate nextDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusYears(1);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			nextDate = nextDate1.format(formatter);
		}

		return nextDate;
	}

	public Double getSurvivalBenifits(String riskComDate, String fup, String dateOfDeath, Double sumAssured,
			String planCode) throws ParseException {

		System.out.println(" Calculation For Survival Benifits ");
		Double survivalBenifits = 0.0;
		Double year = getDiffInYear(fup, riskComDate);

		List<SurvivalBenefitRate> sbr = survivalBenefitRateRepo.getallActiveByPlanCode(planCode, year);

		for (SurvivalBenefitRate sb : sbr) {

			Double value = (sb.getBenefitRate() / 100) * sumAssured;

			System.out.println("**** fup **** " + fup);
			System.out.println("**** rcd ***" + riskComDate);
			System.out.println("**** years *** " + year);
			System.out.println("**** Rate *** " + sb.getBenefitRate());
			System.out.println("**** SA *** " + sumAssured);
			System.out.println("**** Value **** " + value);

			survivalBenifits += value;
		}
		return survivalBenifits;
	}

	public Double getBasicDeathAmount(String docDate, String Fup, Double premiumTerm, Integer billFreq,
			Double sumAssured, String coverStatus) throws ParseException {

		Double basicDeathAmount = 0.0;

		if (coverStatus.equalsIgnoreCase("PU") || coverStatus.equalsIgnoreCase("LA")) {
			basicDeathAmount = getPaidUpValue(docDate, Fup, premiumTerm, billFreq, sumAssured);

			System.out.println("**** Basic Death Amount for paid up ****** " + basicDeathAmount);
		} else if (coverStatus.equalsIgnoreCase("IF") || coverStatus.equalsIgnoreCase("SP")) {
			basicDeathAmount = sumAssured;
		}

		return basicDeathAmount;
	}

	public Double getDeathAmount(String docDate, String Fup, Double premiumTerm, Integer billFreq, Double sumAssured,
			String coverStatus, String causeOfDeath, String uinNumber, String dateOfDeath) throws ParseException {

		System.out.println(" Calculation For Death Amount ");

		Double deathAmount = 0.0;
		Double val = 0.0;
		Long months = getDiffInMonth(docDate, Fup);

		System.out.println("******* Month *********" + months);

		Double deathFactor = deathClaimParamRepo.getDeathFactor(dateOfDeath, uinNumber, causeOfDeath, months);
		System.out.println("******* Death Factor *********" + deathFactor);

		val = getBasicDeathAmount(docDate, Fup, premiumTerm, billFreq, sumAssured, coverStatus);

		System.out.println("**** Value ***** " + val);

		if (deathFactor == 0) {
			Double deathFactor1 = deathClaimParamRepo.getDeathFactor1(dateOfDeath, uinNumber, months);

			System.out.println("******* date of death *********" + dateOfDeath);
			System.out.println("******* uin *********" + uinNumber);
			System.out.println("******* months *********" + months);
			System.out.println("******* Death Factor 1 *********" + deathFactor1);

			deathAmount = deathFactor1 * val;

		} else {
			deathAmount = deathFactor * val;

			System.out.println("******* date of death *********" + dateOfDeath);
			System.out.println("******* uin *********" + uinNumber);
			System.out.println("******* months *********" + months);

		}

		return deathAmount;
	}

	public Double getAdditionalSumAssured(String docDate, String Fup, Double sumAssured, String causeOfDeath,
			String uinNumber, String dateOfDeath) throws ParseException {

		System.out.println(" Calculation For Additional Sum Assured ");
		Double additionalSA = 0.0;
		Long months = getDiffInMonth(docDate, Fup);

		Double factor = deathClaimParamRepo.getDeathFactor(dateOfDeath, uinNumber, causeOfDeath, months);

		if (factor != 0) {
			additionalSA = (sumAssured * factor) - sumAssured;

		} else {
			Double factor1 = deathClaimParamRepo.getDeathFactorAddSA(dateOfDeath, uinNumber, months);

			if (factor1 != 0) {

				additionalSA = (sumAssured * factor1) - sumAssured;
			} else {
				Double factor2 = deathClaimParamRepo.getDeathFactor1(dateOfDeath, uinNumber, months);

				if (factor2 != 0) {
					additionalSA = (sumAssured * factor2) - sumAssured;
				}

			}

		}
		return additionalSA;
	}

	public Double getPenalIntrst(String approvalDate, String dateOfIntimation, Double netPayable)
			throws ParseException {
		Double penalInt = 0.0;

		Long daysDiff = getDiffInDays(approvalDate, dateOfIntimation);

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

	public Double getFundValue(Double noOfUnits, Double fundRate) {
		Double fundValue = 0.0;
		fundValue = noOfUnits * fundRate;
		return fundValue;
	}

	public Double getAccumilatedFundValue(String uinNumber, String effDate) {

		Double accumFundValue = 0.0;
		List<DeathClaimFundDetailsPas> funds = dcfdRepo.getallByUinNumber(uinNumber);

		for (DeathClaimFundDetailsPas fds : funds) {
			String f = fds.getFundCode();
			Double unit = fds.getUnits();

			Double rate = navRepo.getByFundRate(f, effDate);
			Double value = getFundValue(unit, rate);
			accumFundValue += value;
			addDeathClaimFundDetails(fds, rate, value, effDate);
		}

		System.out.println("***** Fund Value ***** " + accumFundValue);

		return accumFundValue;
	}

	public String addDeathClaimFundDetails(DeathClaimFundDetailsPas fds, Double rate, Double fundValue,
			String effDate) {

		DeathClaimLeapFundDetails dclfd = new DeathClaimLeapFundDetails();

		dclfd.setValidFlag(1);
		dclfd.setCompanyId(fds.getCompanyId());
		dclfd.setPolicyNo(fds.getPolicyNum());
		dclfd.setUinNum(fds.getUinNumber());
		dclfd.setPurpleFundCode(fds.getFundCode());
		dclfd.setPurpleFundName(fds.getFundName());
		dclfd.setPurpleNavDate(effDate);
		dclfd.setPurpleUnits(fds.getUnits());
		dclfd.setPurpleRateApp(rate);
		dclfd.setPurpleFundValue(fundValue);
		;
		dclfdRepo.save(dclfd);
		DeathClaimfundValueValidation(fds, dclfd);

		return errorService.getErrorById("ER001");
	}

	public void DeathClaimfundValueValidation(DeathClaimFundDetailsPas fds, DeathClaimLeapFundDetails pfds) {

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
		dclfdRepo.save(pfds);
	}

	public Double getUlipDeathBenefitAmount(String deathBenefitType, Double accumFundValue, Double sumAssured) {

		Double deathBenefitAmount = 0.0;
		if (deathBenefitType.equalsIgnoreCase("A")) {
			deathBenefitAmount = accumFundValue + sumAssured;
		} else if (deathBenefitType.equalsIgnoreCase("B")) {
			Double SAR = sumAssured - accumFundValue;
			deathBenefitAmount = SAR + accumFundValue;
		} else if (deathBenefitType.equalsIgnoreCase("C")) {
			if (sumAssured > accumFundValue) {
				deathBenefitAmount = sumAssured;
			} else {
				deathBenefitAmount = accumFundValue;
			}
		}

		System.out.println("*****  death Benefit Amount ****** " + deathBenefitAmount);

		return deathBenefitAmount;
	}

	public Double getSARorSA(String deathBenefitType, Double accumFundValue, Double sumAssured) {
		Double SarOrSA = 0.0;
		if (deathBenefitType.equalsIgnoreCase("B")) {
			Double SAR = sumAssured - accumFundValue;
			SarOrSA = SAR;
		} else {
			SarOrSA = sumAssured;
		}

		System.out.println("***** SarOrSA **** " + SarOrSA);
		return SarOrSA;
	}

	public Double getMortalityChargesRefund(String riskComDate, String dateOfDeath, String dateOfBirth,
			String effectiveDate, String uinNumber, Double SarOrSA, Double premiumTerm) throws ParseException {

		System.out.println(" Calculation For Mortality Charge Refund ");

		String nextCheckDate = "";
		Double mortChargeRefund = 0.0;
		LocalDate checkDate;

		LocalDate nextMCDate;

		nextCheckDate = riskComDate;
		checkDate = getDateFormat(nextCheckDate);
		LocalDate EffDate = getDateFormat(effectiveDate);

		System.out.println("****** Check date Before ******* " + nextCheckDate);
		while (checkDate.isBefore(EffDate)) {
			nextCheckDate = getIncreaseDateByOneMonth(nextCheckDate);
			checkDate = getDateFormat(nextCheckDate);
		}

		System.out.println("****** Check date After ******* " + nextCheckDate);

		String DOB = dateOfBirth;
		LocalDate checkDOB = getDateFormat(DOB);
		LocalDate checkDOD = getDateFormat(dateOfDeath);

		System.out.println("****** Check  DOB Before ******* " + DOB);
		while (checkDOB.isBefore(checkDOD)) {
			DOB = getIncreaseYearByOneYear(DOB);
			checkDOB = getDateFormat(DOB);
		}

		System.out.println("****** Check DOB After ******* " + DOB);
		System.out.println("****** Check Date ******* " + checkDate);
		System.out.println("****** Check DOD ******* " + checkDOD);

		if (checkDOB.isBefore(checkDate) && checkDOB.isAfter(checkDOD)) {

			Long daysDiff1 = getDiffInDays(DOB, dateOfDeath);
			Long newDaysDiff1 = daysDiff1;
			System.out.println("**** DOB ***** " + DOB);

			String newEffDate = getDecreaseYearByOneDay(effectiveDate);
			Double age = getALB(newEffDate, dateOfBirth);
			System.out.println("****** Age ******* " + age.intValue());
			Integer premTermInt = premiumTerm.intValue();
			System.out.println("***** premium term **** ");

			Float mortRate = mortRateRepo.findByCCCT(uinNumber, age.intValue(), premTermInt.toString());
			Float newRate = mortRate / 1000;
			Double DDperyear = newDaysDiff1.doubleValue() / 365;
			Double mortChargeRefund1 = SarOrSA * newRate * DDperyear;

			System.out.println("****** Age ******* " + age);
			System.out.println("**** Days Diff1 ***** " + newDaysDiff1);
			System.out.println("***** SarOrSA **** " + SarOrSA);
			System.out.println("****** Mort Rate ******* " + mortRate);
			System.out.println("****** MC Refund1  ******* " + mortChargeRefund1);

			Long daysDiff2 = getDiffInDays(nextCheckDate, DOB);
			Long newDaysDiff2 = daysDiff2;
			Double age1 = age + 1;
			Float mortRate1 = mortRateRepo.findByCCCT(uinNumber, age1.intValue(), premTermInt.toString());
			Float newRate1 = mortRate1 / 1000;
			Double DDperyear1 = newDaysDiff2.doubleValue() / 365;
			Double mortChargeRefund2 = SarOrSA * newRate1 * DDperyear1;

			System.out.println("**** Days Diff2 ***** " + daysDiff2);
			System.out.println("****** Age1 ******* " + age1);
			System.out.println("****** Mort Rate1 ******* " + mortRate1);
			System.out.println("****** MC Refund2  ******* " + mortChargeRefund2);

			mortChargeRefund = mortChargeRefund1 + mortChargeRefund2;
		} else {
			System.out.println("**** Inside Else **** ");
			Long daysDiff1 = getDiffInDays(nextCheckDate, dateOfDeath);
			Long newDaysDiff1 = daysDiff1;
			Double age = getALB(dateOfDeath, dateOfBirth);
			Integer premTermInt = premiumTerm.intValue();
			System.out.println("****** Age ******* " + age);
			System.out.println("**** days duff ***** " + newDaysDiff1);
			System.out.println("***** premium term **** " + premTermInt);
			Float mortRate = mortRateRepo.findByCCCT(uinNumber, age.intValue(), premTermInt.toString());
			Float newRate = mortRate / 1000;
			Double DDperyear = newDaysDiff1.doubleValue() / 365;

			System.out.println("***** Age ***** " + age);
			System.out.println("***** rate ***** " + newRate);

			Double mortChargeRefund1 = SarOrSA * newRate * DDperyear;

			System.out.println("***** mort Charge **** " + mortChargeRefund1);
			mortChargeRefund = mortChargeRefund1;

		}
		return mortChargeRefund;
	}

	public Double getUinSA(Long policyNo, String uinNo) {

		Double SA = 0.0;
		DeathClaimCoverDetails dccd = dccdRepo.findByuinNoAndPolicyNo(uinNo, policyNo);

		if (!dccd.getCoverStatus().equalsIgnoreCase("IF")) {
			SA = 0.0;
		} else {
			SA = dccd.getSumAssured();
		}

		return SA;
	}

	public Double getSAOrFundValue(String uinNo, Long policyNo, String effDate, Double sumAssured, Double fundValue) {
		Double value = 0.0;

		Double sa = getUinSA(policyNo, uinNo);
		Double fv = getAccumilatedFundValue(uinNo, effDate);

		if (fv > sa) {
			value = fv;
		} else {
			value = sa;
		}

		return value;
	}

	public Double getSAPlusFundValue(String uinNo, Long policyNo, String effDate, Double sumAssured, Double fundValue) {
		Double value = 0.0;

		Double sa = getUinSA(policyNo, uinNo);
		Double fv = getAccumilatedFundValue(uinNo, effDate);
		value = sa + fv;
		return value;
	}

	public Double getAllPayble(Double SA, Double addSA, Double RB, Double IB, Double TB, Double GA, Double LA,
			Double AR) {

		Double payble = SA + addSA + RB + IB + TB + GA + LA + AR;
		return payble;
	}

	public Double getAllRecoverable(Double premiumRecovery, Double intOnPrem, Double gstOnPrem,
			Double survivalBenifits) {
		Double recoverable = premiumRecovery + intOnPrem + gstOnPrem + survivalBenifits;
		return recoverable;
	}

	public String addNonUlipDeathClaim(String uinNumber, Long userId) throws ParseException {
		Double value = 0.0;
		DeathClaimCoverDetails dccd = dccdRepo.findByuinNo(uinNumber);
		DeathClaimPolicyDetails dcpd = dcpdRepo.getActiveByPolicyNo(dccd.getPolicyNo());
		DeathClaimTransactionPas dctp = dctpRepo.findByUinNumber(uinNumber);
		UinMaster uinMaster = uinMasterRepository.getActiveByUIN(uinNumber);
		Integer billFreq = Integer.parseInt(dcpd.getBillFreq());

		Double premiumPaid = getPremiumPaid(dccd.getDocDate(), dcpd.getFup(), billFreq, dccd.getCoverPremium());

		Double premiumRecovery = getPremiumRecovery(dctp.getDateOfDeath(), dcpd.getFup(), billFreq,
				dccd.getCoverPremium());
		Double paidUpValue = getPaidUpValue(dccd.getDocDate(), dcpd.getFup(), dccd.getPremiumTerm(), billFreq,
				dccd.getSumAssured());
		Double revAmount = getRevAmount(dcpd.getFup(), dccd.getDocDate(), dccd.getPlanName(), dccd.getPlanCode(),
				uinNumber, dccd.getSumAssured(), dccd.getPremiumTerm(), billFreq, dccd.getCoverStatus(),
				uinMaster.getRevesionaryBonus());
		Double interimAmount = getInterimAmount(dcpd.getFup(), dccd.getDocDate(), dccd.getPlanName(),
				dccd.getPlanCode(), uinNumber, dccd.getSumAssured(), dccd.getPremiumTerm(), billFreq,
				dccd.getCoverStatus(), uinMaster.getInterimBonus());
		Double guaranteedAmount = getGuaranteedAmount(dcpd.getFup(), dccd.getDocDate(), dccd.getPlanName(),
				dccd.getPlanCode(), uinNumber, dccd.getSumAssured(), dccd.getPremiumTerm(), billFreq,
				dccd.getCoverStatus(), uinMaster.getGuaranteedBonus());
		Double loyaltyAmount = getLoyalAmount(dcpd.getFup(), dccd.getDocDate(), dccd.getPlanName(), dccd.getPlanCode(),
				uinNumber, dccd.getSumAssured(), dccd.getPremiumTerm(), billFreq, dccd.getCoverStatus(),
				uinMaster.getLoyaltyBonus());
		Double termialAmount = getTermAmount(dcpd.getFup(), dccd.getDocDate(), dccd.getPlanName(), dccd.getPlanCode(),
				uinNumber, dccd.getSumAssured(), dccd.getPremiumTerm(), billFreq, dccd.getCoverStatus(),
				uinMaster.getTerminalBonus());

		Double totalBonus = getTotalBonus(revAmount, interimAmount, guaranteedAmount, loyaltyAmount, termialAmount);

		Double basicDeathAmount = getBasicDeathAmount(dccd.getDocDate(), dcpd.getFup(), dccd.getPremiumTerm(), billFreq,
				dccd.getSumAssured(), dccd.getCoverStatus());
		Double deathAmount = getDeathAmount(dccd.getDocDate(), dcpd.getFup(), dccd.getPremiumTerm(), billFreq,
				dccd.getSumAssured(), dccd.getCoverStatus(), dctp.getCauseOfDeath(), uinNumber, dctp.getDateOfDeath());

		Double basicSumAssured = dccd.getSumAssured();
		Double additionalSumAssured = getAdditionalSumAssured(dccd.getDocDate(), dcpd.getFup(), dccd.getSumAssured(),
				dctp.getCauseOfDeath(), uinNumber, dctp.getDateOfDeath());

		Double interestOnPrem = getInterestOnPremium(dcpd.getFup(), dctp.getDateOfDeath(), uinNumber,
				dccd.getCoverPremium(), billFreq);
		Double gstOnPrem = getGstOnpremium(dccd.getRiskComDate(), dcpd.getFup(), billFreq, dcpd.getInstallmentPremium(),
				dctp.getDateOfDeath(), dccd.getPlanCode());

		Double annuityRefund = getAnnuityRefund(dctp.getAnnuityStartDate(), dctp.getDateOfDeath(),
				dctp.getYearlyAnnuityAmunt(), dctp.getAnnuityGuranteedYears(), dccd.getPlanCode());
		Double annuityPaid = getAnnuityPaid(dctp.getAnnuityStartDate(), dctp.getDateOfDeath(),
				dctp.getYearlyAnnuityAmunt());
		Double survivalBenifits = getSurvivalBenifits(dccd.getRiskComDate(), dcpd.getFup(), dctp.getDateOfDeath(),
				dccd.getSumAssured(), dccd.getPlanCode());

		Double payble = getAllPayble(basicSumAssured, additionalSumAssured, revAmount, interimAmount, termialAmount,
				guaranteedAmount, loyaltyAmount, annuityRefund);
		Double recoverable = getAllRecoverable(premiumRecovery, interestOnPrem, gstOnPrem, survivalBenifits);

		Double totalDeathClaim = payble - recoverable;

		Double deathClaimBenifits = dccd.getSumAssured() - survivalBenifits;

		if (revAmount != -1 && interimAmount != -1 && guaranteedAmount != -1 && loyaltyAmount != -1
				&& termialAmount != -1) {
			DeathClaimLeapDetails dcld = new DeathClaimLeapDetails();

			dcld.setCompanyId(dccd.getCompanyId());
			dcld.setPolicyNo(dccd.getPolicyNo());
			dcld.setTransNo(dctp.getTransNo());
			dcld.setUinNumber(uinNumber);
			dcld.setClientId(dcpd.getClntNum());
			dcld.setContractType(uinMaster.getProductType());
			dcld.setProductType(uinMaster.getProductType());
			dcld.setPolicyStatus(dcpd.getStatusCode());
			dcld.setFup(dcpd.getFup());
			dcld.setTotalPremium(premiumPaid);
			dcld.setBasicSa(basicDeathAmount);
			dcld.setAdditionalSa(additionalSumAssured);
			dcld.setInbuiltRiderSa(value);
			dcld.setInterimBonus(interimAmount);
			dcld.setReversionaryBonus(revAmount);
			dcld.setGuranteedAddition(guaranteedAmount);
			dcld.setLoyaltyAddtion(loyaltyAmount);
			dcld.setOtherRiderBenefit(value);
			dcld.setTerminalBonus(termialAmount);
			dcld.setTotalBonus(totalBonus);
			dcld.setAnnuityRefund(annuityRefund);
			dcld.setPolicyDeposit(dctp.getPolicyDeposit());
			dcld.setGrossPayable(deathClaimBenifits);
			dcld.setTermPremRecov(value);
			dcld.setInterestOnPremium(interestOnPrem);
			dcld.setGstOnPremium(gstOnPrem);
			dcld.setCdaCharges(dctp.getCdaCharges());
			dcld.setOtherCharges(value);
			dcld.setPolicyLoan(value);
			dcld.setPolicyLoanInterest(value);
			dcld.setMoneybackPaid(annuityRefund);
			dcld.setAnnuityPaid(annuityPaid);
			dcld.setMortChargesRefund(value);
			dcld.setAdminFeeRefund(value);
			dcld.setGuranteedAddCharges(value);
			dcld.setTds(dctp.getTds());
			dcld.setTotalDeathClaim(totalDeathClaim);
			dcld.setTotalRecovery(recoverable);

			Double netPay = totalBonus + value; // value is penal interest equals to zero

			Double penalInterest = getPenalIntrst(dctp.getApprovalDate(), dctp.getDateOfIntimation(), netPay);

			dcld.setPenalInterest(penalInterest);

			Double newNetPay = totalBonus + penalInterest;

			dcld.setNetPayable(newNetPay);
			deathClaimLeapDetailsRepo.save(dcld);

			String flag = getNonLeapFlag(dcld, dctp);
			String remark = getNonLeapRemarks(dcld, dctp);
			dcld.setLeapFlag(flag);
			dcld.setLeapRemarks(remark);
			deathClaimLeapDetailsRepo.save(dcld);
		} else {
			DeathClaimLeapDetails dcld = new DeathClaimLeapDetails();

			dcld.setCompanyId(dccd.getCompanyId());
			dcld.setPolicyNo(dccd.getPolicyNo());
			dcld.setTransNo(dctp.getTransNo());
			dcld.setUinNumber(uinNumber);
			dcld.setClientId(dcpd.getClntNum());
			dcld.setContractType(uinMaster.getProductType());
			dcld.setProductType(uinMaster.getProductType());
			dcld.setPolicyStatus(dcpd.getStatusCode());
			dcld.setFup(dcpd.getFup());
			dcld.setTotalPremium(premiumPaid);
			dcld.setBasicSa(basicSumAssured);
			dcld.setAdditionalSa(additionalSumAssured);
			dcld.setInbuiltRiderSa(value);
			dcld.setInterimBonus(value);
			dcld.setReversionaryBonus(value);
			dcld.setGuranteedAddition(value);
			dcld.setLoyaltyAddtion(value);
			dcld.setOtherRiderBenefit(value);
			dcld.setTerminalBonus(value);
			dcld.setTotalBonus(value);
			dcld.setAnnuityRefund(value);
			dcld.setTotalDeathClaim(value);
			dcld.setPolicyDeposit(dctp.getPolicyDeposit());
			dcld.setPenalInterest(value);
			dcld.setGrossPayable(value);
			dcld.setTermPremRecov(value);
			dcld.setInterestOnPremium(value);
			dcld.setGstOnPremium(value);
			dcld.setCdaCharges(dctp.getCdaCharges());
			dcld.setOtherCharges(value);
			dcld.setPolicyLoan(value);
			dcld.setPolicyLoanInterest(value);
			dcld.setMoneybackPaid(value);
			dcld.setAnnuityPaid(value);
			dcld.setMortChargesRefund(value);
			dcld.setAdminFeeRefund(value);
			dcld.setGuranteedAddCharges(value);
			dcld.setTds(dctp.getTds());
			dcld.setTotalRecovery(value);
			dcld.setNetPayable(value);
			dcld.setLeapFlag("Fail");
			String remark = getNonUlipremarks(revAmount, interimAmount, guaranteedAmount, loyaltyAmount, termialAmount);
			dcld.setLeapRemarks(remark);
			deathClaimLeapDetailsRepo.save(dcld);
		}

		return errorService.getErrorById("");
	}

	public String addUlipDeathClaim(String uinNumber, Long userId) throws ParseException {
		Double value = 0.0;
		DeathClaimCoverDetails dccd = dccdRepo.findByuinNo(uinNumber);
		DeathClaimPolicyDetails dcpd = dcpdRepo.getActiveByPolicyNo(dccd.getPolicyNo());
		DeathClaimTransactionPas dctp = dctpRepo.findByUinNumber(uinNumber);
		DeathClaimClientDetails deathclaimClient = deathClaimClientRepo.getActiveByClientNo(dcpd.getClntNum());
		UinMaster uinMaster = uinMasterRepository.getActiveByUIN(uinNumber);

		Integer billFreq = Integer.parseInt(dcpd.getBillFreq());

		Double accumFundValue = getAccumilatedFundValue(uinNumber, dctp.getEffectiveDate());
		Double deathBenefitAmount = getUlipDeathBenefitAmount(dctp.getDeathBenefitType(), accumFundValue,
				dccd.getSumAssured());
		Double SarOrSA = getSARorSA(dctp.getDeathBenefitType(), accumFundValue, dccd.getSumAssured());
		Double ulipMortRefund = getMortalityChargesRefund(dccd.getRiskComDate(), dctp.getDateOfDeath(),
				deathclaimClient.getLaDob(), dctp.getEffectiveDate(), uinNumber, SarOrSA, dccd.getCoverPremium());

		Double premiumPaid = getPremiumPaid(dccd.getDocDate(), dcpd.getFup(), billFreq, dccd.getCoverPremium());

		Double premiumRecovery = getPremiumRecovery(dctp.getDateOfDeath(), dcpd.getFup(), billFreq,
				dccd.getCoverPremium());
		Double paidUpValue = getPaidUpValue(dccd.getDocDate(), dcpd.getFup(), dccd.getPremiumTerm(), billFreq,
				dccd.getSumAssured());

		Double basicDeathAmount = getBasicDeathAmount(dccd.getDocDate(), dcpd.getFup(), dccd.getPremiumTerm(), billFreq,
				dccd.getSumAssured(), dccd.getCoverStatus());
		Double deathAmount = getDeathAmount(dccd.getDocDate(), dcpd.getFup(), dccd.getPremiumTerm(), billFreq,
				dccd.getSumAssured(), dccd.getCoverStatus(), dctp.getCauseOfDeath(), uinNumber, dctp.getDateOfDeath());

		Double basicSumAssured = dccd.getSumAssured();
		Double additionalSumAssured = getAdditionalSumAssured(dccd.getDocDate(), dcpd.getFup(), dccd.getSumAssured(),
				dctp.getCauseOfDeath(), uinNumber, dctp.getDateOfDeath());

//		Double payble = getAllPayble(basicSumAssured, additionalSumAssured, revAmount, interimAmount, termialAmount,
//				guaranteedAmount, loyaltyAmount, annuityRefund);
//		Double recoverable = getAllRecoverable(premiumRecovery, interestOnPrem, gstOnPrem, survivalBenifits);

		Double payble = SarOrSA + accumFundValue + ulipMortRefund;
		Double recoverable = value;

		Double totalDeathClaim = payble - recoverable;

		Double deathClaimBenifits = deathBenefitAmount;

		DeathClaimLeapDetails dcld = new DeathClaimLeapDetails();

		dcld.setCompanyId(dccd.getCompanyId());
		dcld.setPolicyNo(dccd.getPolicyNo());
		dcld.setTransNo(dctp.getTransNo());
		dcld.setUinNumber(uinNumber);
		dcld.setClientId(dcpd.getClntNum());
		dcld.setContractType(uinMaster.getProductType());
		dcld.setProductType(uinMaster.getProductType());
		dcld.setPolicyStatus(dcpd.getStatusCode());
		dcld.setFup(dcpd.getFup());
		dcld.setTotalPremium(premiumPaid);
		dcld.setBasicSa(SarOrSA);
		dcld.setAdditionalSa(value);
		dcld.setInbuiltRiderSa(value);
		dcld.setInterimBonus(value);
		dcld.setReversionaryBonus(value);
		dcld.setGuranteedAddition(value);
		dcld.setLoyaltyAddtion(value);
		dcld.setOtherRiderBenefit(value);
		dcld.setTerminalBonus(value);
		dcld.setTotalBonus(value);
		dcld.setAnnuityRefund(value);
		dcld.setPolicyDeposit(dctp.getPolicyDeposit());
		dcld.setTermPremRecov(value);
		dcld.setInterestOnPremium(value);
		dcld.setGstOnPremium(value);
		dcld.setCdaCharges(dctp.getCdaCharges());
		dcld.setOtherCharges(value);
		dcld.setPolicyLoan(value);
		dcld.setPolicyLoanInterest(value);
		dcld.setMoneybackPaid(value);
		dcld.setAnnuityPaid(value);
		dcld.setMortChargesRefund(ulipMortRefund);
		dcld.setAdminFeeRefund(value);
		dcld.setGuranteedAddCharges(value);
		dcld.setTds(dctp.getTds());
		dcld.setFundValue(accumFundValue);
		dcld.setEffectiveDate(dctp.getEffectiveDate());
		dcld.setTotalDeathClaim(totalDeathClaim);
		dcld.setTotalRecovery(recoverable);

		Double grossPayble = deathClaimBenifits;
		dcld.setGrossPayable(grossPayble);

		Double penalInterest = value;
		dcld.setPenalInterest(penalInterest);

		Double netPayble = grossPayble - recoverable;
		dcld.setNetPayable(netPayble);
		deathClaimLeapDetailsRepo.save(dcld);

		String flag = getUlipLeapFlag(dcld, dctp);
		String remark = getUlipLeapRemarks(dcld, dctp);
		dcld.setLeapFlag(flag);
		dcld.setLeapRemarks(remark);
		deathClaimLeapDetailsRepo.save(dcld);
		return errorService.getErrorById("");
	}

	public String getNonLeapFlag(DeathClaimLeapDetails dcld, DeathClaimTransactionPas dctp) {
		String flag = "";

		if (!dctp.getBasicSumAssured().equals(dcld.getBasicSa())) {
			flag = "Fail";
		} else if (!dctp.getAdditionalSumAssured().equals(dcld.getAdditionalSa())) {
			flag = "Fail";
		} else if (!dctp.getReversionaryBonus().equals(dcld.getReversionaryBonus())) {
			flag = "Fail";
		} else if (!dctp.getInterimBonus().equals(dcld.getInterimBonus())) {
			flag = "Fail";
		} else if (!dctp.getGuranteedBonus().equals(dcld.getGuranteedAddition())) {
			flag = "Fail";
		} else if (!dctp.getLoyaltyAddition().equals(dcld.getLoyaltyAddtion())) {
			flag = "Fail";
		} else if (!dctp.getTerminalBonus().equals(dcld.getTerminalBonus())) {
			flag = "Fail";
		} else if (!dctp.getTotalBonus().equals(dcld.getTotalBonus())) {
			flag = "Fail";
		} else if (!dctp.getTotalDeathClaim().equals(dcld.getTotalDeathClaim())) {
			flag = "Fail";
		} else if (!dctp.getPenalInterest().equals(dcld.getPenalInterest())) {
			flag = "Fail";
		} else if (!dctp.getGrossPay().equals(dcld.getGrossPayable())) {
			flag = "Fail";
		} else if (!dctp.getInterestOnPrem().equals(dcld.getInterestOnPremium())) {
			flag = "Fail";
		} else if (!dctp.getGstOnPrem().equals(dcld.getGstOnPremium())) {
			flag = "Fail";
		} else if (!dctp.getMoneybackPaidRecov().equals(dcld.getMoneybackPaid())) {
			flag = "Fail";
		} else if (!dctp.getAnnuityPaidRecov().equals(dcld.getAnnuityRefund())) {
			flag = "Fail";
		} else if (!dctp.getTotalRecovery().equals(dcld.getTotalRecovery())) {
			flag = "Fail";
		} else if (!dctp.getNetPayable().equals(dcld.getNetPayable())) {
			flag = "Fail";
		} else {
			flag = "Pass";
		}

		return flag;
	}

	public String getNonLeapRemarks(DeathClaimLeapDetails dcld, DeathClaimTransactionPas dctp) {
		String msg = "";

		if (!dctp.getBasicSumAssured().equals(dcld.getBasicSa())) {
			String msg1 = "Sum Assured";
			msg = msg1;
		} else if (!dctp.getAdditionalSumAssured().equals(dcld.getAdditionalSa())) {
			String msg1 = "Additional Sum Assured";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getReversionaryBonus().equals(dcld.getReversionaryBonus())) {
			String msg1 = "Revisionary Bonus";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getInterimBonus().equals(dcld.getInterimBonus())) {
			String msg1 = "Interim Bonus";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getGuranteedBonus().equals(dcld.getGuranteedAddition())) {
			String msg1 = "Guaranteed Bonus";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getLoyaltyAddition().equals(dcld.getLoyaltyAddtion())) {
			String msg1 = "Loyalty Additional";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getTerminalBonus().equals(dcld.getTerminalBonus())) {
			String msg1 = "Terminal Bonus";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getTotalBonus().equals(dcld.getTotalBonus())) {
			String msg1 = "Total Bonus";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getTotalDeathClaim().equals(dcld.getTotalDeathClaim())) {
			String msg1 = "Total Death Claim";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getPenalInterest().equals(dcld.getPenalInterest())) {
			String msg1 = "Penal Interest";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getGrossPay().equals(dcld.getGrossPayable())) {
			String msg1 = "Gross Pay";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getInterestOnPrem().equals(dcld.getInterestOnPremium())) {
			String msg1 = "Interest On Premium";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getGstOnPrem().equals(dcld.getGstOnPremium())) {
			String msg1 = "GST On Premium";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getMoneybackPaidRecov().equals(dcld.getMoneybackPaid())) {
			String msg1 = "Money Back Paid";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getAnnuityPaidRecov().equals(dcld.getAnnuityRefund())) {
			String msg1 = "Annuity Paid Refund";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getTotalRecovery().equals(dcld.getTotalRecovery())) {
			String msg1 = "Total Recovery";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getNetPayable().equals(dcld.getNetPayable())) {
			String msg1 = "Net Payable";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else {
			msg = "Passed";
		}

		return msg;
	}

	public String getUlipLeapFlag(DeathClaimLeapDetails dcld, DeathClaimTransactionPas dctp) {
		String flag = "";

		if (!dctp.getBasicSumAssured().equals(dcld.getBasicSa())) {
			flag = "Fail";
		} else if (!dctp.getMortalityChargeRefund().equals(dcld.getMortChargesRefund())) {
			flag = "Fail";
		} else if (!dctp.getFundValue().equals(dcld.getFundValue())) {
			flag = "Fail";
		} else if (!dctp.getTotalDeathClaim().equals(dcld.getTotalDeathClaim())) {
			flag = "Fail";
		} else if (!dctp.getGrossPay().equals(dcld.getGrossPayable())) {
			flag = "Fail";
		} else if (!dctp.getTotalRecovery().equals(dcld.getTotalRecovery())) {
			flag = "Fail";
		} else if (!dctp.getNetPayable().equals(dcld.getNetPayable())) {
			flag = "Fail";
		} else {
			flag = "Pass";
		}
		return flag;
	}

	public String getUlipLeapRemarks(DeathClaimLeapDetails dcld, DeathClaimTransactionPas dctp) {
		String msg = "";

		if (!dctp.getBasicSumAssured().equals(dcld.getBasicSa())) {
			String msg1 = "Sum Assured";
			msg = msg1;
		} else if (!dctp.getMortalityChargeRefund().equals(dcld.getMortChargesRefund())) {
			String msg1 = "Mort Charge Refund";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getFundValue().equals(dcld.getFundValue())) {
			String msg1 = "Fund Value";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getTotalDeathClaim().equals(dcld.getTotalDeathClaim())) {
			String msg1 = "Total Death Claim";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getGrossPay().equals(dcld.getGrossPayable())) {
			String msg1 = "Gross Pay";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getTotalRecovery().equals(dcld.getTotalRecovery())) {
			String msg1 = "Total Recovery";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else if (!dctp.getNetPayable().equals(dcld.getNetPayable())) {
			String msg1 = "Net Payable";
			if (msg.equals("")) {
				msg = msg1;
			} else {
				msg = msg + "," + msg1;
			}
		} else {
			msg = "Passed";
		}

		return msg;
	}

	public String getNonUlipremarks(Double revAmount, Double interimAmount, Double guarenteedAmount,
			Double loyaltyAmount, Double terminalAmount) {
		String remarks = "";

		if (revAmount != -1 && interimAmount != -1 && guarenteedAmount != -1 && loyaltyAmount != -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in terminal bonus table ";
		} else if (revAmount != -1 && interimAmount != -1 && guarenteedAmount != -1 && loyaltyAmount == -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in loyalty addition bonus table ";
		} else if (revAmount != -1 && interimAmount != -1 && guarenteedAmount != -1 && loyaltyAmount == -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in loyalty addion bonus and terminal bonus table ";
		} else if (revAmount != -1 && interimAmount != -1 && guarenteedAmount == -1 && loyaltyAmount != -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in guaranteed bonus table ";
		} else if (revAmount != -1 && interimAmount != -1 && guarenteedAmount == -1 && loyaltyAmount != -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in guaranteed bonus and  terminal bonus table ";
		} else if (revAmount != -1 && interimAmount != -1 && guarenteedAmount == -1 && loyaltyAmount == -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in guaranteed bonus and loyalty addition bonus table ";
		} else if (revAmount != -1 && interimAmount != -1 && guarenteedAmount == -1 && loyaltyAmount == -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in guaranteed bonus and loyalty addition bonus and terminal bonus table ";
		} else if (revAmount != -1 && interimAmount == -1 && guarenteedAmount != -1 && loyaltyAmount != -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in interim bonus table ";
		} else if (revAmount != -1 && interimAmount == -1 && guarenteedAmount != -1 && loyaltyAmount != -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in interim bonus and terminal bonus table ";
		} else if (revAmount != -1 && interimAmount == -1 && guarenteedAmount != -1 && loyaltyAmount == -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in interim bonus and loyalty addition bonus table ";
		} else if (revAmount != -1 && interimAmount == -1 && guarenteedAmount != -1 && loyaltyAmount == -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in interim bonus and loyalty addition bonus and terminal bonus table ";
		} else if (revAmount != -1 && interimAmount == -1 && guarenteedAmount == -1 && loyaltyAmount != -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in interim bonus and guaranteed bonus table ";
		} else if (revAmount != -1 && interimAmount == -1 && guarenteedAmount == -1 && loyaltyAmount != -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in interim bonus and guaranteed bonus and terminal bonus table ";
		} else if (revAmount != -1 && interimAmount == -1 && guarenteedAmount == -1 && loyaltyAmount == -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in interim bonus and guaranteed bonus and loyalty addition bonus table ";
		} else if (revAmount != -1 && interimAmount == -1 && guarenteedAmount == -1 && loyaltyAmount == -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in interim bonus and guaranteed bonus and loyalty addition bonus and terminal bonus table ";
		} else if (revAmount == -1 && interimAmount != -1 && guarenteedAmount != -1 && loyaltyAmount != -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in revesionary bonus table ";
		} else if (revAmount == -1 && interimAmount != -1 && guarenteedAmount != -1 && loyaltyAmount != -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in revesionary bonus and terminal table ";
		} else if (revAmount == -1 && interimAmount != -1 && guarenteedAmount != -1 && loyaltyAmount == -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in revesionary bonus and loyalty addition bonus table ";
		} else if (revAmount == -1 && interimAmount != -1 && guarenteedAmount != -1 && loyaltyAmount == -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in revesionary bonus and loyalty addition bonus and terminal bonus table ";
		} else if (revAmount == -1 && interimAmount != -1 && guarenteedAmount == -1 && loyaltyAmount != -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in revesionary bonus and guaranteed bonus table ";
		} else if (revAmount == -1 && interimAmount != -1 && guarenteedAmount == -1 && loyaltyAmount != -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in revesionary bonus and  guaranteed bonus and terminal bonus table ";
		} else if (revAmount == -1 && interimAmount != -1 && guarenteedAmount == -1 && loyaltyAmount == -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in revesionary bonus and guaranteed bonus and loyalty addition bonus table ";
		} else if (revAmount == -1 && interimAmount != -1 && guarenteedAmount == -1 && loyaltyAmount == -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in revesionary bonus and guaranteed bonus and loyalty addition bonus and terminal bonus table ";
		} else if (revAmount == -1 && interimAmount == -1 && guarenteedAmount != -1 && loyaltyAmount != -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in revesionary bonus and interim bonus table ";
		} else if (revAmount == -1 && interimAmount == -1 && guarenteedAmount != -1 && loyaltyAmount != -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in revesionary bonus and interim bonus and terminal bonus table ";
		} else if (revAmount == -1 && interimAmount == -1 && guarenteedAmount != -1 && loyaltyAmount == -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in revesionary bonus and interim bonus and loyalty addition bonus  table ";
		} else if (revAmount == -1 && interimAmount == -1 && guarenteedAmount != -1 && loyaltyAmount == -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in revesionary bonus and interim bonus and loyalty addition bonus and terminal bonus table ";
		} else if (revAmount == -1 && interimAmount == -1 && guarenteedAmount == -1 && loyaltyAmount != -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in revesionary bonus and interim bonus and guarateed bonus table ";
		} else if (revAmount == -1 && interimAmount == -1 && guarenteedAmount == -1 && loyaltyAmount != -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in revesionary bonus and interim bonus and guaranteed bonus and terminal bonus table ";
		} else if (revAmount == -1 && interimAmount == -1 && guarenteedAmount == -1 && loyaltyAmount == -1
				&& terminalAmount != -1) {
			remarks = "Values are missing in revesionary bonus and interim bonus and guaranteed bonus and loyalty addition bonus table ";
		} else if (revAmount == -1 && interimAmount == -1 && guarenteedAmount == -1 && loyaltyAmount == -1
				&& terminalAmount == -1) {
			remarks = "Values are missing in all bonus table ";
		}

		return remarks;
	}

	public String assignSingleTrans(String uinNumber, Long userId) {

		try {
			DeathClaimCoverDetails dccd = dccdRepo.findByuinNo(uinNumber);
			UinMaster uinMaster = uinMasterRepository.getActiveByUIN(uinNumber);

			if (uinMaster.getProductType().contains("N")) {
				addNonUlipDeathClaim(uinNumber, userId);
			}
//			else if (uinMaster.getProductType().contains("L")) {
//				addUlipSurrender(policyNo, userId);
//			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return errorService.getErrorById("ER001");
	}

	public String assignMultipleTrans(List<String> uinNumbers, Long userId) {

		uinNumbers.forEach((uinNumber) -> {
			try {
				DeathClaimCoverDetails dccd = dccdRepo.findByuinNo(uinNumber);
				UinMaster uinMaster = uinMasterRepository.getActiveByUIN(uinNumber);

				if (uinMaster.getProductType().contains("N")) {
					addNonUlipDeathClaim(uinNumber, userId);
				} else if (uinMaster.getProductType().contains("L")) {
					addUlipDeathClaim(uinNumber, userId);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

		});

		return errorService.getErrorById("ER001");
	}

	public String getNextDate(String date) throws ParseException {
		Date d1 = new SimpleDateFormat("yyyyMMdd").parse(date);
		LocalDate nextDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusYears(1);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String next = nextDate1.format(formatter);
		return next;
	}

	public String getNextYearlyDue(String startDate) {

		LocalDate nextDate1 = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusYears(1);
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String endDate = nextDate1.format(formatter1);
		return endDate;
	}

	public Long getDiffInMonth(String startDate, String endDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		Date startDate1 = inputFormat.parse(startDate);
		Date endDate1 = inputFormat.parse(endDate);
		String StartDate = sdf.format(startDate1);
		String EndDate = sdf.format(endDate1);

		Long month = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(StartDate)),
				YearMonth.from(LocalDate.parse(EndDate)));

		return month;
	}

	public Long getDiffInDays(String endDate, String startDate) {

		long daysDiff = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date EndDate;
		try {
			EndDate = sdf.parse(endDate);
			Date StartDate = sdf.parse(startDate);
			long timeDiff = Math.abs(EndDate.getTime() - StartDate.getTime());
			daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return daysDiff;
	}

	public Double getRoundedDoubleValue(Double value) {
		BigDecimal years = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
		Double roundedValue = years.doubleValue();

		return roundedValue;
	}

	public Double getRoundedDoubleValueFifthDecimal(Double value) {
		BigDecimal years = new BigDecimal(value).setScale(5, RoundingMode.HALF_UP);
		Double roundedValue = years.doubleValue();

		return roundedValue;
	}

	public Double getCeilDoubleValue(Double value) {
		BigDecimal years = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
		Double roundedValue = Math.ceil(years.doubleValue());
		return roundedValue;
	}

	public String getIncreaseYearByOneDay(String date) {
		Date d1;
		String next = "";
		try {
			d1 = new SimpleDateFormat("yyyyMMdd").parse(date);
			LocalDate nextDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			next = nextDate1.format(formatter);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return next;
	}

	public String getIncreaseYearByOneYear(String date) {
		Date d1;
		String next = "";
		try {
			d1 = new SimpleDateFormat("yyyyMMdd").parse(date);
			LocalDate nextDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusYears(1);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			next = nextDate1.format(formatter);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return next;
	}

	public String getDecreaseYearByOneDay(String date) {
		Date d1;
		String next = "";
		try {
			d1 = new SimpleDateFormat("yyyyMMdd").parse(date);
			LocalDate nextDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(1);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			next = nextDate1.format(formatter);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return next;
	}

	public String getIncreaseDateByOneMonth(String startDate) throws ParseException {
		Date d1;
		String next = "";
		d1 = new SimpleDateFormat("yyyyMMdd").parse(startDate);
		LocalDate nextDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusMonths(1);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		next = nextDate1.format(formatter);

		return next;
	}

	public LocalDate getDateFormat(String date) throws ParseException {

		Date d1 = new SimpleDateFormat("yyyyMMdd").parse(date);
		LocalDate nextDate1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return nextDate1;
	}

	public Double getDiffInYear(String endDate, String startDate) throws ParseException {

		System.out.println("Inside diff in year");

		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		Date StartDate = inputFormat.parse(startDate);
		Date EndDate = inputFormat.parse(endDate);

		long diffInTime = EndDate.getTime() - StartDate.getTime();

		double diffInYears = TimeUnit.MILLISECONDS.toDays(diffInTime) / 365f;

		BigDecimal years = new BigDecimal(diffInYears).setScale(2, RoundingMode.HALF_UP);

		Double ceilYear = Math.ceil(years.doubleValue());
		return ceilYear;
	}

	public Double getALB(String endDate, String startDate) throws ParseException {

		System.out.println("Inside diff in year");

		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		Date StartDate = inputFormat.parse(startDate);
		Date EndDate = inputFormat.parse(endDate);

		long diffInTime = EndDate.getTime() - StartDate.getTime();

		double diffInYears = TimeUnit.MILLISECONDS.toDays(diffInTime) / 365f;

		Double Year = Math.floor(diffInYears);
		return Year;
	}

	public Double getValue(String uinNumber) throws NumberFormatException, ParseException {

		DeathClaimCoverDetails dccd = dccdRepo.findByuinNo(uinNumber);
		DeathClaimPolicyDetails dcpd = dcpdRepo.getActiveByPolicyNo(dccd.getPolicyNo());
		DeathClaimTransactionPas dctp = dctpRepo.findByUinNumber(uinNumber);
		DeathClaimClientDetails deathclaimClient = deathClaimClientRepo.getActiveByClientNo(dcpd.getClntNum());
		UinMaster uinMaster = uinMasterRepository.getActiveByUIN(uinNumber);
		Integer billFreq = Integer.parseInt(dcpd.getBillFreq());

		// Double premiumRecovery =
		// getPremiumRecovery(dctp.getDateOfDeath(),dcpd.getFup(),billFreq,dccd.getCoverPremium());

//		Double premiumPaid = getPremiumPaid(dccd.getDocDate(), dcpd.getFup(), billFreq, dccd.getCoverPremium());

//		Double paidUpValue = getPaidUpValue(dccd.getDocDate(), dcpd.getFup(), dccd.getPremiumTerm(), billFreq,
//				dccd.getSumAssured());

//		Double revAmount = getRevAmount(dcpd.getFup(), dccd.getDocDate(), dccd.getPlanName(), dccd.getPlanCode(),
//				uinNumber, dccd.getSumAssured(), dccd.getPremiumTerm(), billFreq, dccd.getCoverStatus(),
//				uinMaster.getRevesionaryBonus());

//		Double interimAmount = getInterimAmount(dcpd.getFup(), dccd.getDocDate(), dccd.getPlanName(),
//				dccd.getPlanCode(), uinNumber, dccd.getSumAssured(), dccd.getPremiumTerm(), billFreq,
//				dccd.getCoverStatus(), uinMaster.getInterimBonus());

//		Double guaranteedAmount = getGuaranteedAmount(dcpd.getFup(), dccd.getDocDate(), dccd.getPlanName(),
//				dccd.getPlanCode(), uinNumber, dccd.getSumAssured(), dccd.getPremiumTerm(), billFreq,
//				dccd.getCoverStatus(), uinMaster.getGuaranteedBonus());

//		Double loyaltyAmount = getLoyalAmount(dcpd.getFup(), dccd.getDocDate(), dccd.getPlanName(), dccd.getPlanCode(),
//				uinNumber, dccd.getSumAssured(), dccd.getPremiumTerm(), billFreq, dccd.getCoverStatus(),
//				uinMaster.getLoyaltyBonus());

//		Double termialAmount = getTermAmount(dcpd.getFup(), dccd.getDocDate(), dccd.getPlanName(), dccd.getPlanCode(),
//				uinNumber, dccd.getSumAssured(), dccd.getPremiumTerm(), billFreq, dccd.getCoverStatus(),
//				uinMaster.getTerminalBonus());

//
//		Double totalBonus = getTotalBonus(revAmount, interimAmount, guaranteedAmount, loyaltyAmount, termialAmount);
//

//		
//		Double basicDeathAmount = getBasicDeathAmount(dccd.getDocDate(), dcpd.getFup(), dccd.getPremiumTerm(), billFreq,
//				dccd.getSumAssured(), dccd.getCoverStatus());

//		Double deathAmount = getDeathAmount(dccd.getDocDate(), dcpd.getFup(), dccd.getPremiumTerm(), billFreq,
//				dccd.getSumAssured(), dccd.getCoverStatus(), dctp.getCauseOfDeath(), uinNumber, dctp.getDateOfDeath());

//		Double basicSumAssured = basicDeathAmount;
//		Double additionalSumAssured = getAdditionalSumAssured(dccd.getDocDate(), dcpd.getFup(), dccd.getSumAssured(),
//				dctp.getCauseOfDeath(), uinNumber, dctp.getDateOfDeath());

//		Double interestOnPrem = getInterestOnPremium(dcpd.getFup(), dctp.getDateOfDeath(), uinNumber,
//				dccd.getCoverPremium(), billFreq);

//		Double gstOnPrem = getGstOnpremium(dccd.getRiskComDate(), dcpd.getFup(), billFreq, dccd.getCoverPremium(),
//				dctp.getDateOfDeath(), dccd.getPlanCode());

//		Double annuityRefund = getAnnuityRefund(dctp.getAnnuityStartDate(), dctp.getDateOfDeath(),
//				dctp.getYearlyAnnuityAmunt(), dctp.getAnnuityGuranteedYears(), dccd.getPlanCode());

//		Double annuityPaid = getAnnuityPaid(dctp.getAnnuityStartDate(), dctp.getDateOfDeath(),
//				dctp.getYearlyAnnuityAmunt());

//		Double survivalBenifits = getSurvivalBenifits(dccd.getRiskComDate(), dcpd.getFup(), dctp.getDateOfDeath(),
//				dccd.getSumAssured(), dccd.getPlanCode());

//		Double payble = getAllPayble(basicDeathAmount, additionalSumAssured, revAmount, interimAmount, termialAmount,
//				guaranteedAmount, loyaltyAmount, annuityRefund);
//		Double recoverable = getAllRecoverable(premiumPaid, interestOnPrem, gstOnPrem, survivalBenifits);

		Double accumFundValue = getAccumilatedFundValue(uinNumber, dctp.getEffectiveDate());
		Double deathBenefitAmount = getUlipDeathBenefitAmount(dctp.getDeathBenefitType(), accumFundValue,
				dccd.getSumAssured());
		Double SarOrSA = getSARorSA(dctp.getDeathBenefitType(), accumFundValue, dccd.getSumAssured());
		Double ulipMortRefund = getMortalityChargesRefund(dccd.getRiskComDate(), dctp.getDateOfDeath(),
				deathclaimClient.getLaDob(), dctp.getEffectiveDate(), uinNumber, SarOrSA, dccd.getPremiumTerm());

		return ulipMortRefund;
	}

}
