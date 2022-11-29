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

  /** Register a beacon.
   *
   * @param username username of the user that 'owns' this beacon
   * @return saved beacon object.
   */
  public Beacon registerBeacon(String username) {
    return beaconRepository.save(new Beacon(username));
  }

  /** Check whether a beacon exists.
   *
   * @param beaconid id corresponding to the beacon.
   * @param token token corresponding to the beacon.
   * @return a boolean indicating whether the beacon exists.
   */
  public boolean beaconExists(Integer beaconid, String token) {
    return !beaconRepository.findByIdAndToken(beaconid, token).isEmpty();
  }
}