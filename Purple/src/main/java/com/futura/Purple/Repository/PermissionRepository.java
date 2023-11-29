package com.futura.Purple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.futura.Purple.Entity.Permissions;



public interface PermissionRepository extends JpaRepository<Permissions, Long> {


    @Query(value = "select * from permissions where user_group_id = :userGroupId and method = :method and service_id = :serviceId", nativeQuery = true)
    List<Permissions> isMethodPresent(Long userGroupId, Long serviceId, String method);
    
    @Query(value = "select * from permissions where user_id = :userId and method = :method and service_id = :serviceId", nativeQuery = true)
    List<Permissions> isMethodPresentUser(Long userId, Long serviceId, String method);

    @Query(value = "select method from permissions", nativeQuery = true)
    List<String> allPermissionMethods();


}
