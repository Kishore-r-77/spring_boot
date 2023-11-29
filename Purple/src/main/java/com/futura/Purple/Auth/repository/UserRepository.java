package com.futura.Purple.Auth.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.futura.Purple.Auth.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  
  Optional<User> findByEmail(String email);
  
  @Query(value = "select * from users u where u.verification_code = :code", nativeQuery = true)
  User findByVerificationCode(String code);
  
  @Query(value = "select * from users where user_group_id = :id and valid_flag = 1", nativeQuery = true)
  List<User> getByUserGroupId(Long id);


  @Query(value = "select * from users u where u.email = :code", nativeQuery = true)
  User findByMail(String code);
}
