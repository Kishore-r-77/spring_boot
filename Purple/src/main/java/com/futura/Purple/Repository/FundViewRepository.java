package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.FundView;

public interface FundViewRepository extends JpaRepository<FundView, String> {

	
	@Query(value = "select * from fund_view where chdr_num=:chdrNo ", nativeQuery = true)
	List<FundView> getallFundViewByPolicyNo(Long chdrNo);
}
