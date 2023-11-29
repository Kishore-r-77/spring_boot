package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.DeathClaimLeapParameter;

public interface DeathClaimLeapParameterRepository extends JpaRepository<DeathClaimLeapParameter, Long>{
	
	@Query(value = "select * from death_claim_leap_parameter where valid_flag=1", nativeQuery = true)
	List<DeathClaimLeapParameter> getallActive();
	
	@Query(value = "select * from death_claim_leap_parameter where  id like %:key% and valid_flag = 1  or  basic_sa like %:key% and valid_flag = 1  or  increase_sa_years like %:key% and valid_flag = 1  or  reversionary_bonus like %:key% and valid_flag = 1", nativeQuery = true)
	List<DeathClaimLeapParameter> globalSearch(String key);
	
	@Query(value = "select * from death_claim_leap_parameter where id=:id and valid_flag=1", nativeQuery = true)
	DeathClaimLeapParameter getActiveById(Long id);

}
