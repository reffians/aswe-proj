package com.reffians.c2.repository;

import com.reffians.c2.model.Command;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/** A Command Repository representing the table of commands. **/
public interface CommandRepository extends CrudRepository<Command, Integer> {
  @Query(value = "select * from command where beaconid = :beaconid", nativeQuery = true)
  List<Command> findByBeaconid(@Param("beaconid") Integer beaconid);

  @Query(value = "select * from command where (beaconid = :beaconid and status = :status)",
      nativeQuery = true)
  List<Command> findByBeaconidStatus(@Param("beaconid") Integer beaconid,
      @Param("status") String status);

  @Modifying
  @Transactional
  @Query(value = "insert into command (beaconid, content, status) values (:beaconid," 
      + ":content, :status)", nativeQuery = true)
  void insertCommand(@Param("beaconid") Integer beaconid, @Param("content") String content, 
      @Param("status") String status);
  
}

