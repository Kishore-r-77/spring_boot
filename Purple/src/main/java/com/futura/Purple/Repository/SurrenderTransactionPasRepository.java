package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.SurrenderTransactionPas;
import com.futura.Purple.Entity.TransactionPas;

public interface SurrenderTransactionPasRepository extends JpaRepository<SurrenderTransactionPas, Long>{
	
	@Query(value = "select * from surrender_transaction_pas where interim_status = 'Initiated' and valid_flag=1", nativeQuery = true)
	List<SurrenderTransactionPas> getallActive();

	@Query(value = "select * from surrender_transaction_pas where id=:id and valid_flag=1", nativeQuery = true)
	SurrenderTransactionPas getActiveById(Long id);

	@Query(value = "select * from surrender_transaction_pas where  id like %:key% and valid_flag = 1  or  policy_no like %:key% and valid_flag = 1  or  trans_no like %:key% and valid_flag = 1", nativeQuery = true)
	List<SurrenderTransactionPas> globalSearch(String key);
	
	@Query(value = "select flc_policy_no from surrender_transaction_pas tp where interim_status = 'Initiated' and valid_flag =1;", nativeQuery = true)
	List<Long> findListOfPolicyNo();
	
	@Query(value = "select * from surrender_transaction_pas where policy_no=:policyNo and interim_status = 'Initiated' and valid_flag=1", nativeQuery = true)
	SurrenderTransactionPas findByPolicyNo(Long policyNo);
	
	@Query(value = "select * from surrender_transaction_pas where policy_no=:policyNo and interim_status = 'Processed' and valid_flag=1", nativeQuery = true)
	SurrenderTransactionPas findProcessedByPolicyNo(Long policyNo);

	@Query(value ="select EXISTS(select * from surrender_transaction_pas where trans_no=:transNo and valid_flag=1)", nativeQuery = true)
	Long existsByTransNo(long transNo);
	
	@Query(value = "select * from surrender_transaction_pas where trans_no=:tran_no and interim_status = 'Initiated' and valid_flag=1", nativeQuery = true)
	SurrenderTransactionPas getInitiatedTransaction(Long tran_no);
	
	@Query(value = "select * from surrender_transaction_pas where policy_no=:policyNo and valid_flag=1", nativeQuery = true)
	SurrenderTransactionPas getByPolicyNo(Long policyNo);
}
