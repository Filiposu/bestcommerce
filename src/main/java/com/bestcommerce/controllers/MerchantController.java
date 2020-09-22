package com.bestcommerce.controllers;


import com.bestcommerce.entities.Merchant;
import com.bestcommerce.entities.Role;
import com.bestcommerce.entities.Role_Enum;
import com.bestcommerce.entities.User;
import com.bestcommerce.repository.MerchantRepository;
import com.bestcommerce.services.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantRepository merchantRepository;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Merchant> getMerchansts(){
        return merchantRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Merchant createMerchant(@RequestBody Merchant merchant){
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication1.getPrincipal();
        User user = new User();
        Set<Role> roles = new HashSet<>();
        for (GrantedAuthority role:userDetails.getAuthorities()) {
            roles.add(new Role(Role_Enum.valueOf(role.getAuthority())));
        }
        user.setId(userDetails.getId());
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setRoles(roles);
        merchant.setUser(user);
        Merchant merchant1 = merchantRepository.save(merchant);
        return merchant1;
    }



}
