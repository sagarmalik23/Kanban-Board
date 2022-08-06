package com.niit.MailService.service;

import com.niit.MailService.model.EmailDetails;

public interface EmailService {
    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);
//    String sendMailWithAttachment(EmailDetails details);
}
