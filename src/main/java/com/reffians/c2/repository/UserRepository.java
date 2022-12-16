package com.reffians.c2.repository;

import com.reffians.c2.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** User Repository. Queries the User table in the database. */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
  @Query(value = "select * from users where username = :username", nativeQuery = true)
  List<User> findByUsername(@Param("username") String username);

  @Query(value = "select encoded_password from users where username = :username",
      nativeQuery = true)
  List<String> findEncodedPasswordByUsername(@Param("username") String username);
}