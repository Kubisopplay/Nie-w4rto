package com.niew4rto.codes.promocode.domain;


import static com.niew4rto.codes.commons.enums.Usability.ACTIVE;
import static com.niew4rto.codes.commons.enums.Usability.DEPLETED;
import static com.niew4rto.codes.commons.enums.Usability.EXPIRED;

import com.niew4rto.codes.commons.enums.Currency;
import com.niew4rto.codes.commons.enums.DiscountType;
import com.niew4rto.codes.commons.enums.Usability;
import com.niew4rto.codes.promocode.dto.PromoCodeApi;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promo_code")
@NoArgsConstructor
public class PromoCode {

  @Id
  @NotBlank
  private String code;

  @NotNull
  private int maxUsages;

  @NotNull
  private int currentUsages;

  @NotNull
  private Usability usability;

  @NotNull
  private BigDecimal amount;

  @NotNull
  private Currency currency;

  @NotNull
  private DiscountType discountType;

  @NotNull

  private LocalDate expirationDate;

  PromoCode(PromoCodeApi.CreatePromoCodeRequest request) {
    this.code = request.getCode();
    this.maxUsages = request.getMaxUsages();
    this.currentUsages = 0;
    this.usability = ACTIVE;
    this.amount = request.getAmount().setScale(2);
    this.currency = request.getCurrency();
    this.expirationDate = request.getExpirationDate();
    this.discountType = request.getDiscountType();
  }

  PromoCodeApi.PromoCode toDto() {
    return new PromoCodeApi.PromoCode(
        code,
        maxUsages,
        currentUsages,
        usability,
        amount,
        currency,
        expirationDate,
        discountType
    );
  }

  boolean isExpired() {
    return LocalDate.now().isAfter(expirationDate);
  }

  void expireCode() {
    this.usability = EXPIRED;
  }

  void useCode() {
    this.currentUsages++;
    if (currentUsages >= maxUsages) {
      this.usability = DEPLETED;
    }
  }

  boolean isActive() {
    return usability == ACTIVE;
  }

}
