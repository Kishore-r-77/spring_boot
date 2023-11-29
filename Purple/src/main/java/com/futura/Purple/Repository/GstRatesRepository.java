package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.futura.Purple.Entity.GstRates;

public interface GstRatesRepository extends JpaRepository<GstRates, Long>{
	@Query(value = "select * from gst_rates where valid_flag=1", nativeQuery = true)
	List<GstRates> getallActive();

	@Query(value = "select * from gst_rates where id=:id and valid_flag=1", nativeQuery = true)
	GstRates getActiveById(Long id);

	@Query(value = "select * from gst_rates where  id like %:key% and valid_flag = 1", nativeQuery = true)
	List<GstRates> globalSearch(String key);
	
	@Query(value = "call getGstOnPremRate(:startDate,:planCode,:premYear)", nativeQuery = true)
	Double getGstOnPremRate(@Param("startDate") String startDate,@Param("planCode") String planCode,@Param("premYear") Double premYear );

}
