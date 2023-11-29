package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.GsvFactor;

public interface GsvFactorRepository extends JpaRepository<GsvFactor, Long> {
	
	@Query(value = "select * from gsv_factor where valid_flag=1", nativeQuery = true)
	List<GsvFactor> getallActive();

	@Query(value = "select * from gsv_factor where id=:id and valid_flag=1", nativeQuery = true)
	GsvFactor getActiveById(Long id);

	@Query(value = "select * from gsv_factor where id like %:key% and valid_flag = 1 or uinNumber like %:key% and valid_flag = 1 or planName like %:key%  and valid_flag = 1 or planCode like %:key%  and valid_flag = 1 or yearsToMaturity like %:key%  and valid_flag = 1 or cvbRate like %:key% and valid_flag = 1", nativeQuery = true)
	List<GsvFactor> globalSearch(String key);
	
	@Query(value = "select rate from gsv_factor where uin_number=:uinNo and policy_duration=:duration and valid_flag=1", nativeQuery = true)
	Double getRate(String uinNo,Double duration);

}
