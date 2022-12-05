package com.reffians.c2.service;

import com.reffians.c2.model.Result;
import com.reffians.c2.repository.ResultRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Result Service Class. **/
@Service
public class ResultService {
  @Autowired
  private ResultRepository resultRepository;

  /** Post to results table. */
  public Result addResult(Integer commandid, String username, String content) {
    Result r = new Result(commandid, username, content);
    return resultRepository.save(r);
  }

  /** Get a list of results by username and has_been_read=false.

    * @param username String username
    * @return list of command objects associated with the specified beaconid and
    *     status.
    */
  public List<Result> getNotReceivedResults(String username) {
    return resultRepository.findByUsernameStatus(username, false);
  }

  // /** Check whether a beacon exists.
  //  *
  //  * @param beaconid id corresponding to the beacon.
  //  * @param token token corresponding to the beacon.
  //  * @return a boolean indicating whether the beacon exists.
  //  */
  // public boolean beaconExists(Integer beaconid, String token) {
  //   return !beaconRepository.findByIdAndToken(beaconid, token).isEmpty();
  // }

  // /** Find the username of the user this beacon is associated with
  //  *
  //  * @param beaconid id corresponding to the beacon.
  //  * @return a string with the username
  //  */
  // public String getUserForBeacon(Integer beaconid) {
  //   return beaconRepository.findUserForBeacon(beaconid);
  // }
}