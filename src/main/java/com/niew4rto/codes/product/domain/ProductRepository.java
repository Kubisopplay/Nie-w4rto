package com.niew4rto.codes.product.domain;

import com.niew4rto.codes.commons.db.BaseRepository;
import java.util.UUID;

public interface ProductRepository extends BaseRepository<Product, UUID> {

  @Override
  default String getEntityName() {
    return "Product";
  }

}
