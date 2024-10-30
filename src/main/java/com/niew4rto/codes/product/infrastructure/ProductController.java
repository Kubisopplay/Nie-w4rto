package com.niew4rto.codes.product.infrastructure;

import com.niew4rto.codes.product.domain.ProductFacade;
import com.niew4rto.codes.product.dto.ProductApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product related operations")
@RequestMapping("/api/product")
public class ProductController {

  private final ProductFacade productFacade;

  @Operation(summary = "Create a product", description = "Create a product with a name, description, price and currency")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
  public ProductApi.Product createProduct(
      @RequestBody @Valid ProductApi.CreateProductRequest request) {
    return productFacade.createProduct(request);
  }

  @Operation(summary = "Retrieve all products", description = "Retrieve all products")
  @GetMapping(path = "/all", produces = MediaType.APPLICATION_XML_VALUE)
  public List<ProductApi.Product> getProducts() {
    return productFacade.getAllProducts();
  }

  @Operation(summary = "Retrieve a product by id", description = "Retrieve a product by id")
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
  public ProductApi.Product getProduct(@PathVariable UUID id) {
    return productFacade.getProduct(id);
  }

  @Operation(summary = "Update a product by id", description = "Update a product by id with a name, description, price or currency")
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
  public ProductApi.Product updateProduct(@PathVariable UUID id,
      @RequestBody @Valid ProductApi.UpdateProductRequest request) {
    return productFacade.updateProduct(id, request);
  }

  @Operation(summary = "Delete a product by id", description = "Delete a product by id")
  @DeleteMapping(path = "/{id}")
  public void deleteProduct(@PathVariable UUID id) {
    productFacade.deleteProduct(id);
  }

}