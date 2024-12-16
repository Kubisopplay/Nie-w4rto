package com.niew4rto.codes.purchase.infrastructure;

import com.niew4rto.codes.purchase.domain.PurchaseFacade;
import com.niew4rto.codes.purchase.dto.PurchaseApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Purchase", description = "Purchase related operations")
@RequestMapping("/api/purchase")
public class PurchaseController {

  private final PurchaseFacade purchaseFacade;

  @Operation(summary = "Create a purchase", description = "Create a purchase from a product and a promo code")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
  public PurchaseApi.Purchase createPurchase(
      @RequestBody @Valid PurchaseApi.CreatePurchaseRequest request) {
    return purchaseFacade.createPurchase(request);
  }

  @Operation(summary = "Generate a report of purchases", description = "Generate a report of purchases with total amount, total discount and number of purchases by currency")
  @GetMapping(path = "/report", produces = MediaType.APPLICATION_XML_VALUE)
  public PurchaseApi.PurchaseReport generateSalesReport() {
    return purchaseFacade.generateSalesReport();
  }

}
