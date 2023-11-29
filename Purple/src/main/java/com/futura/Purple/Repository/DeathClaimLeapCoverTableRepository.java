package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.DeathClaimLeapCoverTable;

public interface DeathClaimLeapCoverTableRepository extends JpaRepository<DeathClaimLeapCoverTable, Long> {

	@Query(value = "select * from death_claim_leap_cover_table where valid_flag=1", nativeQuery = true)
	List<DeathClaimLeapCoverTable> getallActive();

	@Query(value = "select * from death_claim_leap_cover_table where id=:id and valid_flag=1", nativeQuery = true)
	DeathClaimLeapCoverTable getActiveById(Long id);

	@Query(value = "select * from death_claim_leap_cover_table where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  client_number like %:key% and valid_flag = 1  or  la_name like %:key% and valid_flag = 1  or email_id like %:key% and valid_flag = 1", nativeQuery = true)
	List<DeathClaimLeapCoverTable> globalSearch(String key);
	
	@Query(value = "select * from death_claim_leap_cover_table where uin_number=:uinNo and valid_flag=1", nativeQuery = true)
	DeathClaimLeapCoverTable getActiveByUinNo(String uinNo);
}
