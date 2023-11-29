package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.MedicalDetails;

public interface MedicalDetailsRepository extends JpaRepository<MedicalDetails, Long> {

	@Query(value = "select * from medical_details where valid_flag=1", nativeQuery = true)
	List<MedicalDetails> getallActive();

	@Query(value = "select * from medical_details where id=:id and valid_flag=1", nativeQuery = true)
	MedicalDetails getActiveById(Long id);

	@Query(value = "select * from medical_details where  id like %:key% and valid_flag = 1  or  companyCode like %:key% and valid_flag = 1  or  mfRate like %:key% and valid_flag = 1  or  startDate like %:key% and valid_flag = 1  or  endDate like %:key% and valid_flag = 1  or id like %:key% and valid_flag = 1", nativeQuery = true)
	List<MedicalDetails> globalSearch(String key);
	
	@Query(value = "select mf_rate from medical_details where medical_category=:category and medical_center=:center and tpa_code=:tpaCpde and valid_flag=1", nativeQuery = true)
	Long getMfRate(String category,String center,String tpaCpde);

}