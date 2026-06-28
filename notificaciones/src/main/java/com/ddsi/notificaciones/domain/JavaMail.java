package com.ddsi.notificaciones.domain;

@Service
public class JavaMail implements EnvioEmailAdapter {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailUser;

    public JavaMail(String mailUser) {
        this.mailUser = mailUser;
    }

    @Override
    public void enviarMail(String direccion, String mensaje) {

        SimpleMailMessage mail =
                new SimpleMailMessage();

        mail.setFrom(mailUser);
        mail.setTo(direccion);
        mail.setSubject("Notificación");
        mail.setText(mensaje);

        mailSender.send(mail);
    }
}
