package com.niew4rto.codes.purchase.domain;

import com.niew4rto.codes.commons.db.BaseRepository;
import java.util.UUID;

public interface PurchaseRepository extends BaseRepository<Purchase, UUID> {

  @Override
  default String getEntityName() {
    return "Purchase";
  }
}
