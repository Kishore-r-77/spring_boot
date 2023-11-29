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
import com.futura.Purple.Entity.SurrenderPolicyDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Repository.SurrenderPolicyDetailsRepository;
import com.futura.Purple.Service.SurrenderPolicyDetailsService;

@RestController
@RequestMapping("/surrenderpolicydetails")
public class SurrenderPolicyDetailsController {
	
	@Autowired
	private SurrenderPolicyDetailsService surrenderpolicydetailsService;
	
	@Autowired
	private SurrenderPolicyDetailsRepository surrenderPolicyDetailsRepo;
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 20;
	
	@GetMapping("/getall/{userId}")
	public ResponseEntity<?> viewAll(@PathVariable Long userId) {
		String method = "get-all-surrenderpolicydetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderpolicydetailsService.getAll());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderpolicydetailsService.getAll());
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

		return ResponseEntity.ok(surrenderpolicydetailsService.getByPolicyNo(policyNo));
	}

	@GetMapping("/{id}/{userId}")
	public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

		String method = "get-surrenderpolicydetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderpolicydetailsService.getById(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderpolicydetailsService.getById(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<?> add(@RequestBody SurrenderPolicyDetails entity, @PathVariable Long userId) {

		String method = "add-surrenderpolicydetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();
		Long val = surrenderPolicyDetailsRepo.existsByChdrNum(entity.getChdrNum());
		if (val > 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Policy Number already exists"));
		} else {

			if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(surrenderpolicydetailsService.add(entity));
			} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(surrenderpolicydetailsService.add(entity));
			} else {
				return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
			}
		}

	}

	@PatchMapping("/update/{id}/{userId}")
	public ResponseEntity<?> update(@RequestBody SurrenderPolicyDetails entity, @PathVariable Long id,
			@PathVariable Long userId) {

		String method = "update-surrenderpolicydetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderpolicydetailsService.update(id, entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderpolicydetailsService.update(id, entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@GetMapping("/search/{val}")
	public List<SurrenderPolicyDetails> search(@PathVariable String val) {
		return surrenderpolicydetailsService.search(val);
	}

	@DeleteMapping("/hardDelete/{id}/{userId}")
	public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "hard-delete-surrenderpolicydetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderpolicydetailsService.hardDelete(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderpolicydetailsService.hardDelete(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PatchMapping("/softdelete/{id}/{userId}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "soft-delete-surrenderpolicydetails";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderpolicydetailsService.deactivate(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderpolicydetailsService.deactivate(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

}
