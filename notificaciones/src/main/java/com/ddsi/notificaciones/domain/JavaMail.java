package com.ddsi.notificaciones.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class
JavaMail implements EnvioEmailAdapter {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailUser;

    public JavaMail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarMail(String direccion, String mensaje) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(mailUser);
        mail.setTo(direccion);
        mail.setSubject("no-reply-donatrack");
        mail.setText(mensaje);
        mailSender.send(mail);
    }
}