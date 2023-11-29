package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.DeathClaimLeapFundDetails;

public interface DeathClaimLeapFundDetailsRepository extends JpaRepository<DeathClaimLeapFundDetails,Long> {
	
	@Query(value = "select * from death_claim_leap_fund_details where valid_flag=1", nativeQuery = true)
	List<DeathClaimLeapFundDetails> getallActive();
	
	@Query(value = "select * from death_claim_leap_fund_details where id=:id and valid_flag=1", nativeQuery = true)
	DeathClaimLeapFundDetails getActiveById(Long id);
	
	@Query(value = "select * from death_claim_leap_fund_details where  id like %:key% and valid_flag = 1  or  fund_code like %:key% and valid_flag = 1  or  fund_name like %:key% and valid_flag = 1  or  rate_app like %:key% and valid_flag = 1  or  units like %:key% and valid_flag = 1  or  value like %:key% and valid_flag = 1  or  remarks like %:key% and valid_flag = 1", nativeQuery = true)
	List<DeathClaimLeapFundDetails> globalSearch(String key);
	
	@Query(value = "select * from death_claim_leap_fund_details where policy_no=:policyNo and valid_flag=1", nativeQuery = true)
	List<DeathClaimLeapFundDetails> getallByPolicyNo(Long policyNo);


}
