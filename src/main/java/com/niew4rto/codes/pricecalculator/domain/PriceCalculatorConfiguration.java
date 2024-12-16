package com.niew4rto.codes.pricecalculator.domain;

import com.niew4rto.codes.product.domain.ProductFacade;
import com.niew4rto.codes.promocode.domain.PromoCodeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PriceCalculatorConfiguration {

  private final PromoCodeFacade promoCodeFacade;
  private final ProductFacade productFacade;

  @Bean
  public PriceCalculatorFacade priceCalculatorFacade() {
    return new PriceCalculatorFacade(productFacade, promoCodeFacade);
  }

}
