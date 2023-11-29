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
import com.futura.Purple.Entity.UserGroup;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Repository.UserGroupRepository;
import com.futura.Purple.Service.UserGroupService;

@RestController
@RequestMapping("/userGroup")
public class UserGroupController {

	@Autowired
	private UserGroupService userGroupService;

	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private ErrorService errorService;

	@Autowired
	private UserGroupRepository userGroupRepo;

	@Autowired
	private UserRepository userRepo;

	long serviceId = 2;

	@GetMapping("/getAll/{userId}")
	public ResponseEntity<?> viewAll(@PathVariable Long userId) {

		String method = "get-all-usergroup";
		long userGroupId = userRepo.getById(userId).getUserGroupId();

		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(userGroupService.getall());
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(userGroupService.getall());
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@GetMapping("/getAll")
	public List<UserGroup> getall() {
		return userGroupRepo.findAll();
	}

	@GetMapping("/{id}/{userId}")
	public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

		String method = "get-usergroup";
		long userGroupId = userRepo.getById(userId).getUserGroupId();
		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(userGroupService.getById(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(userGroupService.getById(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<?> add(@RequestBody UserGroup entity, @PathVariable Long userId) {

		String method = "add-usergroup";
		long userGroupId = userRepo.getById(userId).getUserGroupId();
		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {

			return ResponseEntity.ok(userGroupService.add(entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {

			return ResponseEntity.ok(userGroupService.add(entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PatchMapping("/update/{id}/{userId}")
	public ResponseEntity<?> update(@RequestBody UserGroup entity, @PathVariable Long id, @PathVariable Long userId) {

		String method = "update-usergroup";
		long userGroupId = userRepo.getById(userId).getUserGroupId();
		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {

			return ResponseEntity.ok(userGroupService.update(id, entity));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {

			return ResponseEntity.ok(userGroupService.update(id, entity));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@GetMapping("/search/{val}")
	public List<UserGroup> search(@PathVariable String val) {
		return userGroupService.search(val);
	}

	@DeleteMapping("/hardDelete/{id}/{userId}")
	public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "hard-delete-usergroup";
		long userGroupId = userRepo.getById(userId).getUserGroupId();
		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {

			return ResponseEntity.ok(userGroupService.hardDelete(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {

			return ResponseEntity.ok(userGroupService.hardDelete(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@PatchMapping("/softdelete/{id}/{userId}")
	public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

		String method = "soft-delete-usergroup";
		long userGroupId = userRepo.getById(userId).getUserGroupId();
		if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(userGroupService.deactivate(id));
		} else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(userGroupService.deactivate(id));
		} else {
			return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
		}

	}

	@GetMapping("/getByCompanyId/{cmpId}")
	public ResponseEntity<?> getAllUserGroupByCompanyId(@PathVariable Long cmpId) {

		return ResponseEntity.ok(userGroupService.getAllByCmpId(cmpId));

	}

	@GetMapping("/getUserGrpName/{id}")
	public ResponseEntity<?> getUserGrpNameById(@PathVariable Long id) {

		return ResponseEntity.ok(userGroupRepo.getUserNameById(id));

	}

}
