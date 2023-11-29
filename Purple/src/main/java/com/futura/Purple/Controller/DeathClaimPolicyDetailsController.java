package com.futura.Purple.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futura.Purple.Auth.payload.response.MessageResponse;
import com.futura.Purple.Auth.repository.UserRepository;
import com.futura.Purple.Entity.DeathClaimPolicyDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimPolicyDetailsRepository;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Service.DeathClaimPolicyDetailsService;

@RestController
@RequestMapping("/deathClaimPolicyDetails")
public class DeathClaimPolicyDetailsController {
	
	@Autowired
	private DeathClaimPolicyDetailsService policydetailsService;
	
	@Autowired
	private DeathClaimPolicyDetailsRepository policyDetailsRepo;
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 32;
	
	@GetMapping("/getall/{userId}")
	public ResponseEntity<?> viewAll(@PathVariable Long userId) {
		String method = "get-all-deathClaimpolicyDetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailsService.getAll());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailsService.getAll());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

//	@GetMapping("/getAllByClient/{clntNum}")
//	public ResponseEntity<List<?>> viewAllByClient(@PathVariable Long clntNum) {
//
//		return ResponseEntity.ok(surrenderpolicydetailsService.getAllByClientNo(clntNum));
//
//	}
//
	@GetMapping("/getByPolicyNo/{policyNo}")
	public ResponseEntity<?> getByPolicyNo(@PathVariable Long policyNo) {

		return ResponseEntity.ok(policydetailsService.getByPolicyNo(policyNo));
	}

	@GetMapping("/{id}/{userId}")
	public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

		String method = "get-deathClaimpolicyDetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailsService.getById(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailsService.getById(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<?> add(@RequestBody DeathClaimPolicyDetails entity, @PathVariable Long userId) {

		String method = "add-deathClaimpolicyDetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();
		Long val = policyDetailsRepo.existsByChdrNum(entity.getChdrNum());
		if (val > 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Policy Number already exists"));
		} else {

			if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(policydetailsService.add(entity));
			} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(policydetailsService.add(entity));
			} else {
				return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
			}
		}

	}

	@PatchMapping("/update/{id}/{userId}")
	public ResponseEntity<?> update(@RequestBody DeathClaimPolicyDetails entity, @PathVariable Long id,
			@PathVariable Long userId) {

		String method = "update-deathClaimpolicyDetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailsService.update(id, entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailsService.update(id, entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@GetMapping("/search/{val}")
	public List<DeathClaimPolicyDetails> search(@PathVariable String val) {
		return policydetailsService.search(val);
	}

	@DeleteMapping("/hardDelete/{id}/{userId}")
	public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "hard-delete-deathClaimpolicyDetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailsService.hardDelete(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailsService.hardDelete(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PatchMapping("/softdelete/{id}/{userId}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "soft-delete-deathClaimpolicyDetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailsService.deactivate(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailsService.deactivate(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

}
