package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.SsvFactor;

public interface SsvFactorRepository extends JpaRepository<SsvFactor, Long> {
	
	@Query(value = "select * from ssv_factor where valid_flag=1", nativeQuery = true)
	List<SsvFactor> getallActive();
	
	@Query(value = "select * from ssv_factor where id=:id and valid_flag=1", nativeQuery = true)
	SsvFactor getActiveById(Long id);
	
	@Query(value = "select * from ssv_factor where  id like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1  or  plan_name like %:key% and valid_flag = 1  or  plan_code like %:key% and valid_flag = 1", nativeQuery = true)
	List<SsvFactor> globalSearch(String key);

	@Query(value = "select rate from ssv_factor where uin_number=:uinNo and policy_duration=:duration and valid_flag=1;", nativeQuery = true)
	Double getRate(String uinNo,Double duration);
	
	
}
