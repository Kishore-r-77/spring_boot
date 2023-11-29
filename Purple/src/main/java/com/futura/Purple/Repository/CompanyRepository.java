package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@Query(value = "select * from company where valid_flag=1", nativeQuery = true)
	List<Company> getallActive();

	@Query(value = "select * from company where id=:id and valid_flag=1", nativeQuery = true)
	Company getActiveById(Long id);

	@Query(value = "select * from company where id like %:key% and valid_flag = 1 or company_code like %:key% and valid_flag = 1 or company_name like %:key% and valid_flag = 1 or company_short_name like %:key%  and valid_flag = 1 or company_long_name like %:key%  and valid_flag = 1", nativeQuery = true)
	List<Company> globalSearch(String key);

}
