package com.loja.autos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.loja.autos.dto.request.SmsRequestDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import jakarta.validation.Valid;

@Service
public class SMSAppService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    public void sendSMSAppMessage(@Valid SmsRequestDto request) {
        Twilio.init(accountSid, authToken);
        String enviarPara = request.to().contains("+")? request.to() : "+" + request.to();
		String de = fromPhoneNumber.contains("+")? fromPhoneNumber : "+" + fromPhoneNumber;
		Message.creator(
//                new com.twilio.type.PhoneNumber("whatsapp:" + "+5585999343911"),
//                new com.twilio.type.PhoneNumber("whatsapp:" + "+15075644848"),
//                new com.twilio.type.PhoneNumber("+5585997406510"),
//                new com.twilio.type.PhoneNumber("+15075644848"),
                new com.twilio.type.PhoneNumber(enviarPara.replace(" ", "")),
                new com.twilio.type.PhoneNumber(de.replace(" ", "")),
                request.message())
                .create();
    }
}
