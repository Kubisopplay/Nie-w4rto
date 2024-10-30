package com.niew4rto.codes.product.domain;

import com.niew4rto.codes.commons.db.RandomStringGenerator;
import com.niew4rto.codes.commons.enums.Currency;
import com.niew4rto.codes.product.dto.ProductApi;
import com.niew4rto.codes.product.dto.ProductApi.CreateProductRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
class Product {

  @Id
  @NotNull
  private UUID id;

  @NotBlank
  private String name;

  private String description;

  @NotNull
  private BigDecimal price;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Currency currency;

  Product(CreateProductRequest request) {
    this.id = RandomStringGenerator.randomUUID();
    this.name = request.getName();
    this.description = request.getDescription();
    this.price = request.getPrice();
    this.currency = request.getCurrency();
  }

  ProductApi.Product toDto() {
    return new ProductApi.Product(
        id,
        name,
        description,
        price,
        currency
    );
  }

  public void update(ProductApi.UpdateProductRequest request) {
    if (request.getName() != null) {
      this.name = request.getName();
    }
    if (request.getDescription() != null) {
      this.description = request.getDescription();
    }
    if (request.getPrice() != null) {
      this.price = request.getPrice();
    }
    if (request.getCurrency() != null) {
      this.currency = request.getCurrency();
    }
  }
}