package com.reffians.c2.exception;

/** User Exists Exception. */
public class UserExistsException extends Exception {
  public UserExistsException(String username) {
    super(String.format("user %s already exists", username));
  }
}