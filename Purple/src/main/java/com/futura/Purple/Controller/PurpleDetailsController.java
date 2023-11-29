package com.futura.Purple.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futura.Purple.Auth.repository.UserRepository;
import com.futura.Purple.Entity.Company;
import com.futura.Purple.Entity.CoverDetailsPas;
import com.futura.Purple.Entity.FundDetailsPas;
import com.futura.Purple.Entity.PolicyDetailsPas;
import com.futura.Purple.Entity.PurpleDetails;
import com.futura.Purple.Entity.TransactionPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.ExcelGenerator;
import com.futura.Purple.Pdf.PdfGeneration;
import com.futura.Purple.Repository.CompanyRepository;
import com.futura.Purple.Repository.CoverDetailsPasRepository;
import com.futura.Purple.Repository.FundDetailsPasRepository;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Repository.PolicyDetailsPasRepository;
import com.futura.Purple.Repository.PurpleDetailsRepository;
import com.futura.Purple.Repository.TransactionPasRepository;
import com.futura.Purple.Service.PurpleDetailsService;


@RestController
@RequestMapping("/purpledetails")
public class PurpleDetailsController {

	@Autowired
	private PurpleDetailsService purpledetailsService;

	@Autowired
	private PermissionRepository permissionRepo;
	
	@Autowired
	private PurpleDetailsRepository purpledetailsRepo;

	@Autowired
	private PolicyDetailsPasRepository policyDetailsPasRepository;

	@Autowired
	private TransactionPasRepository transactionPasRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CoverDetailsPasRepository coverRepository;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FundDetailsPasRepository fundDetailsPasRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 11;

	@GetMapping("/getAllQCPending/{userId}")
	public ResponseEntity<?> viewAllQCPending(@PathVariable Long userId) {
		String method = "get-all-purpledetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.getAllQCPending());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.getAllQCPending());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
	@GetMapping("/getAllFailed/{userId}")
	public ResponseEntity<?> viewAllfailed(@PathVariable Long userId) {
		String method = "get-all-purpledetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.getAllFailed());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.getAllFailed());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
	
	@GetMapping("/getAllPassed/{userId}")
	public ResponseEntity<?> viewAllPassed(@PathVariable Long userId) {
		String method = "get-all-purpledetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.getAllPassed());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.getAllPassed());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
	
	@GetMapping("/getByPolicyNo/{val}")
    public PurpleDetails getBypolicyNo(@PathVariable Long val) {
        return purpledetailsService.getByPolicyNo(val);
    }
	
	@GetMapping("/getByTransNo/{val}")
    public PurpleDetails getByTransNo(@PathVariable Long val) {
        return purpledetailsService.getByTransNo(val);
    }

	@GetMapping("/{id}/{userId}")
	public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

		String method = "get-purpledetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.getById(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.getById(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
//	@PostMapping("/add/{policyNo}/{userId}")
//	public ResponseEntity<?> add(@RequestBody PurpleDetails entity, @PathVariable Long policyNo, @PathVariable Long userId) throws ParseException {
//
//		String method = "add-purpledetails";
//		long userGroupId = userRepo.getById(userId).getUserGroupId();
//
//		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
//			return ResponseEntity.ok(purpledetailsService.add(entity,policyNo));
//		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
//			return ResponseEntity.ok(purpledetailsService.add(entity,policyNo));
//		} else {
//			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
//		}
//
//	}
	
	@PatchMapping("/qcUpdate/{policyNo}/{userId}")
    public ResponseEntity<String> qcUpdate(@RequestBody PurpleDetails entity,@PathVariable Long policyNo,@PathVariable Long userId){
    	return new ResponseEntity<String>(purpledetailsService.qcUpdate(entity ,policyNo,userId),HttpStatus.OK);
    }

	@PatchMapping("/update/{id}/{userId}")
	public ResponseEntity<?> update(@RequestBody PurpleDetails entity, @PathVariable Long id,
			@PathVariable Long userId) {

		String method = "update-purpledetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.update(id, entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.update(id, entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@GetMapping("/search/{val}")
	public List<PurpleDetails> search(@PathVariable String val) {
		return purpledetailsService.search(val);
	}



	@DeleteMapping("/hardDelete/{id}/{userId}")
	public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "hard-delete-purpledetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.hardDelete(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.hardDelete(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PatchMapping("/softdelete/{id}/{userId}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "soft-delete-purpledetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.deactivate(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpledetailsService.deactivate(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
	
	@GetMapping("/calculateTotalPremium/{val}")
	public Long calculate(@PathVariable Long val) throws ParseException {
		return purpledetailsService.calculateTotalPremium(val);

	}
	
//	@GetMapping("/calculatePenalIntrest")
//	public Long calculate(@PathVariable String approv , @PathVariable String req , @PathVariable Long netPay) throws ParseException {
////		String approv ="20221021";
////		String req ="20220821";
////		long val =8476;
//		return purpledetailsService.;
//	}

	@GetMapping("/calculateMortalityRate/{val}")
    public Double mortalityCharges(@PathVariable Long val) throws ParseException {
        return purpledetailsService.calculateUlipMortalityRates(val);
    }
	
	@GetMapping("/calculateFundDetails/{val}")
    public Double CalculateFundDetails(@PathVariable Long val) throws ParseException {
        return purpledetailsService.calculateFundValue(val);
    }
	
	@GetMapping("/calculateNonUlipMortalityRate/{val}")
    public Double NonUliportalityCharges(@PathVariable Long val) throws ParseException {
        return purpledetailsService.calculateNonUlipMortalityRates(val);
    }
	
	@GetMapping("/calculateMFRate/{val}")
    public Long calculateMF(@PathVariable Long val) {
        return purpledetailsService.calculateMFRate(val);
    }
	
	@GetMapping("/calculateStamDuty/{policyNo}")
    public ResponseEntity<?> calculateStamp(@PathVariable Long policyNo){
    	return ResponseEntity.ok(purpledetailsService.calculateStampDuty(policyNo));
    }
	
	@GetMapping("/calculateFundValue/{transNo}")
    public ResponseEntity<?> calculateFundValue(@PathVariable Long transNo){
    	return ResponseEntity.ok(purpledetailsService.calculateFundValue(transNo));
    }
	
	@PatchMapping("/reinitiateTrans/{policyNo}")
    public ResponseEntity<?> reinitiateTrans(@PathVariable Long policyNo){
    	return ResponseEntity.ok(purpledetailsService.reIniiatedTrans(policyNo));
    }
	
	
	
	
	 @PostMapping(value = "/assignMultipleTrans/{userId}")
	    public ResponseEntity<?> assignMultipleTrans(@RequestBody List<Long> PolicyNums, @PathVariable Long userId) {
	        return ResponseEntity.ok(purpledetailsService.assignMultipleTrans(PolicyNums ,userId));
	    }
	
	 @GetMapping(value = "/generatePdf/{policyId}", produces = MediaType.APPLICATION_PDF_VALUE)
		public ResponseEntity<?> pdfgeneration(@PathVariable Long policyId) throws IOException, ParseException{
			
			PolicyDetailsPas policy = policyDetailsPasRepository.getActiveByPolicyNo(policyId);
			
			TransactionPas trans = transactionPasRepository.findCompletedByPolicyNo(policyId);
			
			PurpleDetails purple = purpledetailsRepo.getByTransNo(trans.getFlcTransNo());

			Company company = companyRepository.getActiveById(policy.getCompanyId());
			
			if(policy.getUinNumber().contains("N"))
			{
				List<CoverDetailsPas> covers = coverRepository.getAllByPolicyNo(policyId);

				String s1 = company.getCompanyLogo().split(",")[1];

				byte[] image = javax.xml.bind.DatatypeConverter.parseBase64Binary(s1);

				BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(image));

				String companyName = company.getCompanyLongName();

				ByteArrayInputStream bis = PdfGeneration.nonUlip(policy,trans,covers,purple,companyName, image1);
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "inline; filename=purple.pdf");
				return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
						.body(new InputStreamResource(bis));
			}
			else {
				List<CoverDetailsPas> covers = coverRepository.getAllByPolicyNo(policyId);
				List<FundDetailsPas> funds = fundDetailsPasRepo.getallByPolicy(policyId);

				String s1 = company.getCompanyLogo().split(",")[1];

				byte[] image = javax.xml.bind.DatatypeConverter.parseBase64Binary(s1);

				BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(image));

				String companyName = company.getCompanyLongName();

				ByteArrayInputStream bis = PdfGeneration.Ulip(policy,trans,covers,funds,purple,companyName, image1);
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "inline; filename=purple.pdf");
				return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
						.body(new InputStreamResource(bis));
			}
	    }
	 
