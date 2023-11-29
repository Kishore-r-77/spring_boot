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
import com.futura.Purple.Entity.PolicyDetailsPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Repository.PolicyDetailsPasRepository;
import com.futura.Purple.Service.PolicyDetailsPasService;

@RestController
@RequestMapping("/policyDetailsPas")
public class PolicyDetailsPasController {

	@Autowired
	private PolicyDetailsPasService policydetailspasService;

	@Autowired
	private PolicyDetailsPasRepository policyDetailsPasRepository;

	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 8;

	@GetMapping("/getAll/{userId}")
	public ResponseEntity<?> viewAll(@PathVariable Long userId) {
		String method = "get-all-policydetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailspasService.getAll());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailspasService.getAll());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@GetMapping("/getAllByClient/{clntNum}")
	public ResponseEntity<List<?>> viewAllByClient(@PathVariable Long clntNum) {

		return ResponseEntity.ok(policydetailspasService.getAllByClientNo(clntNum));

	}

	@GetMapping("/getByPolicyNo/{policyNo}")
	public ResponseEntity<?> getByPolicyNo(@PathVariable Long policyNo) {

		return ResponseEntity.ok(policydetailspasService.getByPolicyNo(policyNo));

	}

	@GetMapping("/{id}/{userId}")
	public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

		String method = "get-policydetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailspasService.getById(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailspasService.getById(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<?> add(@RequestBody PolicyDetailsPas entity, @PathVariable Long userId) {

		String method = "add-policydetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();
		Long val = policyDetailsPasRepository.existsByChdrNum(entity.getChdrNum());
		if (val > 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Policy Number already exists"));
		} else {

			if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(policydetailspasService.add(entity));
			} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(policydetailspasService.add(entity));
			} else {
				return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
			}
		}

	}

	@PatchMapping("/update/{id}/{userId}")
	public ResponseEntity<?> update(@RequestBody PolicyDetailsPas entity, @PathVariable Long id,
			@PathVariable Long userId) {

		String method = "update-policydetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailspasService.update(id, entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailspasService.update(id, entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@GetMapping("/search/{val}")
	public List<PolicyDetailsPas> search(@PathVariable String val) {
		return policydetailspasService.search(val);
	}

	@DeleteMapping("/hardDelete/{id}/{userId}")
	public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "hard-delete-policydetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailspasService.hardDelete(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailspasService.hardDelete(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PatchMapping("/softdelete/{id}/{userId}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "soft-delete-policydetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailspasService.deactivate(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(policydetailspasService.deactivate(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

}
