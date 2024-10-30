package com.niew4rto.codes.product.domain;

import com.niew4rto.codes.product.dto.ProductApi;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Transactional
@RequiredArgsConstructor
@Component
public class ProductFacade {

  private final ProductRepository productRepository;

  public ProductApi.Product createProduct(ProductApi.CreateProductRequest request) {

    Product product = new Product(request);
    productRepository.save(product);
    return product.toDto();
  }

  public List<ProductApi.Product> getAllProducts() {
    return productRepository.findAll().stream()
        .map(Product::toDto)
        .toList();
  }

  public ProductApi.Product getProduct(UUID id) {
    return productRepository.loadById(id).toDto();
  }

  public ProductApi.Product updateProduct(UUID id, ProductApi.UpdateProductRequest request) {
    Product product = productRepository.loadById(id);
    product.update(request);
    return product.toDto();
  }

  public void deleteProduct(UUID id) {
    productRepository.deleteById(id);
  }
}
