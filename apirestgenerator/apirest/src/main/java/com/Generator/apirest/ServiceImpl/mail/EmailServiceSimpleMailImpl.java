package com.Generator.apirest.ServiceImpl.mail;


import com.Generator.apirest.modelo.back.mail.Correo;
import com.Generator.apirest.modelo.back.mail.CuerpoDeCorreo;
import com.Generator.apirest.services.mail.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service("emailService")
public class EmailServiceSimpleMailImpl implements EnvioEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage preConfiguredMessage;


    /**
     * This method will send compose and send the message
     */
    @Override
    public void sendMail(Correo mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mailSender.send(message);
    }


    @Override
    public int sendEmails(List<String> correos, CuerpoDeCorreo cuerpo) {
        int contador = 0;
        for (String correo : correos) { // recorremos la lista y enviamos a cada cliente el mismo correo
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(correo);
            email.setSubject(cuerpo.getSubject());
            email.setText(cuerpo.getContent());
            mailSender.send(email);
            contador++;
        }
        return contador;
    }

    /**
     * This method will send a pre-configured message
     */

    @Override
    public void sendPreConfiguredMail(String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    @Override
    public void sendPreConfiguredMailR() {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailSender.send(mailMessage);
    }


    @Override
    public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(body);

                FileSystemResource file = new FileSystemResource(new File(fileToAttach));
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.addAttachment("logo.jpg", file);
            }
        };
        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public void sendMailWithInlineResources(String to, String subject, String fileToAttach) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
                mimeMessage.setSubject(subject);

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);

                FileSystemResource res = new FileSystemResource(new File(fileToAttach));
                helper.addInline("identifier1234", res);
            }
        };
        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}