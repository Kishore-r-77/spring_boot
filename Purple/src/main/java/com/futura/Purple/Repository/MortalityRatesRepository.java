package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.MortalityRates;

public interface MortalityRatesRepository extends JpaRepository<MortalityRates, Long> {

	@Query(value = "select * from mortality_rates where valid_flag=1", nativeQuery = true)
	List<MortalityRates> getallActive();

	@Query(value = "select * from mortality_rates where id=:id and valid_flag=1", nativeQuery = true)
	MortalityRates getActiveById(Long id);

	@Query(value = "select * from mortality_rates where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  gender like %:key% and valid_flag = 1  or  smoker like %:key% and valid_flag = 1  or id like %:key% and valid_flag = 1", nativeQuery = true)
	List<MortalityRates> globalSearch(String key);
	
	@Query(value="select rates from mortality_rates where uin_number=:uinNumber  and age=:age and prem_term=:premTerm and valid_flag=1",nativeQuery = true)
	Float findByCCCT(String uinNumber, Integer age,String premTerm);

}
