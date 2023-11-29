package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.DeathClaimCoverTablePas;

public interface DeathClaimCoverTablePasRepository extends JpaRepository<DeathClaimCoverTablePas, Long> {

	@Query(value = "select * from death_claim_cover_table_pas where valid_flag=1", nativeQuery = true)
	List<DeathClaimCoverTablePas> getallActive();

	@Query(value = "select * from death_claim_cover_table_pas where id=:id and valid_flag=1", nativeQuery = true)
	DeathClaimCoverTablePas getActiveById(Long id);

	@Query(value = "select * from death_claim_cover_table_pas where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  client_number like %:key% and valid_flag = 1  or  la_name like %:key% and valid_flag = 1  or email_id like %:key% and valid_flag = 1", nativeQuery = true)
	List<DeathClaimCoverTablePas> globalSearch(String key);
	
	@Query(value = "select * from death_claim_cover_table_pas where uin_number=:uinNo and valid_flag=1", nativeQuery = true)
	DeathClaimCoverTablePas getActiveByUinNo(String uinNo);
}
