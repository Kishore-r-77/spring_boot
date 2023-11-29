package com.futura.Purple.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.ParameterTable;

import java.util.List;

public interface ParameterRepository extends JpaRepository<ParameterTable, Long> {


    @Query(value = "select * from parameter_table where rule = :rule", nativeQuery = true)
    List<ParameterTable> getParameterwithRuleone(String rule);

}
