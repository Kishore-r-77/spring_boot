package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.SurrenderClientDetails;

public interface SurrenderClientDetailsRepository extends JpaRepository<SurrenderClientDetails, Long>{

	@Query(value = "select * from surrender_client_details where valid_flag=1", nativeQuery = true)
	List<SurrenderClientDetails> getallActive();

	@Query(value = "select * from surrender_client_details where id=:id and valid_flag=1", nativeQuery = true)
	SurrenderClientDetails getActiveById(Long id);

	@Query(value = "select * from surrender_client_details where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  client_number like %:key% and valid_flag = 1  or  la_name like %:key% and valid_flag = 1  or email_id like %:key% and valid_flag = 1", nativeQuery = true)
	List<SurrenderClientDetails> globalSearch(String key);
	
	@Query(value = "select * from surrender_client_details where clnt_num=:clientNo and valid_flag=1", nativeQuery = true)
	SurrenderClientDetails getActiveByClientNo(Long clientNo);
	
	@Query(value = "select EXISTS(select * from surrender_client_details where clnt_num=:clientNumber and valid_flag=1)", nativeQuery = true)
	Long existsByClientNumber(Long clientNumber);
	
}
