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
import com.futura.Purple.Entity.SurrenderTransactionPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Repository.SurrenderTransactionPasRepository;
import com.futura.Purple.Service.SurrenderTransactionPasService;

@RestController
@RequestMapping("/surrenderTransactionPas")
public class SurrenderPasTransactionController {
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;
	
	@Autowired
	private SurrenderTransactionPasService surrenderTransactionPasService;
	
	@Autowired
	private SurrenderTransactionPasRepository surrenderTransactionPasRepository;
	
	
	long serviceId = 22;

	@GetMapping("/getAll/{userId}")
	public ResponseEntity<?> viewAll(@PathVariable Long userId) {
		String method = "get-all-surrenderTransactionPas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderTransactionPasService.getAll());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderTransactionPasService.getAll());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@GetMapping("/getAllPOlicyNo")
	public ResponseEntity<List<?>> getAllPolicyNo() {
		return ResponseEntity.ok(surrenderTransactionPasService.getAllPolicyNo());
	}

	@GetMapping("/getByPolicyNo/{policyNo}")
	public SurrenderTransactionPas findByPolicyNo(@PathVariable Long policyNo) {
		return surrenderTransactionPasService.findByPolicyNo(policyNo);
	}

	@GetMapping("/getProcessedByPolicyNo/{policyNo}")
	public SurrenderTransactionPas findProcessedByPolicyNo(@PathVariable Long policyNo) {
		return surrenderTransactionPasService.findProcessedByPolicy(policyNo);
	}

	@GetMapping("/{id}/{userId}")
	public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

		String method = "get-surrenderTransactionPas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderTransactionPasService.getById(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderTransactionPasService.getById(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<?> add(@RequestBody SurrenderTransactionPas entity, @PathVariable Long userId) {

		String method = "add-surrenderTransactionPas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		Long val =surrenderTransactionPasRepository.existsByTransNo(entity.getTransNo());
		if (val>0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Client Number already exists"));
		} else {

			if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(surrenderTransactionPasService.add(entity));
			} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(surrenderTransactionPasService.add(entity));
			} else {
				return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
			}

		}

	}

	@PatchMapping("/update/{id}/{userId}")
	public ResponseEntity<?> update(@RequestBody SurrenderTransactionPas entity, @PathVariable Long id,
			@PathVariable Long userId) {

		String method = "update-surrenderTransactionPas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderTransactionPasService.update(id, entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderTransactionPasService.update(id, entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@GetMapping("/search/{val}")
	public List<SurrenderTransactionPas> search(@PathVariable String val) {
		return surrenderTransactionPasService.search(val);
	}

	@DeleteMapping("/hardDelete/{id}/{userId}")
	public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "hard-delete-surrenderTransactionPas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderTransactionPasService.hardDelete(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderTransactionPasService.hardDelete(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PatchMapping("/softdelete/{id}/{userId}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "soft-delete-surrenderTransactionPas";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderTransactionPasService.deactivate(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(surrenderTransactionPasService.deactivate(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}
	
}
