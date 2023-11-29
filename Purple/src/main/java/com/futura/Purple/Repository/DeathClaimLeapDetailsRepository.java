package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.DeathClaimLeapDetails;

public interface DeathClaimLeapDetailsRepository extends JpaRepository<DeathClaimLeapDetails, Long> {
	
	@Query(value = "select * from death_claim_leap_details where valid_flag=1", nativeQuery = true)
	List<DeathClaimLeapDetails> getallActive();
	
	@Query(value = "select * from death_claim_leap_details where qc_approval_flag is NULL and valid_flag=1", nativeQuery = true)
	List<DeathClaimLeapDetails> getAllQCPending();

	@Query(value = "select * from death_claim_leap_details where id=:id and valid_flag=1", nativeQuery = true)
	DeathClaimLeapDetails getActiveById(Long id);

	@Query(value = "select * from death_claim_leap_details where  id like %:key% and valid_flag = 1  or  policy_no like %:key% and valid_flag = 1  or  trans_no like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1  or  req_date like %:key% and valid_flag = 1  or  log_date like %:key% and valid_flag = 1", nativeQuery = true)
	List<DeathClaimLeapDetails> globalSearch(String key);
	
	@Query(value = "select * from death_claim_leap_details where uin_number=:uinNo and valid_flag=1", nativeQuery = true)
	DeathClaimLeapDetails getByUinNo(String uinNo);
	
	@Query(value = "select * from death_claim_leap_details where trans_no=:tranNo and valid_flag=1", nativeQuery = true)
	DeathClaimLeapDetails getByTransNo(Long tranNo);
	
	@Query(value = "select * from death_claim_leap_details where qc_approval_flag='Fail' and valid_flag=1", nativeQuery = true)
	List<DeathClaimLeapDetails> getAllFailedRecord();
	
	@Query(value = "select * from death_claim_leap_details where qc_approval_flag='Pass' and valid_flag=1", nativeQuery = true)
	List<DeathClaimLeapDetails> getAllPassedRecord();

}
