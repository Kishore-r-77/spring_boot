package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.futura.Purple.Entity.InterimBonusRate;

public interface InterimBonusRateRepository extends JpaRepository<InterimBonusRate,Long> {
	
	@Query(value = "select * from interim_bonus_rate where valid_flag=1", nativeQuery = true)
	List<InterimBonusRate> getallActive();

	@Query(value = "select * from interim_bonus_rate where id=:id and valid_flag=1", nativeQuery = true)
	InterimBonusRate getActiveById(Long id);

	@Query(value = "select * from interim_bonus_rate where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1  or  plan_name like %:key% and valid_flag = 1  or plan_code like %:key% and valid_flag = 1", nativeQuery = true)
	List<InterimBonusRate> globalSearch(String key);
	
	@Query(value = "select * from interim_bonus_rate where uin_number=:uinNumber and valid_flag=1", nativeQuery = true)
	InterimBonusRate getActiveByUIN(String uinNumber);

	@Query(value = "call getinterimbonusrate(:docDate,:planCode,:planName,:uinNumber)", nativeQuery = true)
	Double getRateForDoc(@Param("docDate") String docDate,@Param("planCode") String planCode ,@Param("planName") String planName,@Param("uinNumber") String uinNumber);

}
