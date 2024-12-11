package com.niew4rto.codes.promocode.domain;

import com.niew4rto.codes.commons.db.BaseRepository;

public interface PromoCodeRepository extends BaseRepository<PromoCode, String> {

  @Override
  default String getEntityName() {
    return "PromoCode";
  }
}
