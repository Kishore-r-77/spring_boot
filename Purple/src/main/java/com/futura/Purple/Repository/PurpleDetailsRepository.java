package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.PurpleDetails;

public interface PurpleDetailsRepository extends JpaRepository<PurpleDetails, Long> {

	@Query(value = "select * from purple_details where approv_flag is NULL and valid_flag=1", nativeQuery = true)
	List<PurpleDetails> getAllQCPending();

	@Query(value = "select * from purple_details where id=:id and valid_flag=1", nativeQuery = true)
	PurpleDetails getActiveById(Long id);

	@Query(value = "select * from purple_details where  id like %:key% and valid_flag = 1  or  comp_id like %:key% and valid_flag = 1  or  tran_date like %:key% and valid_flag = 1  or  policy_no like %:key% and valid_flag = 1  or  tran_no like %:key% and valid_flag = 1  or  totl_premium like %:key% and valid_flag = 1  or  aval_suspense like %:key% and valid_flag = 1  or  penal_interest like %:key% and valid_flag = 1  or  medical_fee like %:key% and valid_flag = 1  or  stamp_duty like %:key% and valid_flag = 1  or  mort_charge like %:key% and valid_flag = 1  or  gross_payable like %:key% and valid_flag = 1  or  recoveries like %:key% and valid_flag = 1  or  net_payable like %:key% and valid_flag = 1  or  pf_flag like %:key% and valid_flag = 1  or  pf_remarks like %:key% and valid_flag = 1  or  approv_flag like %:key% and valid_flag = 1  or  approv_remarks like %:key% and valid_flag = 1  or  approv_date like %:key% and valid_flag = 1  or  pf_flag_update like %:key% and valid_flag = 1  or id like %:key% and valid_flag = 1", nativeQuery = true)
	List<PurpleDetails> globalSearch(String key);
	
	@Query(value = "select * from purple_details where policy_no=:policyNo and valid_flag=1", nativeQuery = true)
	PurpleDetails getByPolicyNo(Long policyNo);
	
	@Query(value = "select * from purple_details where tran_no=:tranNo and valid_flag=1", nativeQuery = true)
	PurpleDetails getByTransNo(Long tranNo);
	
	@Query(value = "select * from purple_details where approv_flag='Fail' and valid_flag=1", nativeQuery = true)
	List<PurpleDetails> getAllFailedRecord();
	
	@Query(value = "select * from purple_details where approv_flag='Pass' and valid_flag=1", nativeQuery = true)
	List<PurpleDetails> getAllPassedRecord();
	
	Boolean existsByPolicyNo(Long val);

}
