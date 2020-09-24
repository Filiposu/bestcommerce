package com.bestcommerce.services.impl;

import com.bestcommerce.entities.Merchant;
import com.bestcommerce.entities.User;
import com.bestcommerce.repository.MerchantRepository;
import com.bestcommerce.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public Merchant getMerchandByUserId(long id){
        Merchant merchant = merchantRepository.getMerchantByUserId(id).orElseThrow(()->new UsernameNotFoundException("Could not get merchant By user_id = " + id ));
        return merchant;
    }



}
