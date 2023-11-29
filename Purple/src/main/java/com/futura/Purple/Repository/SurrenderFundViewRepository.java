package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.SurrenderFundView;

public interface SurrenderFundViewRepository extends JpaRepository<SurrenderFundView, String> {

	
	@Query(value = "select * from surrender_fund_view where policy_num=:chdrNo ", nativeQuery = true)
	List<SurrenderFundView> getallFundViewByPolicyNo(Long chdrNo);
}
