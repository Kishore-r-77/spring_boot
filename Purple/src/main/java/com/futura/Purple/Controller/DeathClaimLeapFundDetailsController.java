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
import com.futura.Purple.Entity.DeathClaimFundView;
import com.futura.Purple.Entity.DeathClaimLeapFundDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.DeathClaimFundViewRepository;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Service.DeathClaimLeapFundDetailsService;

@RestController
@RequestMapping("/deathClaimLeapFundDetails")
public class DeathClaimLeapFundDetailsController {
	
	@Autowired
	private DeathClaimLeapFundDetailsService deathClaimLeapFundDetailsService;
	
	@Autowired
	private DeathClaimFundViewRepository fundViewRepository;
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired
	private ErrorService errorService;
	
	long serviceId = 44;
	
	@GetMapping("/getall/{userId}")
    public ResponseEntity<?> viewAll(@PathVariable Long userId) {
        String method = "get-all-deathClaimLeapFundDetails";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
        return ResponseEntity.ok(deathClaimLeapFundDetailsService.getAll());
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimLeapFundDetailsService.getAll());
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@GetMapping("/{id}/{userId}")
    public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

        String method = "get-deathClaimLeapFundDetails";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimLeapFundDetailsService.getById(id));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimLeapFundDetailsService.getById(id));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

	}
	
	@PostMapping("/add/{userId}")
    public ResponseEntity<?> add(@RequestBody DeathClaimLeapFundDetails entity, @PathVariable Long userId) {

        String method = "add-deathClaimLeapFundDetails";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimLeapFundDetailsService.add(entity));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimLeapFundDetailsService.add(entity));
        } else {
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@PatchMapping("/update/{id}/{userId}")
    public ResponseEntity<?> update(@RequestBody DeathClaimLeapFundDetails entity, @PathVariable Long id, @PathVariable Long userId) {

        String method = "update-deathClaimLeapFundDetails";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(deathClaimLeapFundDetailsService.update(id, entity));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimLeapFundDetailsService.update(id, entity));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@GetMapping("/search/{val}")
    public List<DeathClaimLeapFundDetails> search(@PathVariable String val) {
        return deathClaimLeapFundDetailsService.search(val);
    }
	
	@GetMapping("/getAllByPolicyNo/{policyNo}")
    public List<DeathClaimLeapFundDetails> viewAllByPolicy(@PathVariable Long policyNo) {
        return deathClaimLeapFundDetailsService.getAllByPolicyNo(policyNo);
    }
	
	@GetMapping("/getAllFundView/{uinNumber}")
    public List<DeathClaimFundView> viewAllFundView(@PathVariable String uinNumber) {
        return fundViewRepository.getallFundViewByUinNo(uinNumber);
    }
	
	@DeleteMapping("/hardDelete/{id}/{userId}")
    public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

        String method = "hard-delete-deathClaimLeapFundDetails";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	        return ResponseEntity.ok(deathClaimLeapFundDetailsService.hardDelete(id));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(deathClaimLeapFundDetailsService.hardDelete(id));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }

        @PatchMapping("/softdelete/{id}/{userId}")
        public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

            String method = "soft-delete-deathClaimLeapFundDetails";
            long userGroupId = userRepo.getById(userId).getUserGroupId();

            if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
                return ResponseEntity.ok(deathClaimLeapFundDetailsService.deactivate(id));
            } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
                return ResponseEntity.ok(deathClaimLeapFundDetailsService.deactivate(id));
            } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
            }

        }

}
