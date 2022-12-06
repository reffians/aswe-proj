package com.reffians.c2.repository;

import com.reffians.c2.model.Result;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** A Result Repository representing the table of commands. */
@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {
  @Query(value = 
      "select * from results where (username = :username and has_been_read = :hasBeenRead)",
      nativeQuery = true)
  List<Result> findByUsernameStatus(@Param("username") String username,
      @Param("hasBeenRead") boolean hasBeenRead);
}

