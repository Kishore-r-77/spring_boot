package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.futura.Purple.Entity.DeathClaimParam;

public interface DeathClaimParamRepository extends JpaRepository<DeathClaimParam, Long> {
	
	@Query(value = "select * from death_claim_param where valid_flag=1", nativeQuery = true)
	List<DeathClaimParam> getallActive();

	@Query(value = "select * from death_claim_param where id=:id and valid_flag=1", nativeQuery = true)
	DeathClaimParam getActiveById(Long id);

	@Query(value = "select * from death_claim_param where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1  or  cause_of_death like %:key% and valid_flag = 1", nativeQuery = true)
	List<DeathClaimParam> globalSearch(String key);
	
	@Query(value = "select * from death_claim_param where uin_number=:uinNumber and valid_flag=1", nativeQuery = true)
	DeathClaimParam getActiveByUIN(String uinNumber);
	
	@Query(value = "call getDeathClaimFactor(:dateOfDeath,:uinNumber,:causeOfDeath,:month)", nativeQuery = true)
	Double getDeathFactor(@Param("dateOfDeath") String dateOfDeath,@Param("uinNumber") String uinNumber ,@Param("causeOfDeath") String causeOfDeath,@Param("month") Long month);
	
	@Query(value = "call getDeathClaimFactor(:dateOfDeath,:uinNumber,'****',:month)", nativeQuery = true)
	Double getDeathFactor1(@Param("dateOfDeath") String dateOfDeath,@Param("uinNumber") String uinNumber ,@Param("month") Long month);

	@Query(value = "call getDeathClaimFactor(:dateOfDeath,:uinNumber,'DASA',:month)", nativeQuery = true)
	Double getDeathFactorAddSA(@Param("dateOfDeath") String dateOfDeath,@Param("uinNumber") String uinNumber ,@Param("month") Long month);

}
