package com.niew4rto.codes.promocode.dto;

public class PromoCodeAlreadyExistsException extends RuntimeException {

  public PromoCodeAlreadyExistsException(String message) {
    super(message);
  }
}
