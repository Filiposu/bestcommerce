package com.bestcommerce.services;

import com.bestcommerce.entities.Merchant;

import java.util.List;
import java.util.Optional;

public interface MerchantService {
    public List<Merchant> getMerchants();
    public Optional<Merchant> getMerchant(long merchantId);
    public Merchant getMerchandByUserId(long id);
}
