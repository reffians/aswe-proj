package com.reffians.c2.service;

import com.reffians.c2.model.Beacon;
import com.reffians.c2.repository.BeaconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Beacon Service Class. **/
@Service
public class BeaconService {
  @Autowired
  private BeaconRepository beaconRepository;

  /**
   * Method to register beacon.

   * @param username username of the user that 'owns' this beacon
   */
  public Beacon registerBeacon(String username) {
    return beaconRepository.save(new Beacon(username));
  }
}