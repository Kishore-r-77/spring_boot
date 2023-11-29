package com.futura.Purple.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Auth.repository.UserRepository;
import com.futura.Purple.Entity.GuaranteedBonusRate;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Excel.Helper;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Service.GuaranteedBonusRateService;

@RestController
@RequestMapping("/guaranteedBonusRate")
@CrossOrigin
public class GuaranteedBonusRateController {

	@Autowired
	private GuaranteedBonusRateService guaranteedbonusRateService;

	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 41;

	@GetMapping("/getall/{userId}")
	public ResponseEntity<?> viewAll(@PathVariable Long userId) {
		String method = "get-all-GuaranteedBonusRate";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.getAll());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.getAll());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@GetMapping("/{id}/{userId}")
	public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

		String method = "get-GuaranteedBonusRate";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.getById(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.getById(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<?> add(@RequestBody GuaranteedBonusRate entity, @PathVariable Long userId) {

		String method = "add-GuaranteedBonusRate";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.add(entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.add(entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@PatchMapping("/update/{id}/{userId}")
	public ResponseEntity<?> update(@RequestBody GuaranteedBonusRate entity, @PathVariable Long id, @PathVariable Long userId) {

		String method = "update-GuaranteedBonusRate";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.update(id, entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.update(id, entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@GetMapping("/search/{val}")
	public List<GuaranteedBonusRate> search(@PathVariable String val) {
		return guaranteedbonusRateService.search(val);
	}

	@DeleteMapping("/hardDelete/{id}/{userId}")
	public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "hard-delete-GuaranteedBonusRate";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.hardDelete(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.hardDelete(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}

	@PatchMapping("/softdelete/{id}/{userId}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "soft-delete-GuaranteedBonusRate";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.deactivate(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(guaranteedbonusRateService.deactivate(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
		if (Helper.checkExcelFormat(file)) {
			// true

			this.guaranteedbonusRateService.save(file);

			return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	}

}
