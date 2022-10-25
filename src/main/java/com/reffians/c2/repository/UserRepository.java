package com.reffians.c2.repository;
import java.util.List;

import com.reffians.c2.model.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;



public interface UserRepository extends CrudRepository<User, String>{
  @Query(value = "select * from users where username = :username", nativeQuery = true)
  List<User> findByUsername(@Param("username") String username);

  @Query(value = "select * from users where username = :username and password = :password", 
  nativeQuery = true)
  List<User> findByUnamePword(@Param("username") String username, @Param("password") String password);

  @Modifying
  @Transactional
  @Query(value = "insert into users (username, password) values (:username, :password)", nativeQuery = true)
  void insertUser(@Param("username") String username, @Param("password") String password);
  @Modifying
  @Transactional
  @Query(value = "select beacons from users where username = :username", nativeQuery = true)
  void findBeaconsByUsername(@Param("username") String username);


  @Modifying
  @Transactional
  @Query(value = "update User u set u.beacons = :beacons where u.username = :username", nativeQuery = true)
  void addBeaconToUser(@Param("username") String username, @Param("beacons") List<Beacon> beacons);

}
