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
import com.futura.Purple.Entity.DeathClaimFundDetailsPas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Service.DeathClaimFundDetailsPasService;

@RestController
@RequestMapping("/deathClaimFundDetails")
public class DeathClaimFundDetailsPasController {
	
	@Autowired
	private DeathClaimFundDetailsPasService deathClaimDetailsPasService;
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ErrorService errorService;
	
	long serviceId = 43;
	
	@GetMapping("/getall/{userId}")
    public ResponseEntity<?> viewAll(@PathVariable Long userId) {
        String method = "get-all-deathClaimfundDetailsPas";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
        return ResponseEntity.ok(deathClaimDetailsPasService.getAll());
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimDetailsPasService.getAll());
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@GetMapping("/{id}/{userId}")
    public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

        String method = "get-deathClaimfundDetailsPas";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimDetailsPasService.getById(id));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimDetailsPasService.getById(id));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

	}
	
	@PostMapping("/add/{userId}")
    public ResponseEntity<?> add(@RequestBody DeathClaimFundDetailsPas entity, @PathVariable Long userId) {

        String method = "add-deathClaimfundDetailsPas";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimDetailsPasService.add(entity));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimDetailsPasService.add(entity));
        } else {
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@PatchMapping("/update/{id}/{userId}")
    public ResponseEntity<?> update(@RequestBody DeathClaimFundDetailsPas entity, @PathVariable Long id, @PathVariable Long userId) {

        String method = "update-deathClaimfundDetailsPas";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(deathClaimDetailsPasService.update(id, entity));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimDetailsPasService.update(id, entity));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@GetMapping("/search/{val}")
    public List<DeathClaimFundDetailsPas> search(@PathVariable String val) {
        return deathClaimDetailsPasService.search(val);
    }
	
	@GetMapping("/getAllByPolicy/{policyNo}")
    public List<DeathClaimFundDetailsPas> viewAllByPolicy(@PathVariable Long policyNo) {
        return deathClaimDetailsPasService.getAllByPolicyNo(policyNo);
    }
	
	@DeleteMapping("/hardDelete/{id}/{userId}")
    public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

        String method = "hard-delete-deathClaimfundDetailsPas";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	        return ResponseEntity.ok(deathClaimDetailsPasService.hardDelete(id));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimDetailsPasService.hardDelete(id));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }

        @PatchMapping("/softdelete/{id}/{userId}")
        public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

            String method = "soft-delete-deathClaimfundDetailsPas";
            long userGroupId = userRepo.getById(userId).getUserGroupId();

            if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
                return ResponseEntity.ok(deathClaimDetailsPasService.deactivate(id));
            } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
                return ResponseEntity.ok(deathClaimDetailsPasService.deactivate(id));
            } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
            }

        }

}
