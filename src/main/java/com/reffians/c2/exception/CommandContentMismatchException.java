package com.reffians.c2.exception;

/** Input validation exception. */
public class CommandContentMismatchException extends Exception {
  public CommandContentMismatchException(String commandType, String content) {
    super(String.format("Content %s does not match command type %s.", content, commandType));
  }
}
