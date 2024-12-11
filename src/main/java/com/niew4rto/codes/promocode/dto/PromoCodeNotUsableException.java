package com.niew4rto.codes.promocode.dto;

public class PromoCodeNotUsableException extends RuntimeException {

  public PromoCodeNotUsableException(String message) {
    super(message);
  }
}
