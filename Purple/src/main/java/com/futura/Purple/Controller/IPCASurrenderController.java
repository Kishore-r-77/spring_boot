package com.futura.Purple.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futura.Purple.Auth.repository.UserRepository;
import com.futura.Purple.Entity.IPCASurrender;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Service.IPCASurrenderService;

@RestController
@RequestMapping("/ipcaSurrender")
public class IPCASurrenderController {
	
	@Autowired
	private IPCASurrenderService ipcaSurrenderService;

	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 23;
	
	@GetMapping("/getAll/{userId}")
	public ResponseEntity<?> viewAll(@PathVariable Long userId) {
		String method = "get-all-ipcaSurrender";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.getAll());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.getAll());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}
	
	@GetMapping("/getAllQCPending/{userId}")
	public ResponseEntity<?> getAllQCPending(@PathVariable Long userId) {
		String method = "get-all-ipcaSurrender";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.getAllQCPending());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.getAllQCPending());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}
	
	@GetMapping("/getAllFailed/{userId}")
	public ResponseEntity<?> viewAllfailed(@PathVariable Long userId) {
		String method = "get-all-ipcaSurrender";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.getAllFailed());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.getAllFailed());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
	
	@GetMapping("/getAllPassed/{userId}")
	public ResponseEntity<?> viewAllPassed(@PathVariable Long userId) {
		String method = "get-all-ipcaSurrender";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.getAllPassed());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.getAllPassed());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
	@GetMapping("/{id}/{userId}")
	public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

		String method = "get-ipcaSurrender";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.getById(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.getById(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
	@PatchMapping("/update/{id}/{userId}")
	public ResponseEntity<?> update(@RequestBody IPCASurrender entity, @PathVariable Long id,
			@PathVariable Long userId) {

		String method = "update-ipcaSurrender";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.update(id, entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.update(id, entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
	@PatchMapping("/softdelete/{id}/{userId}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "soft-delete-ipcaSurrender";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.deactivate(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(ipcaSurrenderService.deactivate(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
	@GetMapping("/getByPolicyNo/{policyNo}")
	public ResponseEntity<IPCASurrender> getByPolicyNo(@PathVariable Long policyNo){
		return new ResponseEntity<>(ipcaSurrenderService.getByPolicyNo(policyNo),HttpStatus.OK);
	}
	
	@GetMapping("/calculateBonusVal/{policyNo}")
	public Double getBonusValue(@PathVariable Long policyNo) throws ParseException {
		return ipcaSurrenderService.calculateBonusValue(policyNo);
	}
	
	@GetMapping("/calculateTotalPremiumPaid/{policyNo}")
	public Double calculateTotalPremiumPaid(@PathVariable Long policyNo) throws ParseException {
		return ipcaSurrenderService.calculateTotalPremiumPaid(policyNo);
	}

	@GetMapping("/calculateGrossGsv/{policyNo}")
	public Double calculateGrossGsv(@PathVariable Long policyNo) throws ParseException {
		return ipcaSurrenderService.calculateGSV(policyNo);
	}
	
	@GetMapping("/calculateGrossSsv/{policyNo}")
	public Double calculateGrossSsv(@PathVariable Long policyNo) throws ParseException {
		return ipcaSurrenderService.calculateSsv(policyNo);
	}
	
	@GetMapping("/getGsvCashValue/{policyNo}")
	public ResponseEntity<?> getGsvCashValue(@PathVariable Long policyNo) throws ParseException{
		return ResponseEntity.ok(ipcaSurrenderService.calculateGsvCashValue(policyNo));
	}
	
	@GetMapping("/getBonusRate/{docDate}/{planCode}/{planName}/{uinNumber}")
	public Double getBonusRate(@PathVariable String docDate,@PathVariable String planCode,@PathVariable String planName, @PathVariable String uinNumber ) {
		return ipcaSurrenderService.getBonusRate(docDate, planCode,planName,uinNumber);
	}
	
	@PostMapping(value = "/assignMultipleTrans/{userId}")
    public ResponseEntity<?> assignMultipleTrans(@RequestBody List<Long> PolicyNums, @PathVariable Long userId) {
        return ResponseEntity.ok(ipcaSurrenderService.assignMultipleTrans(PolicyNums ,userId));
    }
	
	@PostMapping(value = "/assignSingleTrans/{policyNo}/{userId}")
    public ResponseEntity<?> assignSingleTrans(@PathVariable Long policyNo, @PathVariable Long userId) {
        return ResponseEntity.ok(ipcaSurrenderService.assignSingleTrans(policyNo, userId));
    }
	
	@PatchMapping("/qcUpdate/{policyNo}/{userId}")
    public ResponseEntity<String> qcUpdate(@RequestBody IPCASurrender entity,@PathVariable Long policyNo,@PathVariable Long userId){
    	return new ResponseEntity<String>(ipcaSurrenderService.qcUpdate(entity ,policyNo,userId),HttpStatus.OK);
    }
	
	@PatchMapping("/reinitiateTrans/{policyNo}")
	public ResponseEntity<?> reinitiateTrans(@PathVariable Long policyNo) throws ParseException{
		return ResponseEntity.ok(ipcaSurrenderService.reInitiatedTrans(policyNo));
	}
}
