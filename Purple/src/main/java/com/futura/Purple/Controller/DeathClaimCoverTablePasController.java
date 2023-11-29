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
import com.futura.Purple.Entity.DeathClaimCoverTablePas;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Service.DeathClaimCoverTablePasService;

@RestController
@RequestMapping("/deathClaimCoverTablePas")
public class DeathClaimCoverTablePasController {

	@Autowired
	private DeathClaimCoverTablePasService coverTablePasService;
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 37;
	
	
	
	 @GetMapping("/getAll/{userId}")
	    public ResponseEntity<?> viewAll(@PathVariable Long userId) {
	        String method = "get-all-deathClaimCoverTablePas";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	        return ResponseEntity.ok(coverTablePasService.getAll());
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(coverTablePasService.getAll());
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }
	}
	 
	 @GetMapping("/{id}/{userId}")
	    public ResponseEntity<?> viewById(@PathVariable Long id, @PathVariable Long userId) {

	        String method = "get-deathClaimCoverTablePas";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(coverTablePasService.getById(id));
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(coverTablePasService.getById(id));
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }
		}
	 
	 @GetMapping("/getByUin/{uinNo}/{userId}")
	    public ResponseEntity<?> viewByUinNo(@PathVariable String uinNo, @PathVariable Long userId) {

	        String method = "get-deathClaimCoverTablePas";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(coverTablePasService.getByUinNo(uinNo));
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(coverTablePasService.getByUinNo(uinNo));
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }
		}
	 
	  @PostMapping("/add/{userId}")
	    public ResponseEntity<?> add( @RequestBody DeathClaimCoverTablePas entity, @PathVariable Long userId) {

	        String method = "add-deathClaimCoverTablePas";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        
	        	
	        
	            if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(coverTablePasService.add(entity));
	            } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(coverTablePasService.add(entity));
	            } else {
	                return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	            }
	        

	    }
	  
	  @PatchMapping("/update/{id}/{userId}")
	    public ResponseEntity<?> update(@Valid @RequestBody DeathClaimCoverTablePas entity, @PathVariable Long id, @PathVariable Long userId) {

	        String method = "update-deathClaimCoverTablePas";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(coverTablePasService.update(id, entity));
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(coverTablePasService.update(id, entity));
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }

	}
	  
	  @GetMapping("/search/{val}")
	    public List<DeathClaimCoverTablePas> search(@PathVariable String val) {
	        return coverTablePasService.search(val);
	    }

	

	        @PatchMapping("/softdelete/{id}/{userId}")
	        public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

	            String method = "soft-delete-deathClaimCoverTablePas";
	            long userGroupId = userRepo.getById(userId).getUserGroupId();

	            if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(coverTablePasService.deactivate(id));
	            } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(coverTablePasService.deactivate(id));
	            } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	            }

	    }
}
