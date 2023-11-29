package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
	
	@Query(value = "select * from user_group where valid_flag=1", nativeQuery = true)
	List<UserGroup> getallActive();

	@Query(value = "select * from user_group where id=:id and valid_flag=1", nativeQuery = true)
	UserGroup getActiveById(Long id);
	
	@Query(value = "select user_group_name from user_group where id=:id and valid_flag=1", nativeQuery = true)
	String getUserNameById(Long id);

	@Query(value = "select * from user_group where id like %:key% and valid_flag = 1 or user_group_name like %:key% and valid_flag = 1", nativeQuery = true)
	List<UserGroup> globalSearch(String key);
	
    @Query(value = "select * from user_group where company_id = :cmpId and valid_flag = 1", nativeQuery = true)
    List<UserGroup>  getAllUserGroupByCompanyId(Long cmpId);

}
