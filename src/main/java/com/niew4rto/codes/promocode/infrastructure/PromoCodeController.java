package com.niew4rto.codes.promocode.infrastructure;

import com.niew4rto.codes.promocode.domain.PromoCodeFacade;
import com.niew4rto.codes.promocode.dto.PromoCodeApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Promo Code", description = "Promo code related operations")
@RequestMapping("/api/promo-code")
public class PromoCodeController {

  private final PromoCodeFacade promoCodeFacade;

  @Operation(summary = "Create a promo code", description = "Create a promo code with a code, max usages(greater than 0), discount amount(greater than 0), discount type, currency and expiration date")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
  public PromoCodeApi.PromoCode createPromoCode(
      @RequestBody @Valid PromoCodeApi.CreatePromoCodeRequest request) {
    return promoCodeFacade.createPromoCode(request);
  }

  @Operation(summary = "Retrieve all promo codes", description = "Retrieve all promo codes")
  @GetMapping(path = "/all", produces = MediaType.APPLICATION_XML_VALUE)
  public List<PromoCodeApi.PromoCode> getPromoCodes() {
    return promoCodeFacade.getAllPromoCodes();
  }

  @Operation(summary = "Retrieve a promo code by code", description = "Retrieve a promo code by it's code")
  @GetMapping(path = "/{code}", produces = MediaType.APPLICATION_XML_VALUE)
  public PromoCodeApi.PromoCode getPromoCode(@PathVariable String code) {
    return promoCodeFacade.getPromoCode(code);
  }

}
