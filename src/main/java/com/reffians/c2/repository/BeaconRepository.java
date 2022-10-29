package com.reffians.c2.repository;

import com.reffians.c2.model.Beacon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/** Beacon Repository to query beacons table. */
public interface BeaconRepository extends CrudRepository<Beacon, Integer> {
}