package com.reffians.c2.exception;

/** Input validation exception. */
public class InvalidCommandTypeException extends Exception {
  public InvalidCommandTypeException(String commandType) {
    super(String.format("Command type %s is invalid.", commandType));
  }
}
