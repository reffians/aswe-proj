package com.reffians.c2.repository;

import com.reffians.c2.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/** User Repository. Queries the User table in the database. **/
public interface UserRepository extends CrudRepository<User, String> {
  /** findByUsername
   * Find username and password given a username.
   **/
  @Query(value = "select * from users where username = :username", nativeQuery = true)
  List<User> findByUsername(@Param("username") String username);

  /*
   * findPwordByUser
   * get password given username
   */
  @Query(value = "select encoded_password from users where username = :username",
      nativeQuery = true)
  List<String> findEncodedPasswordByUsername(@Param("username") String username);
}