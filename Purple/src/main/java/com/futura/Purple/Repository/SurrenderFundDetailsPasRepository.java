package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.SurrenderFundDetailsPas;

public interface SurrenderFundDetailsPasRepository extends JpaRepository<SurrenderFundDetailsPas,Long> {
	
	@Query(value = "select * from surrender_fund_details_pas where valid_flag=1", nativeQuery = true)
	List<SurrenderFundDetailsPas> getallActive();
	
	@Query(value = "select * from surrender_fund_details_pas where id=:id and valid_flag=1", nativeQuery = true)
	SurrenderFundDetailsPas getActiveById(Long id);
	
	@Query(value = "select * from surrender_fund_details_pas where  id like %:key% and valid_flag = 1  or  fund_code like %:key% and valid_flag = 1  or  fund_name like %:key% and valid_flag = 1  or  rate_app like %:key% and valid_flag = 1  or  units like %:key% and valid_flag = 1  or  value like %:key% and valid_flag = 1", nativeQuery = true)
	List<SurrenderFundDetailsPas> globalSearch(String key);
	
	@Query(value = "select * from surrender_fund_details_pas where policy_num=:chdrNum and valid_flag=1", nativeQuery = true)
	List<SurrenderFundDetailsPas> getallByPolicy(Long chdrNum);

}
