package com.demo.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidPlayerTurnException extends RuntimeException {
  public InvalidPlayerTurnException() {}

  public InvalidPlayerTurnException(String message) {
    super(message);
  }

  public InvalidPlayerTurnException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidPlayerTurnException(Throwable cause) {
    super(cause);
  }

  public InvalidPlayerTurnException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
