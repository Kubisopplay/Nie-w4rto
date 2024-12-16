package com.niew4rto.codes.purchase.domain;

import static com.niew4rto.codes.pricecalculator.dto.PriceCalculatorApi.CalculatePriceRequest.fromPurchase;

import com.niew4rto.codes.pricecalculator.domain.PriceCalculatorFacade;
import com.niew4rto.codes.pricecalculator.dto.PriceCalculatorApi;
import com.niew4rto.codes.product.domain.ProductFacade;
import com.niew4rto.codes.product.dto.ProductApi;
import com.niew4rto.codes.promocode.domain.PromoCodeFacade;
import com.niew4rto.codes.purchase.dto.PurchaseApi;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class PurchaseFacade {

  private final PurchaseRepository purchaseRepository;
  private final PriceCalculatorFacade priceCalculatorFacade;
  private final ProductFacade productFacade;
  private final PromoCodeFacade promoCodeFacade;
  private final PurchaseReportHandler purchaseReportHandler;

  public PurchaseApi.Purchase createPurchase(PurchaseApi.CreatePurchaseRequest request) {

    var calculatedPrice = priceCalculatorFacade.calculatePrice(fromPurchase(request));
    var product = productFacade.getProduct(request.getProductId());

    PurchaseApi.Purchase purchaseDto = processPurchase(calculatedPrice, product);
    promoCodeFacade.usePromoCode(request.getPromoCode());

    return purchaseDto;
  }

  private PurchaseApi.Purchase processPurchase(PriceCalculatorApi.CalculatedPrice calculatedPrice,
      ProductApi.Product product) {

    PurchaseApi.Purchase purchaseDto = new PurchaseApi.Purchase(
        product.getId(),
        product.getName(),
        product.getPrice(),
        calculatedPrice.getDiscountAmount(),
        product.getCurrency(),
        LocalDate.now()
    );

    purchaseRepository.save(new Purchase(purchaseDto));
    return purchaseDto;
  }

  public PurchaseApi.PurchaseReport generateSalesReport() {
    return purchaseReportHandler.generateSalesReport();
  }

}
