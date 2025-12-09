package com.loja.autos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.producers.MessageProducer;

@RestController
@RequestMapping(value = "rabbitmq")
public class RabbitMQController {

    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/produz")
    public String sendMessage(@RequestParam("message") String message) {
        messageProducer.sendMessage(message);
        return "Mensagem enviada: " + message;
    }
}
