package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.futura.Purple.Entity.UinMaster;

public interface UinMasterRepository extends JpaRepository<UinMaster, Long>{

	@Query(value = "select * from uin_master where valid_flag=1", nativeQuery = true)
	List<UinMaster> getallActive();

	@Query(value = "select * from uin_master where id=:id and valid_flag=1", nativeQuery = true)
	UinMaster getActiveById(Long id);
	
	@Query(value = "select * from uin_master where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1  or  plan_name like %:key% and valid_flag = 1  or plan_code like %:key% and valid_flag = 1 or prodect_type like %:key% and valid_flag = 1" , nativeQuery = true)
	List<UinMaster> globalSearch(String key);
	
	@Query(value = "select * from uin_master where uin_number=:uinNo and valid_flag=1", nativeQuery = true)
	UinMaster getActiveByUIN(String uinNo);
}
