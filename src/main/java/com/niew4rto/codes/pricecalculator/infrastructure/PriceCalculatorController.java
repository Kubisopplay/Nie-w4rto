package com.niew4rto.codes.pricecalculator.infrastructure;

import com.niew4rto.codes.pricecalculator.domain.PriceCalculatorFacade;
import com.niew4rto.codes.pricecalculator.dto.PriceCalculatorApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Price Calculator", description = "Price calculator related operations")
@RequestMapping("/api/calculate-price")
public class PriceCalculatorController {

  private final PriceCalculatorFacade priceCalculatorFacade;

  @Operation(summary = "Calculate price", description = "Calculate price for a product with a promo code")
  @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
  public PriceCalculatorApi.CalculatedPrice calculatePrice(
      @RequestBody @Valid PriceCalculatorApi.CalculatePriceRequest request) {
    return priceCalculatorFacade.calculatePrice(request);
  }

}
