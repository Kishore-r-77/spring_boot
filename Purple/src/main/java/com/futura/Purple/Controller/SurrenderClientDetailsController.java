package com.futura.Purple.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.futura.Purple.Entity.SurrenderClientDetails;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.PermissionRepository;
import com.futura.Purple.Repository.SurrenderClientDetailsRepository;
import com.futura.Purple.Service.SurrenderClientDetailsService;

@RestController
@RequestMapping("/SurrenderClientDetails")
@CrossOrigin
public class SurrenderClientDetailsController {
	
	@Autowired
	private SurrenderClientDetailsService detailsService;
	
	@Autowired
	private SurrenderClientDetailsRepository detailsRepository;
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ErrorService errorService;

	long serviceId = 19;
	
	
	 @GetMapping("/getAll/{userId}")
	    public ResponseEntity<?> viewAll(@PathVariable Long userId) {
	        String method = "get-all-SurrenderClientDetails";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	        return ResponseEntity.ok(detailsService.getAll());
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(detailsService.getAll());
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }
	}
	 
	 @GetMapping("/{id}/{userId}")
	    public ResponseEntity<?> view(@PathVariable Long id, @PathVariable Long userId) {

	        String method = "get-SurrenderClientDetails";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(detailsService.getById(id));
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(detailsService.getById(id));
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }
		}
	 
	  @PostMapping("/add/{userId}")
	    public ResponseEntity<?> add(@Valid @RequestBody SurrenderClientDetails entity, @PathVariable Long userId) {

	        String method = "add-SurrenderClientDetails";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();
	        Long val = detailsRepository.existsByClientNumber(entity.getClntNum());

	        if (val>0) {
	        	
	            return ResponseEntity.badRequest().body(new MessageResponse("Client Number already exists"));
	        } else {
	            if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(detailsService.add(entity));
	            } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(detailsService.add(entity));
	            } else {
	                return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	            }
	        }

	    }
	  
	  @PatchMapping("/update/{id}/{userId}")
	    public ResponseEntity<?> update(@Valid @RequestBody SurrenderClientDetails entity, @PathVariable Long id, @PathVariable Long userId) {

	        String method = "update-SurrenderClientDetails";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
				return ResponseEntity.ok(detailsService.update(id, entity));
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(detailsService.update(id, entity));
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }

	}
	  
	  @GetMapping("/search/{val}")
	    public List<SurrenderClientDetails> search(@PathVariable String val) {
	        return detailsService.search(val);
	    }

	    @DeleteMapping("/hardDelete/{id}/{userId}")
	    public ResponseEntity<?> hardDelete(@PathVariable Long id, @PathVariable Long userId) {

	        String method = "hard-delete-SurrenderClientDetails";
	        long userGroupId = userRepo.getById(userId).getUserGroupId();

	        if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
		        return ResponseEntity.ok(detailsService.hardDelete(id));
	        } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	            return ResponseEntity.ok(detailsService.hardDelete(id));
	        } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	        }

	    }

	        @PatchMapping("/softdelete/{id}/{userId}")
	        public ResponseEntity<?> softDelete(@PathVariable Long id, @PathVariable Long userId) {

	            String method = "soft-delete-SurrenderClientDetails";
	            long userGroupId = userRepo.getById(userId).getUserGroupId();

	            if (!permissionRepo.isMethodPresent(userGroupId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(detailsService.deactivate(id));
	            } else if (!permissionRepo.isMethodPresentUser(userId, serviceId, method).isEmpty()) {
	                return ResponseEntity.ok(detailsService.deactivate(id));
	            } else {
	            return ResponseEntity.badRequest().body(errorService.getErrorById("ER007"));
	            }

	    }

}
