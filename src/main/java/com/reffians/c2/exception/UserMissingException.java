package com.reffians.c2.exception;

/** User Missing Exception. */
public class UserMissingException extends Exception {
  public UserMissingException(String username) {
    super(String.format("user %s does not exist", username));
  }
}