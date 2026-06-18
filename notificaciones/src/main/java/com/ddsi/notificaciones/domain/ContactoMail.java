package com.ddsi.notificaciones.domain;

@Service
public class ContactoMail implements Contacto {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailUser;

    public ContactoMail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarMensaje(String mensaje, String direccion) {

        SimpleMailMessage mail =
                new SimpleMailMessage();

        mail.setFrom(mailUser);
        mail.setTo(direccion);
        mail.setSubject("Notificación");
        mail.setText(mensaje);

        mailSender.send(mail);
    }
}
