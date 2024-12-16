package com.niew4rto.codes.purchase.domain;

import com.niew4rto.codes.pricecalculator.domain.PriceCalculatorFacade;
import com.niew4rto.codes.product.domain.ProductFacade;
import com.niew4rto.codes.promocode.domain.PromoCodeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PurchaseConfiguration {

  private final PurchaseRepository purchaseRepository;
  private final PriceCalculatorFacade priceCalculatorFacade;
  private final ProductFacade productFacade;
  private final PromoCodeFacade promoCodeFacade;

  @Bean
  PurchaseReportHandler purchaseReportHandler() {
    return new PurchaseReportHandler(purchaseRepository);
  }

  @Bean
  PurchaseFacade purchaseFacade() {
    return new PurchaseFacade(purchaseRepository, priceCalculatorFacade, productFacade,
        promoCodeFacade, purchaseReportHandler());
  }

}
