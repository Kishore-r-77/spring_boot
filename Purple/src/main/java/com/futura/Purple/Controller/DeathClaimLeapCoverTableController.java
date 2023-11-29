package com.futura.Purple.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futura.Purple.Auth.repository.UserRepository;
import com.futura.Purple.Entity.DeathClaimLeapCoverTable;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Service.DeathClaimLeapCoverTableService;

@RestController
@RequestMapping("/deathClaimLeapCoverTable")
public class DeathClaimLeapCoverTableController {

	@Autowired
	private DeathClaimLeapCoverTableService leapCoverTableService;
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 38;
	

	 @GetMapping("/getAll/{userId}")
	    public ResponseEntity<?> viewAll(@PathVariable Long userId) {
	        String method = "get-all-deathClaimLeapCoverTable";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	        return ResponseEntity.ok(leapCoverTableService.getAll());
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(leapCoverTableService.getAll());
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }
	}
	 
	 @GetMapping("/{id}/{userId}")
	    public ResponseEntity<?> viewById(@PathVariable Long id, @PathVariable Long userId) {

	        String method = "get-deathClaimLeapCoverTable";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(leapCoverTableService.getById(id));
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(leapCoverTableService.getById(id));
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }
		}
	 
	 @GetMapping("/getByUin/{uinNo}/{userId}")
	    public ResponseEntity<?> viewByUinNo(@PathVariable String uinNo, @PathVariable Long userId) {

	        String method = "get-deathClaimLeapCoverTable";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(leapCoverTableService.getByUinNo(uinNo));
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(leapCoverTableService.getByUinNo(uinNo));
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }
		}
	 
	  @PostMapping("/add/{userId}")
	    public ResponseEntity<?> add( @RequestBody DeathClaimLeapCoverTable entity, @PathVariable Long userId) {

	        String method = "add-deathClaimLeapCoverTable";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        
	        	
	        
	            if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(leapCoverTableService.add(entity));
	            } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(leapCoverTableService.add(entity));
	            } else {
	                return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	            }
	        

	    }
	  
	  @PatchMapping("/update/{id}/{userId}")
	    public ResponseEntity<?> update(@Valid @RequestBody DeathClaimLeapCoverTable entity, @PathVariable Long id, @PathVariable Long userId) {

	        String method = "update-deathClaimLeapCoverTable";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(leapCoverTableService.update(id, entity));
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(leapCoverTableService.update(id, entity));
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }

	}
	  
	  @GetMapping("/search/{val}")
	    public List<DeathClaimLeapCoverTable> search(@PathVariable String val) {
	        return leapCoverTableService.search(val);
	    }

	

	        @PatchMapping("/softdelete/{id}/{userId}")
	        public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

	            String method = "soft-delete-deathClaimLeapCoverTable";
	            long userGroupId = userRepo.getById(userId).getUserGroupId();

	            if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(leapCoverTableService.deactivate(id));
	            } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(leapCoverTableService.deactivate(id));
	            } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	            }

	    }
}
