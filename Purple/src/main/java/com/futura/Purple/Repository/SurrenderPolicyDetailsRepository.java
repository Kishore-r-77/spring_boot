package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.SurrenderPolicyDetails;

public interface SurrenderPolicyDetailsRepository extends JpaRepository<SurrenderPolicyDetails,Long> {
	
	@Query(value = "select * from surrender_policy_details where valid_flag=1", nativeQuery = true)
	List<SurrenderPolicyDetails> getallActive();
	
	@Query(value = "select * from surrender_policy_details where  id like %:key% and valid_flag = 1  or  clnt_num like %:key% and valid_flag = 1  or  chdr_num like %:key% and valid_flag = 1  or  installment_premium like %:key% and valid_flag = 1", nativeQuery = true)
	List<SurrenderPolicyDetails> globalSearch(String key);
	
	@Query(value = "select * from surrender_policy_details where id=:id and valid_flag=1", nativeQuery = true)
	SurrenderPolicyDetails getActiveById(Long id);
	
	@Query(value = "select * from surrender_policy_details where chdr_num=:policyNo and valid_flag=1", nativeQuery = true)
	SurrenderPolicyDetails getActiveByPolicyNo(Long policyNo);
	
	@Query(value="select EXISTS( select * from surrender_policy_details where chdr_num = :chdrNum and valid_flag=1)",nativeQuery = true)
	Long existsByChdrNum(Long chdrNum);

}
