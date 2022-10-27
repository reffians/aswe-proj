package com.reffians.c2.repository;

import com.reffians.c2.model.Beacon;
import com.reffians.c2.model.Command;
import com.reffians.c2.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/** User Repository. Queries the User table in the database. **/
public interface UserRepository extends CrudRepository<User, String> {
  /** findByUsername
   * Find username and password given a username.
   **/
  @Query(value = "select * from users where username = :username", nativeQuery = true)
  List<User> findByUsername(@Param("username") String username);

  /** findByUnamePword
   * Check if username and password match an entry in the database.
   **/
  @Query(value = "select * from users where username = :username and password = :password", 
      nativeQuery = true)
  List<User> findByUnamePword(@Param("username") String username, 
      @Param("password") String password);

  /*
   * findPwordByUser
   * get password given username
   */
  @Query(value = "select password from users where username = :username", nativeQuery = true)
  List<String> findPwordByUser(@Param("username") String username);

  /** insertUser
   * Add new user to the database with a given username and password.
   **/
  @Modifying
  @Transactional
  @Query(value = "insert into users (username, password) values (:username, :password)",
      nativeQuery = true)
  void insertUser(@Param("username") String username, @Param("password") String password);
}