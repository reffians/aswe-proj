package com.reffians.c2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.reffians.c2.model.Command;

public interface CommandRepository extends CrudRepository<Command, Integer> {
  @Query(value = "select * from command where beaconid = :beaconid", nativeQuery = true)
  List<Command> findByBeaconid(@Param("beaconid") Integer beaconid);

  @Query(value = "select * from command where (beaconid = :beaconid and status = :status)", nativeQuery = true)
  List<Command> findByBeaconidStatus(@Param("beaconid") Integer beaconid, @Param("status") String status);
  
  //querys for submit command batch
  @Query(value = "select * from command where (beaconid = :beaconid and id = :commandid)", nativeQuery = true)
  List<Command> findCommandByid(@Param("beaconid") Integer beaconid, @Param("id") Integer commandid);

  @Modifying
  @Query(value = "update command set status = :status where id = :commandid, beaconid = :beaconid", nativeQuery = true)
  void updateCommand(@Param("status") String status, @Param("beaconid") Integer beaconid, @Param("id") Integer commandid);
}
