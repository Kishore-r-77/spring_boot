package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.DeathClaimTransactionPas;

public interface DeathClaimTransactionPasRepository extends JpaRepository<DeathClaimTransactionPas, Long>{

	
	@Query(value = "select * from death_claim_transaction_pas where interim_status = 'Initiated' and valid_flag=1", nativeQuery = true)
	List<DeathClaimTransactionPas> getallActive();

	@Query(value = "select * from death_claim_transaction_pas where id=:id and valid_flag=1", nativeQuery = true)
	DeathClaimTransactionPas getActiveById(Long id);

	@Query(value = "select * from death_claim_transaction_pas where  id like %:key% and valid_flag = 1  or  policy_no like %:key% and valid_flag = 1  or  trans_no like %:key% and valid_flag = 1", nativeQuery = true)
	List<DeathClaimTransactionPas> globalSearch(String key);
	
	@Query(value = "select flc_policy_no from death_claim_transaction_pas tp where interim_status = 'Initiated' and valid_flag =1;", nativeQuery = true)
	List<Long> findListOfPolicyNo();
	
	@Query(value = "select * from death_claim_transaction_pas where uin_number=:uinNumber and interim_status = 'Initiated' and valid_flag=1", nativeQuery = true)
	DeathClaimTransactionPas findByUinNumber(String uinNumber);
	
	@Query(value = "select * from death_claim_transaction_pas where uin_number=:uinNo and interim_status = 'Processed' and valid_flag=1", nativeQuery = true)
	DeathClaimTransactionPas findProcessedByUinNo(Long uinNo);

	@Query(value ="select EXISTS(select * from death_claim_transaction_pas where trans_no=:transNo and valid_flag=1)", nativeQuery = true)
	Long existsByTransNo(long transNo);
	
	@Query(value = "select * from death_claim_transaction_pas where trans_no=:tran_no and interim_status = 'Initiated' and valid_flag=1", nativeQuery = true)
	DeathClaimTransactionPas getInitiatedTransaction(Long tran_no);
	
	@Query(value = "select * from death_claim_transaction_pas where policy_no=:policyNo and valid_flag=1", nativeQuery = true)
	DeathClaimTransactionPas getByPolicyNo(Long policyNo);
}
