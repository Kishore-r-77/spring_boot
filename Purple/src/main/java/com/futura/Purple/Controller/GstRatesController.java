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
import com.futura.Purple.Entity.GstRates;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Service.GstRatesService;

@RestController
@RequestMapping("/gstRates")
public class GstRatesController {

	@Autowired
	private GstRatesService gstRatesService;

	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 45;

	@GetMapping("/getall/{userId}")
	public ResponseEntity<?> viewAll(@PathVariable Long userId) {
		String method = "get-all-gstRates";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.getAll());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.getAll());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@GetMapping("/{id}/{userId}")
	public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

		String method = "get-gstRates";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.getById(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.getById(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<?> add(@RequestBody GstRates entity, @PathVariable Long userId) {

		String method = "add-gstRates";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.add(entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.add(entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@PatchMapping("/update/{id}/{userId}")
	public ResponseEntity<?> update(@RequestBody GstRates entity, @PathVariable Long id, @PathVariable Long userId) {

		String method = "update-gstRates";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.update(id, entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.update(id, entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@GetMapping("/search/{val}")
	public List<GstRates> search(@PathVariable String val) {
		return gstRatesService.search(val);
	}

	@DeleteMapping("/hardDelete/{id}/{userId}")
	public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "hard-delete-gstRates";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.hardDelete(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.hardDelete(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@PatchMapping("/softdelete/{id}/{userId}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "soft-delete-gstRates";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.deactivate(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(gstRatesService.deactivate(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}
}
