package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.futura.Purple.Entity.GSVCashValue;

public interface GSVCashValueRepository extends JpaRepository<GSVCashValue, Long> {

	@Query(value = "select * from gsv_cash_value where valid_flag=1", nativeQuery = true)
	List<GSVCashValue> getallActive();

	@Query(value = "select * from gsv_cash_value where id=:id and valid_flag=1", nativeQuery = true)
	GSVCashValue getActiveById(Long id);

	@Query(value = "select * from gsv_cash_value where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1  or  plan_name like %:key% and valid_flag = 1  or plan_code like %:key% and valid_flag = 1", nativeQuery = true)
	List<GSVCashValue> globalSearch(String key);
	
	@Query(value = "select * from gsv_cash_value where uin_number=:uinNumber and valid_flag=1", nativeQuery = true)
	GSVCashValue getActiveByUIN(String uinNumber);
	
	@Query(value = "select cvb_rate from gsv_cash_value where uin_number=:uinNumber and years_to_maturity=:years and valid_flag=1", nativeQuery = true)
	Double getRate(String uinNumber, Double years);
}
