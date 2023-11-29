package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.futura.Purple.Entity.SurvivalBenefitRate;

public interface SurvivalBenefitRateRepository extends JpaRepository<SurvivalBenefitRate, Long> {
	
	@Query(value = "select * from survival_benefit_rate where valid_flag=1", nativeQuery = true)
	List<SurvivalBenefitRate> getallActive();

	@Query(value = "select * from survival_benefit_rate where id=:id and valid_flag=1", nativeQuery = true)
	SurvivalBenefitRate getActiveById(Long id);

	@Query(value = "select * from survival_benefit_rate where  id like %:key% and valid_flag = 1  or  company_id like %:key% and valid_flag = 1 or product_code like %:key% and valid_flag = 1 or policy_years like %:key% and valid_flag = 1 or benefit_rate like %:key% and valid_flag = 1", nativeQuery = true)
	List<SurvivalBenefitRate> globalSearch(String key);
	
	@Query(value = "select * from survival_benefit_rate where product_code=:planCode and policy_years <:noOfDues and valid_flag=1", nativeQuery = true)
	List<SurvivalBenefitRate> getallActiveByPlanCode(String planCode, Double noOfDues);

}
