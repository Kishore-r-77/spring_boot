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
import com.futura.Purple.Entity.SurrenderFundDetailsPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Service.SurrenderFundDetailsPasService;

@RestController
@RequestMapping("/surenderFundDetails")
public class SurrenderFundDetailsPasController {
	
	@Autowired
	private SurrenderFundDetailsPasService fundDetailsPasService;
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ErrorService errorService;
	
	long serviceId = 29;
	
	@GetMapping("/getall/{userId}")
    public ResponseEntity<?> viewAll(@PathVariable Long userId) {
        String method = "get-all-surrenderfundDetailsPas";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
        return ResponseEntity.ok(fundDetailsPasService.getAll());
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(fundDetailsPasService.getAll());
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@GetMapping("/{id}/{userId}")
    public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

        String method = "get-surrenderfundDetailsPas";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(fundDetailsPasService.getById(id));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(fundDetailsPasService.getById(id));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

	}
	
	@PostMapping("/add/{userId}")
    public ResponseEntity<?> add(@RequestBody SurrenderFundDetailsPas entity, @PathVariable Long userId) {

        String method = "add-surrenderfundDetailsPas";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(fundDetailsPasService.add(entity));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(fundDetailsPasService.add(entity));
        } else {
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@PatchMapping("/update/{id}/{userId}")
    public ResponseEntity<?> update(@RequestBody SurrenderFundDetailsPas entity, @PathVariable Long id, @PathVariable Long userId) {

        String method = "update-surrenderfundDetailsPas";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(fundDetailsPasService.update(id, entity));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(fundDetailsPasService.update(id, entity));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@GetMapping("/search/{val}")
    public List<SurrenderFundDetailsPas> search(@PathVariable String val) {
        return fundDetailsPasService.search(val);
    }
	
	@GetMapping("/getAllByPolicy/{policyNo}")
    public List<SurrenderFundDetailsPas> viewAllByPolicy(@PathVariable Long policyNo) {
        return fundDetailsPasService.getAllByPolicyNo(policyNo);
    }
	
	@DeleteMapping("/hardDelete/{id}/{userId}")
    public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

        String method = "hard-delete-surrenderfundDetailsPas";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	        return ResponseEntity.ok(fundDetailsPasService.hardDelete(id));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(fundDetailsPasService.hardDelete(id));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }

        @PatchMapping("/softdelete/{id}/{userId}")
        public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

            String method = "soft-delete-surrenderfundDetailsPas";
            long userGroupId = userRepo.getById(userId).getUserGroupId();

            if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
                return ResponseEntity.ok(fundDetailsPasService.deactivate(id));
            } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
                return ResponseEntity.ok(fundDetailsPasService.deactivate(id));
            } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
            }

        }

}
