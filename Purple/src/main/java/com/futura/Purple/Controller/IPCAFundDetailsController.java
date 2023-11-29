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
import com.futura.Purple.Entity.IPCAFundDetails;
import com.futura.Purple.Entity.SurrenderFundView;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Repository.SurrenderFundViewRepository;
import com.futura.Purple.Service.IPCAFundDetailsService;

@RestController
@RequestMapping("/ipcafunddetails")
public class IPCAFundDetailsController {
	
	@Autowired
	private IPCAFundDetailsService purpleFundDetailsService;
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SurrenderFundViewRepository fundViewRepository;
	
	@Autowired
	private ErrorService errorService;
	
	long serviceId = 30;
	
	@GetMapping("/getall/{userId}")
    public ResponseEntity<?> viewAll(@PathVariable Long userId) {
        String method = "get-all-ipcaFundDetails";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
        return ResponseEntity.ok(purpleFundDetailsService.getAll());
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(purpleFundDetailsService.getAll());
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@GetMapping("/{id}/{userId}")
    public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

        String method = "get-ipcaFundDetails";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(purpleFundDetailsService.getById(id));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(purpleFundDetailsService.getById(id));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

	}
	
	@PostMapping("/add/{userId}")
    public ResponseEntity<?> add(@RequestBody IPCAFundDetails entity, @PathVariable Long userId) {

        String method = "add-ipcaFundDetails";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(purpleFundDetailsService.add(entity));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(purpleFundDetailsService.add(entity));
        } else {
        return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@PatchMapping("/update/{id}/{userId}")
    public ResponseEntity<?> update(@RequestBody IPCAFundDetails entity, @PathVariable Long id, @PathVariable Long userId) {

        String method = "update-ipcaFundDetails";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
			return ResponseEntity.ok(purpleFundDetailsService.update(id, entity));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(purpleFundDetailsService.update(id, entity));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }
	
	@GetMapping("/search/{val}")
    public List<IPCAFundDetails> search(@PathVariable String val) {
        return purpleFundDetailsService.search(val);
    }
	
	@GetMapping("/getAllByPolicyNo/{policyNo}")
    public List<IPCAFundDetails> viewAllByPolicy(@PathVariable Long policyNo) {
        return purpleFundDetailsService.getAllByPolicyNo(policyNo);
    }
	
	@GetMapping("/getAllFundView/{policyNo}")
    public List<SurrenderFundView> viewAllFundView(@PathVariable Long policyNo) {
        return fundViewRepository.getallFundViewByPolicyNo(policyNo);
    }
	
	@DeleteMapping("/hardDelete/{id}/{userId}")
    public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

        String method = "hard-delete-ipcaFundDetails";
        long userGroupId = userRepo.getById(userId).getUserGroupId();

        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	        return ResponseEntity.ok(purpleFundDetailsService.hardDelete(id));
        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
            return ResponseEntity.ok(purpleFundDetailsService.hardDelete(id));
        } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
        }

    }

        @PatchMapping("/softdelete/{id}/{userId}")
        public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

            String method = "soft-delete-ipcaFundDetails";
            long userGroupId = userRepo.getById(userId).getUserGroupId();

            if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
                return ResponseEntity.ok(purpleFundDetailsService.deactivate(id));
            } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
                return ResponseEntity.ok(purpleFundDetailsService.deactivate(id));
            } else {
            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
            }

        }

}
