package com.exam.sbb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor Not Found")
public class DataNotFoundException extends RuntimeException {
  public DataNotFoundException(String questionNotFound) {
  }
}
