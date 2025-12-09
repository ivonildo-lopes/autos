package com.loja.autos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.service.EmailService;

@RestController
@RequestMapping(value = "email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public String sendEmail() {
        emailService.sendSimpleEmail("ivonildolopes@gmail.com", "Assunto", "texto teste de envio de email - api autos");
        return "Email enviado com sucesso!";
    }
}
