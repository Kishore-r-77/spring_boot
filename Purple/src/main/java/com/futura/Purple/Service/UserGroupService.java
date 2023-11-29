package com.futura.Purple.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.UserGroup;
import com.futura.Purple.Error.ErrorService;
import com.futura.Purple.Repository.UserGroupRepository;
import com.futura.Purple.dto.UserGroupDto;

@Service
public class UserGroupService {
	
	@Autowired
	private UserGroupRepository userGroupRepo;
	@Autowired
	private ErrorService errorService;
	
//	public List<UserGroup> getall(){
//		return userGroupRepo.getallActive();
//	}
	
	public List<UserGroupDto> getall() {
		List<UserGroupDto> userGroupDtos = new ArrayList<>();
		List<UserGroup> userGroups = userGroupRepo.getallActive();
		userGroups.stream().forEach(userGroup -> {
			UserGroupDto userGroupDto = mapToDto(userGroup);
			userGroupDtos.add(userGroupDto);
		});
		return userGroupDtos;
	}
	
	public UserGroupDto getById(Long id) {
		UserGroup userGroup = userGroupRepo.getActiveById(id);
		return mapToDto(userGroup);
	}
	
	public String add(UserGroup entity) {
		entity.setValidFlag(1);
		userGroupRepo.save(entity);
		return errorService.getErrorById("ER001");
		
	}
	
	public String update(Long id, UserGroup entity) {
		UserGroup userGroup = userGroupRepo.getActiveById(id);
		if(entity.getCompanyId()!=null) {
			userGroup.setCompanyId(entity.getCompanyId());
		}
		if(entity.getUserGroupName()!=null) {
			userGroup.setUserGroupName(entity.getUserGroupName());
		}
		
		userGroupRepo.save(userGroup);
		return errorService.getErrorById("ER003");
	}
	
	public String deactivate(Long id) {
		UserGroup userGroup = userGroupRepo.getActiveById(id);
		userGroup.setValidFlag(-1);
		userGroupRepo.save(userGroup);
		return errorService.getErrorById("ER008");
	}
	
	public String hardDelete(Long id) {
		userGroupRepo.deleteById(id);
		return errorService.getErrorById("ER012");
	}
	
	public List<UserGroup> search(String key){
		return userGroupRepo.globalSearch(key);
	}
	
	public List<UserGroup> getAllByCmpId(long id){
		return userGroupRepo.getAllUserGroupByCompanyId(id);
	}
	
	public UserGroupDto mapToDto(UserGroup userGroup) {
		UserGroupDto userGroupDto = new UserGroupDto();
		
		userGroupDto.setId(userGroup.getId());
		userGroupDto.setCompanyId(userGroup.getCompanyId());
		userGroupDto.setCompanyName(userGroup.getCompany().getCompanyName());
		userGroupDto.setUserGroupName(userGroup.getUserGroupName());
		return userGroupDto;
		
		
	}
	

}
