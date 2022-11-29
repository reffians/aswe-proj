package com.reffians.c2.repository;

import com.reffians.c2.model.Beacon;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/** Beacon Repository to query beacons table. */
public interface BeaconRepository extends CrudRepository<Beacon, Integer> {
  List<Beacon> findByIdAndToken(Integer id, String token);
}