package com.bestcommerce.services.impl;

import com.bestcommerce.entities.Merchant;
import com.bestcommerce.repository.MerchantRepository;
import com.bestcommerce.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public List<Merchant> getMerchants() {
       return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> getMerchant(long merchantId) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        return merchant;
    }
}
