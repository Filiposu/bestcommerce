package com.bestcommerce.controllers;

import com.bestcommerce.utils.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    private EmailServiceImpl emailService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void sendEmail(){
        System.out.println("Trying to send email ..");
        emailService.sendSimpleMessage("togrul125@gmail.com","Hello",simpleMailMessage.getText());
    }

}
