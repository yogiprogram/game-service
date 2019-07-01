package com.demo.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidPlayerTurn extends RuntimeException {
  public InvalidPlayerTurn() {}

  public InvalidPlayerTurn(String message) {
    super(message);
  }

  public InvalidPlayerTurn(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidPlayerTurn(Throwable cause) {
    super(cause);
  }

  public InvalidPlayerTurn(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
