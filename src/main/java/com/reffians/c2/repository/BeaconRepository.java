package com.reffians.c2.repository;

import com.reffians.c2.model.Beacon;
import org.springframework.data.repository.CrudRepository;

/** Beacon Repository to query beacons table. */
public interface BeaconRepository extends CrudRepository<Beacon, Integer> {
}