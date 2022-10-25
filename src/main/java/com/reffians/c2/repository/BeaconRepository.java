package com.reffians.c2.repository;
import java.util.List;

import com.reffians.c2.model.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BeaconRepository extends CrudRepository<Beacon, Integer>{
  @Modifying
  @Transactional
  @Query(value = "insert into beacons (username) values (:username)", nativeQuery = true)
  void createBeacon(@Param("username") String username);
}