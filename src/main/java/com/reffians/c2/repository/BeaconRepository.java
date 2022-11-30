package com.reffians.c2.repository;

import com.reffians.c2.model.Beacon;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/** Beacon Repository to query beacons table. */
public interface BeaconRepository extends CrudRepository<Beacon, Integer> {
  List<Beacon> findByIdAndToken(Integer id, String token);

  /*
   * find username by beacon id
   * get password given username
   */
  @Query(value = "select username from beacons where id = :beaconid",
      nativeQuery = true)
  String findUserForBeacon(@Param("beaconid") int beaconid);
}