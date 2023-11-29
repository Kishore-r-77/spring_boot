package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.AnnuityRates;

public interface AnnuityRatesRepository extends JpaRepository<AnnuityRates, Long> {

	@Query(value = "select * from annuity_rates where valid_flag=1", nativeQuery = true)
	List<AnnuityRates> getallActive();

	@Query(value = "select * from annuity_rates where id=:id and valid_flag=1", nativeQuery = true)
	AnnuityRates getActiveById(Long id);
	
	@Query(value = "select annuity_discount_rate from annuity_rates where product_code=:planCode and pending_annuity_years=:dueYears and valid_flag=1", nativeQuery = true)
	Double getActiveByProductCode(String planCode, Double dueYears);
}
