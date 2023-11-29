package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.PolicyDetailsPas;

public interface PolicyDetailsPasRepository extends JpaRepository<PolicyDetailsPas, Long> {

	@Query(value = "select * from policy_details_pas where valid_flag=1", nativeQuery = true)
	List<PolicyDetailsPas> getallActive();

	@Query(value = "select * from policy_details_pas where id=:id and valid_flag=1", nativeQuery = true)
	PolicyDetailsPas getActiveById(Long id);

	@Query(value = "select * from policy_details_pas where  id like %:key% and valid_flag = 1  or  clnt_num like %:key% and valid_flag = 1  or  chdr_num like %:key% and valid_flag = 1  or  installment_premium like %:key% and valid_flag = 1", nativeQuery = true)
	List<PolicyDetailsPas> globalSearch(String key);
	
	@Query(value = "select * from policy_details_pas where clnt_num=:clnt_num and valid_flag=1", nativeQuery = true)
	List<PolicyDetailsPas> getByClientNo(Long clnt_num);
	
	@Query(value = "select * from policy_details_pas where chdr_num=:chdr_num and valid_flag=1", nativeQuery = true)
	PolicyDetailsPas getActiveByPolicyNo(Long chdr_num);
	
	@Query(value="select anb_at_ccd from policy_details_pas where chdr_num = :policyNo and valid_flag=1",nativeQuery = true)
	Integer getByAge(Long policyNo);
	
	@Query(value="select EXISTS( select * from policy_details_pas where chdr_num = :chdrNum and valid_flag=1)",nativeQuery = true)
	Long existsByChdrNum(Long chdrNum);

}
