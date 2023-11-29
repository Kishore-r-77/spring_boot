package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.DeathClaimFundView;

public interface DeathClaimFundViewRepository extends JpaRepository<DeathClaimFundView, String> {
	
	@Query(value = "select * from death_claim_fund_view where uin_number=:uinNumber ", nativeQuery = true)
	List<DeathClaimFundView> getallFundViewByUinNo(String uinNumber);

}
