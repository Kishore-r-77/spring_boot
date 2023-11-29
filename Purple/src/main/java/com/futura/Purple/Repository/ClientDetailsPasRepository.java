package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.ClientDetailsPas;

public interface ClientDetailsPasRepository extends JpaRepository<ClientDetailsPas, Long> {

	@Query(value = "select * from client_details_pas where valid_flag=1", nativeQuery = true)
	List<ClientDetailsPas> getallActive();

	@Query(value = "select * from client_details_pas where id=:id and valid_flag=1", nativeQuery = true)
	ClientDetailsPas getActiveById(Long id);

	@Query(value = "select * from client_details_pas where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  clnt_num like %:key% and valid_flag = 1  or  la_name like %:key% and valid_flag = 1  or email_id like %:key% and valid_flag = 1", nativeQuery = true)
	List<ClientDetailsPas> globalSearch(String key);
	
	@Query(value = "select * from client_details_pas where clnt_num=:clientNo and valid_flag=1", nativeQuery = true)
	ClientDetailsPas getActiveByClientNo(Long clientNo);
	
	@Query(value = "select EXISTS(select * from client_details_pas where clnt_num=:clntNum and valid_flag=1)", nativeQuery = true)
	Long existsByClntNum(Long clntNum);

}
