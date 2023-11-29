package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.PurpleNav;

public interface PurpleNavRepository extends JpaRepository<PurpleNav,Long> {
	
	@Query(value = "select * from purple_nav where valid_flag=1", nativeQuery = true)
	List<PurpleNav> getallActive();
	
	@Query(value = "select * from purple_nav where id=:id and valid_flag=1", nativeQuery = true)
	PurpleNav getActiveById(Long id);
	
	@Query(value = "select * from purple_nav where  id like %:key% and valid_flag = 1  or  eff_date like %:key% and valid_flag = 1  or  fund_code like %:key% and valid_flag = 1  or  fund_name like %:key% and valid_flag = 1  or  rate like %:key% and valid_flag = 1", nativeQuery = true)
	List<PurpleNav> globalSearch(String key);
	
	@Query(value = "select * from purple_nav where fund_code=:fundCode and nav_date=:navDate and valid_flag=1", nativeQuery = true)
	PurpleNav getByFundCode(String fundCode, String navDate);
	
	@Query(value = "select rate from purple_nav where fund_code=:fundCode and nav_date=:navDate and valid_flag=1", nativeQuery = true)
	Double getByFundRate(String fundCode, String navDate);

}
