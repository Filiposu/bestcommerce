package com.bestcommerce.services;

import com.bestcommerce.entities.Merchant;

import java.util.List;
import java.util.Optional;

public interface MerchantService {
    List<Merchant> getMerchants();
    Optional<Merchant> getMerchant(long merchantId);
    Merchant getMerchandByUserId(long id);
    Merchant createMerchant(Merchant merchant) throws Exception;
}