	 @GetMapping("download/purple.xlsx/{state}")
		public ResponseEntity<InputStreamResource> excelPurpleReport(@PathVariable String state) throws IOException, ParseException
		{
			if (state.equalsIgnoreCase("Passed"))
			{
				List<PurpleDetails> list = (List<PurpleDetails>)purpledetailsRepo.getAllPassedRecord();
				
				ByteArrayInputStream in = ExcelGenerator.purpleToExcel(list);
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "attachment; filename=Purple_passed.xlsx");
				return ResponseEntity
						.ok()
						.headers(headers)
						.body(new InputStreamResource(in));
			}
			else if (state.equalsIgnoreCase("Failed"))
			{
				List<PurpleDetails> list = (List<PurpleDetails>)purpledetailsRepo.getAllFailedRecord();
				
				ByteArrayInputStream in = ExcelGenerator.purpleToExcel(list);
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "attachment; filename=Purple_failed.xlsx");
				return ResponseEntity
						.ok()
						.headers(headers)
						.body(new InputStreamResource(in));
			}
			else if (state.equalsIgnoreCase("Processed"))
			{
				List<PurpleDetails> list = (List<PurpleDetails>)purpledetailsRepo.getAllQCPending();
				
				ByteArrayInputStream in = ExcelGenerator.purpleToExcel(list);
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "attachment; filename=Purple_processed.xlsx");
				return ResponseEntity
						.ok()
						.headers(headers)
						.body(new InputStreamResource(in));
			}
			else if (state.equalsIgnoreCase("Initiated"))
			{
				List<TransactionPas> list = (List<TransactionPas>)transactionPasRepository.getallActive();
				
				ByteArrayInputStream in = ExcelGenerator.transactionToExcel(list);
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "attachment; filename=flc_initiated.xlsx");
				return ResponseEntity
						.ok()
						.headers(headers)
						.body(new InputStreamResource(in));
			}
			else
			{
				List<PurpleDetails> list = (List<PurpleDetails>)purpledetailsRepo.getAllQCPending();
				
				ByteArrayInputStream in = ExcelGenerator.purpleToExcel(list);
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "attachment; filename=Purple.xlsx");
				return ResponseEntity
						.ok()
						.headers(headers)
						.body(new InputStreamResource(in));
			}
		}
	
	


}
