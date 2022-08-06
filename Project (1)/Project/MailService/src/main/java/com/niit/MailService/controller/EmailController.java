package com.niit.MailService.controller;



import com.niit.MailService.model.EmailDetails;
import com.niit.MailService.model.Message;
import com.niit.MailService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody EmailDetails details) {
        String status
                = emailService.sendSimpleMail(details);

        return new ResponseEntity<>(new Message("info",status), HttpStatus.OK);
    }

    // Sending email with attachment
//    @PostMapping("/sendMailWithAttachment")
//    public String sendMailWithAttachment(
//            @RequestBody EmailDetails details)
//    {
//        String status
//                = emailService.sendMailWithAttachment(details);
//
//        return status;
//    }
//}
}