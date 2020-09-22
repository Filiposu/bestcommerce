package com.bestcommerce.controllers;


import com.bestcommerce.entities.Merchant;
import com.bestcommerce.repository.MerchantRepository;
import com.bestcommerce.services.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/merchant")
public class MerchantController {

    @Autowired
    private MerchantRepository merchantRepository;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Merchant createMerchant(){
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication1.getPrincipal();
        System.out.println( userDetails.getId());
        return null;
    }

}
