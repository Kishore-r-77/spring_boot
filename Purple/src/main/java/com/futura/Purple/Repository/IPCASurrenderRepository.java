package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.IPCASurrender;
import com.futura.Purple.Entity.PurpleDetails;

public interface IPCASurrenderRepository extends JpaRepository<IPCASurrender, Long>{

	@Query(value = "select * from ipca_surrender where valid_flag=1", nativeQuery = true)
	List<IPCASurrender> getallActive();
	
	@Query(value = "select * from ipca_surrender where purple_approval_flag is NULL and valid_flag=1", nativeQuery = true)
	List<IPCASurrender> getAllQCPending();

	@Query(value = "select * from ipca_surrender where id=:id and valid_flag=1", nativeQuery = true)
	IPCASurrender getActiveById(Long id);

	@Query(value = "select * from ipca_surrender where  id like %:key% and valid_flag = 1  or  policy_no like %:key% and valid_flag = 1  or  trans_no like %:key% and valid_flag = 1  or  uin_number like %:key% and valid_flag = 1  or  req_date like %:key% and valid_flag = 1  or  log_date like %:key% and valid_flag = 1", nativeQuery = true)
	List<IPCASurrender> globalSearch(String key);
	
	@Query(value = "select * from ipca_surrender where policy_no=:policyNo and valid_flag=1", nativeQuery = true)
	IPCASurrender getByPolicyNo(Long policyNo);
	
	@Query(value = "select * from ipca_surrender where trans_no=:tranNo and valid_flag=1", nativeQuery = true)
	IPCASurrender getByTransNo(Long tranNo);
	
	@Query(value = "select * from ipca_surrender where purple_approval_flag='Fail' and valid_flag=1", nativeQuery = true)
	List<IPCASurrender> getAllFailedRecord();
	
	@Query(value = "select * from ipca_surrender where approv_flag='Pass' and valid_flag=1", nativeQuery = true)
	List<IPCASurrender> getAllPassedRecord();
}
