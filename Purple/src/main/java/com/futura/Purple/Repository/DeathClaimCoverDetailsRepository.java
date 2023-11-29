package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.DeathClaimCoverDetails;

public interface DeathClaimCoverDetailsRepository extends JpaRepository<DeathClaimCoverDetails, Long> {
	
	@Query(value = "select * from death_claim_cover_details where valid_flag=1", nativeQuery = true)
	List<DeathClaimCoverDetails> getallActive();

	@Query(value = "select * from death_claim_cover_details where id=:id and valid_flag=1", nativeQuery = true)
	DeathClaimCoverDetails getActiveById(Long id);

	@Query(value = "select * from death_claim_cover_details where  id like %:key% and valid_flag = 1  or  clnt_num like %:key% and valid_flag = 1  or  policy_no like %:key% and valid_flag = 1  or  plan_code like %:key% and valid_flag = 1  or  plan_name like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1  or  risk_com_date like %:key% and valid_flag = 1", nativeQuery = true)
	List<DeathClaimCoverDetails> globalSearch(String key);
	
	@Query(value = "select * from death_claim_cover_details where policy_no=:policyNo and valid_flag=1", nativeQuery = true)
	List<DeathClaimCoverDetails> getAllByPolicyNo(Long policyNo);
	
	@Query(value="select * from death_claim_cover_details where policy_no = :policyNo and valid_flag=1",nativeQuery = true)
	DeathClaimCoverDetails findByPolicyNo(Long policyNo);
	
	@Query(value="select * from death_claim_cover_details where uin_number = :uinNumber and valid_flag=1",nativeQuery = true)
	DeathClaimCoverDetails findByuinNo(String uinNumber);
	
	@Query(value="select * from death_claim_cover_details where uin_number = :uinNumber and policy_no = :policyNo and valid_flag=1",nativeQuery = true)
	DeathClaimCoverDetails findByuinNoAndPolicyNo(String uinNumber, Long policyNo);

}
