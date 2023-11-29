package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.StamDutyMaster;

public interface StamDutyMasterRepository extends JpaRepository<StamDutyMaster, Long> {

	@Query(value = "select * from stam_duty_master where valid_flag=1", nativeQuery = true)
	List<StamDutyMaster> getallActive();

	@Query(value = "select * from stam_duty_master where id=:id and valid_flag=1", nativeQuery = true)
	StamDutyMaster getActiveById(Long id);
	
	@Query(value = "select * from stam_duty_master where uin_number=:uinNumber and valid_flag=1", nativeQuery = true)
	StamDutyMaster getByUniqueNo(String uinNumber);

	@Query(value = "select * from stam_duty_master where  id like %:key% and valid_flag = 1  or  uinNumber like %:key% and valid_flag = 1  or  coverName like %:key% and valid_flag = 1  or  startDate like %:key% and valid_flag = 1  or  endDate like %:key% and valid_flag = 1  or id like %:key% and valid_flag = 1", nativeQuery = true)
	List<StamDutyMaster> globalSearch(String key);

}
