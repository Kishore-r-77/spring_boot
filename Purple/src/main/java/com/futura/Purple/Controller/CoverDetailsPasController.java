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

import com.futura.Purple.Auth.repository.UserRepository;
import com.futura.Purple.Entity.CoverDetailsPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Service.CoverDetailsPasService;

@RestController
@RequestMapping("/coverdetailspas")
public class CoverDetailsPasController {

	@Autowired
	private CoverDetailsPasService coverdetailspasService;

	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 9;

	@GetMapping("/getAll/{userId}")
	public ResponseEntity<?> viewAll(@PathVariable Long userId) {
		String method = "get-all-coverdetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.getAll());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.getAll());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@GetMapping("/getByPolicyNo/{chdrNum}")
	public ResponseEntity<List<?>> viewAllByPolicy(@PathVariable Long chdrNum) {
		return ResponseEntity.ok(coverdetailspasService.getAllByPolicy(chdrNum));
	}

	@GetMapping("/calculate/{val}")
	public void mortalityCharges(@PathVariable Long val) {
		coverdetailspasService.calculate(val);
	}

	@GetMapping("/{id}/{userId}")
	public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

		String method = "get-coverdetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.getById(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.getById(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<?> add(@RequestBody CoverDetailsPas entity, @PathVariable Long userId) {

		String method = "add-coverdetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.add(entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.add(entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PatchMapping("/update/{id}/{userId}")
	public ResponseEntity<?> update(@RequestBody CoverDetailsPas entity, @PathVariable Long id,
			@PathVariable Long userId) {

		String method = "update-coverdetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.update(id, entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.update(id, entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@GetMapping("/search/{val}")
	public List<CoverDetailsPas> search(@PathVariable String val) {
		return coverdetailspasService.search(val);
	}

	@DeleteMapping("/hardDelete/{id}/{userId}")
	public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "hard-delete-coverdetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.hardDelete(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.hardDelete(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PatchMapping("/softdelete/{id}/{userId}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "soft-delete-coverdetailspas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.deactivate(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(coverdetailspasService.deactivate(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

}
