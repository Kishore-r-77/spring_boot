package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.TransactionPas;

public interface TransactionPasRepository extends JpaRepository<TransactionPas, Long> {

	@Query(value = "select * from transaction_pas where interim_status = 'Initiated' and  valid_flag=1", nativeQuery = true)
	List<TransactionPas> getallActive();

	@Query(value = "select * from transaction_pas where id=:id and valid_flag=1", nativeQuery = true)
	TransactionPas getActiveById(Long id);

	@Query(value = "select * from transaction_pas where  id like %:key% and valid_flag = 1  or  flc_policy_no like %:key% and valid_flag = 1  or  flc_trans_no like %:key% and valid_flag = 1  or  flc_log_date like %:key% and valid_flag = 1  or  flc_prem_refund like %:key% and valid_flag = 1  or  flc_policy_dop like %:key% and valid_flag = 1  or  penal_intrest like %:key% and valid_flag = 1  or  gross_flc_pay like %:key% and valid_flag = 1  or  medical_fee like %:key% and valid_flag = 1  or  stam_duty like %:key% and valid_flag = 1  or  risk_prem_recov like %:key% and valid_flag = 1  or  total_recov like %:key% and valid_flag = 1  or  net_flc_pay like %:key% and valid_flag = 1  or  medical_category like %:key% and valid_flag = 1  or  medical_center like %:key% and valid_flag = 1  or  medicat_tpa_code like %:key% and valid_flag = 1  or  maker_flag like %:key% and valid_flag = 1  or checker_flag  like %:key% and valid_flag = 1  or id like %:key% and valid_flag = 1", nativeQuery = true)
	List<TransactionPas> globalSearch(String key);

	@Query(value = "select * from transaction_pas where flc_policy_no=:policyNo and interim_status = 'Initiated' and valid_flag=1", nativeQuery = true)
	TransactionPas findByFlcPolicyNo(Long policyNo);

	@Query(value = "select flc_policy_no from transaction_pas tp where interim_status = 'Initiated' and valid_flag =1;", nativeQuery = true)
	List<Long> findFlcPolicyNo();

	@Query(value = "select * from transaction_pas where flc_trans_no=:tran_no and valid_flag=1", nativeQuery = true)
	TransactionPas getByflctrano(Long tran_no);

	@Query(value = "select * from transaction_pas where flc_policy_no=:policyNo and interim_status = 'Processed' and valid_flag=1", nativeQuery = true)
	TransactionPas findProcessedByPolicyNo(Long policyNo);

	@Query(value ="select EXISTS(select * from transaction_pas where flc_trans_no=:flctransNo and valid_flag=1)", nativeQuery = true)
	Long existsByFlcTransNo(long flctransNo);
	
	@Query(value = "select * from transaction_pas where flc_trans_no=:tran_no and interim_status = 'Initiated' and valid_flag=1", nativeQuery = true)
	TransactionPas getInitiatedTransaction(Long tran_no);
	
	@Query(value = "select * from transaction_pas where flc_policy_no=:policyNo and valid_flag=1", nativeQuery = true)
	TransactionPas getByFlcPolicyNo(Long policyNo);
	
	@Query(value = "select * from transaction_pas where flc_policy_no=:policyNo and interim_status = 'QC Completed' and valid_flag=1", nativeQuery = true)
	TransactionPas findCompletedByPolicyNo(Long policyNo);

}
