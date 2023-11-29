package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.futura.Purple.Entity.CoverDetailsPas;
import com.futura.Purple.Entity.TransactionPas;

public interface CoverDetailsPasRepository extends JpaRepository<CoverDetailsPas, Long> {

	@Query(value = "select * from cover_details_pas where valid_flag=1", nativeQuery = true)
	List<CoverDetailsPas> getallActive();

	@Query(value = "select * from cover_details_pas where id=:id and valid_flag=1", nativeQuery = true)
	CoverDetailsPas getActiveById(Long id);

	@Query(value = "select * from cover_details_pas where  id like %:key% and valid_flag = 1  or  clnt_num like %:key% and valid_flag = 1  or  chdr_num like %:key% and valid_flag = 1  or  cr_table like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1  or  risk_com_date like %:key% and valid_flag = 1", nativeQuery = true)
	List<CoverDetailsPas> globalSearch(String key);
	
	@Query(value = "select * from cover_details_pas where chdr_num=:chdr_num and valid_flag=1", nativeQuery = true)
	List<CoverDetailsPas> getAllByPolicyNo(Long chdr_num);
	
	@Query(value="select * from cover_details_pas where chdr_num = :policyNo and valid_flag=1",nativeQuery = true)
	CoverDetailsPas findByChdrNum(Long policyNo);
	
	@Query(value = "select * from cover_details_pas where chdr_num=:policy and valid_flag=1", nativeQuery = true)
	CoverDetailsPas getByPolicy(Long policy);
	

	
}
