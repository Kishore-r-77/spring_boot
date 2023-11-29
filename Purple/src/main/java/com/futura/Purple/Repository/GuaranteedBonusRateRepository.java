package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.futura.Purple.Entity.GuaranteedBonusRate;

public interface GuaranteedBonusRateRepository extends JpaRepository<GuaranteedBonusRate, Long> {

	@Query(value = "select * from guaranteed_bonus_rate where valid_flag=1", nativeQuery = true)
	List<GuaranteedBonusRate> getallActive();

	@Query(value = "select * from guaranteed_bonus_rate where id=:id and valid_flag=1", nativeQuery = true)
	GuaranteedBonusRate getActiveById(Long id);

	@Query(value = "select * from guaranteed_bonus_rate where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1  or  plan_name like %:key% and valid_flag = 1  or plan_code like %:key% and valid_flag = 1", nativeQuery = true)
	List<GuaranteedBonusRate> globalSearch(String key);
	
	@Query(value = "select * from guaranteed_bonus_rate where uin_number=:uinNumber and valid_flag=1", nativeQuery = true)
	GuaranteedBonusRate getActiveByUIN(String uinNumber);
	
//	@Query(value = "select guaranteed_bonus_rate from guaranteed_bonus_rate where STR_TO_DATE(start_date,'%Y%m%d')<=:docDate and STR_TO_DATE(end_date,'%Y%m%d')>=:docDate and plan_name=:planName and plan_code=: planCode and uin_number=:uinNumber and valid_flag=1", nativeQuery = true)
//	Double getRateForDoc(@Param("docDate") String docDate,@Param("uinNumber") String uinNumber,@Param("planName") String planName,@Param("planCode") String planCode);

	@Query(value = "call getGuaranteedBonusrate(:docDate,:planCode,:planName,:uinNumber)", nativeQuery = true)
	Double getRateForDoc(@Param("docDate") String docDate,@Param("planCode") String planCode ,@Param("planName") String planName,@Param("uinNumber") String uinNumber);
	
}
