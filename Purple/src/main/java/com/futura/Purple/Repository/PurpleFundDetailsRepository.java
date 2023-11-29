package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.PurpleFundDetails;

public interface PurpleFundDetailsRepository extends JpaRepository<PurpleFundDetails,Long> {
	
	@Query(value = "select * from purple_fund_details where valid_flag=1", nativeQuery = true)
	List<PurpleFundDetails> getallActive();
	
	@Query(value = "select * from purple_fund_details where id=:id and valid_flag=1", nativeQuery = true)
	PurpleFundDetails getActiveById(Long id);
	
	@Query(value = "select * from purple_fund_details where  id like %:key% and valid_flag = 1  or  fund_code like %:key% and valid_flag = 1  or  fund_name like %:key% and valid_flag = 1  or  rate_app like %:key% and valid_flag = 1  or  units like %:key% and valid_flag = 1  or  value like %:key% and valid_flag = 1  or  remarks like %:key% and valid_flag = 1", nativeQuery = true)
	List<PurpleFundDetails> globalSearch(String key);
	
	@Query(value = "select * from purple_fund_details where policy_no=:policyNo and valid_flag=1", nativeQuery = true)
	List<PurpleFundDetails> getallByPolicyNo(Long policyNo);

}
