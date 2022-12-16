package com.reffians.c2.repository;

import com.reffians.c2.model.commands.Command;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** A Command Repository representing the table of commands. */
@Repository
public interface CommandRepository extends CrudRepository<Command, Integer> {
  @Query(value = "select * from commands where beaconid = :beaconid", nativeQuery = true)
  List<Command> findByBeaconid(@Param("beaconid") Integer beaconid);

  @Query(value = 
      "select * from commands where (beaconid = :beaconid and has_been_sent = :hasBeenSent)",
      nativeQuery = true)
  List<Command> findByBeaconidStatus(@Param("beaconid") Integer beaconid,
      @Param("hasBeenSent") boolean hasBeenSent);

  @Query(value = "select beaconid from commands where id = :commandid",
      nativeQuery = true)
  int findBeaconForCommand(@Param("commandid") int commandid);
}

