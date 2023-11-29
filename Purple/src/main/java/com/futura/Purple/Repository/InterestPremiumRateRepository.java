package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.futura.Purple.Entity.InterestPremiumRate;

public interface InterestPremiumRateRepository extends JpaRepository<InterestPremiumRate, Long> {

	@Query(value = "select * from interest_premium_rate where valid_flag=1", nativeQuery = true)
	List<InterestPremiumRate> getallActive();

	@Query(value = "select * from interest_premium_rate where id=:id and valid_flag=1", nativeQuery = true)
	InterestPremiumRate getActiveById(Long id);

	@Query(value = "select * from interest_premium_rate where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1", nativeQuery = true)
	List<InterestPremiumRate> globalSearch(String key);
	
	@Query(value = "call getInterestPremiumRate(:startDate,:uinNumber)", nativeQuery = true)
	InterestPremiumRate getInterestPremiumRate(@Param("startDate") String startDate,@Param("uinNumber") String uinNumber);

}
