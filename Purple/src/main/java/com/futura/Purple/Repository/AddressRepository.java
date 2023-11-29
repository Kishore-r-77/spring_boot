package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.futura.Purple.Entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query(value = "select * from address where valid_flag=1", nativeQuery = true)
	List<Address> getallActive();

	@Query(value = "select * from address where id=:id and valid_flag=1", nativeQuery = true)
	Address getActiveById(Long id);

	@Query(value = "select * from address where id like %:key% and valid_flag = 1 or postal_code like %:key% and valid_flag = 1 or address1 like %:key%  and valid_flag = 1 or address2 like %:key%  and valid_flag = 1 or address3 like %:key%  and valid_flag = 1 or address4 like %:key% and valid_flag = 1 or address5 like %:key% and valid_flag = 1 or district like %:key% and valid_flag = 1 or state like %:key% and valid_flag = 1 or country like %:key% and valid_flag = 1 or mobile like %:key% and valid_flag = 1", nativeQuery = true)
	List<Address> globalSearch(String key);

}
