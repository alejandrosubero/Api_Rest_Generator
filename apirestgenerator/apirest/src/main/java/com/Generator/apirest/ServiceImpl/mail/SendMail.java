package com.Generator.apirest.ServiceImpl.mail;

import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.LinkedList;
import java.util.Properties;

@Service
public class SendMail {

    private String destino;
    private String envia;
    private String pass;
    private String mensage;
    private String adress;
    private String name;
    private String asunto;
    private String a2, a6;
    private static boolean hotmailOrGmail;
    private int contador;
    private String enviadoACorreo;
    private static LinkedList<String> correos = new LinkedList();

    public SendMail() {	}


    public SendMail(String envia, String pass, String asunto, String adress, String name, String mensage) {

        this.envia = envia;
        this.pass = pass;
        this.asunto = asunto;
        this.adress = adress;
        this.name = name;
        this.mensage = mensage;
        correos = new LinkedList();
        destino = "";
    }


    public void tipeEmail(int caso) {//metodo de seleccion de cuerpo o con adjunto usando las librerias.
        if (caso==0) {
            sendMaillCmailProps();
        }else if (caso==1) {
            sendMessegeOnli();
        }
    }


    public void pruebasend() {
        for (int i = 0; i < correos.size(); i++) {
            destino = correos.get(i);
        }
    }
    /// se le pude dar la occion de pasar solo las genenricas en al caso que no tenga base de datos o implimentar el extaraerlas de un txt.


    public void Send() {
        for (int i = 0; i < correos.size(); i++) {
            contador += i;
            destino = correos.get(i);
            sendMaillCmailProps();
        }
    }


    public void Send2() {
        for (int i = 0; i < correos.size(); i++) {
            contador += i;
            destino = correos.get(i);
            sendMessegeOnli();
        }
    }


    public void sendMaillCmailProps() {
        if (hotmailOrGmail) {
            a2 = "smtp.live.com";
            a6 = "25";
        } else {
            a2 = "smtp.gmail.com";
            a6 = "587";
        }

        try {
            Properties mailProps = new Properties();
            mailProps.setProperty("mail.transport.protocol", "smtp");
            mailProps.setProperty("mail.smtp.host", a2);
            mailProps.setProperty("mail.smtp.starttls.enable", "true");
            mailProps.setProperty("mail.smtp.port", a6);
            mailProps.setProperty("mail.smtp.user", envia);
            mailProps.setProperty("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(mailProps, null);
            session.setDebug(true);
            // Se compone la parte del texto ////
            BodyPart text = new MimeBodyPart();
            text.setText(mensage);
            BodyPart attached = new MimeBodyPart();
            attached.setDataHandler(new DataHandler(new FileDataSource(adress)));
            attached.setFileName(name);

            // Una MultiParte para agrupar texto y adjunto.
            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(text);
            multiPart.addBodyPart(attached);
            // Se compone el correo, dando to, from, subject y el contenido.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(envia));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            message.setSubject(asunto);
            message.setContent(multiPart);
            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            t.connect(envia, pass);
            t.sendMessage(message, message.getAllRecipients());
            t.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMaillCmessage() {
        if (hotmailOrGmail) {
            a2 = "smtp.live.com";
            a6 = "25";
        } else {
            a2 = "smtp.gmail.com";
            a6 = "587";
        }

        for (int i = 0; i < correos.size(); i++) {
            destino = correos.get(i);
            try {
                Properties props = new Properties();
                props.setProperty("mail.smtp.host", a2);
                props.setProperty("mail.smtp.starttls.enable", "true");
                props.setProperty("mail.smtp.port", a6);
                props.setProperty("mail.smtp.user", envia);
                props.setProperty("mail.smtp.auth", "true");

                // Preparamos la sesion
                Session session = Session.getDefaultInstance(props);

                // Construimos el mensaje
                BodyPart text = new MimeBodyPart();
                text.setText(mensage);
                MimeMessage message = new MimeMessage(session);

                message.setFrom(new InternetAddress(envia));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
                message.setSubject(asunto);

                MimeBodyPart messageBodyPart = new MimeBodyPart();
                String archivo = adress;
                DataSource src = new FileDataSource(archivo);
                messageBodyPart.setDataHandler(new DataHandler(src));
                messageBodyPart.setFileName(name);

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(text);
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);

                Transport t = session.getTransport("smtp");
                t.connect(envia, pass);
                t.sendMessage(message, message.getAllRecipients());
                // Cierre.
                t.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // System.out.println(" Mensaje enviado");

        }
    }

    public void sendMessegeOnli() {
        if (hotmailOrGmail) {
            a2 = "smtp.live.com";
            a6 = "25";
        } else {
            a2 = "smtp.gmail.com";
            a6 = "587";
        }

        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", a2); // El servidor SMTP de Google
            props.put("mail.smtp.user", envia);
            props.put("mail.smtp.clave", pass); // *** //La clave de la cuenta
            props.put("mail.smtp.auth", "true"); // Usar autenticaciï¿½n mediante usuario y clave
            props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
            props.put("mail.smtp.port", a6); // El puerto SMTP seguro de Google

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(envia));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino)); // Se podrï¿½an aï¿½adir varios de
            // la misma manera
            message.setSubject(asunto);
            message.setText(mensage);
            Transport transport = session.getTransport("smtp");
            transport.connect(a2, envia, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace(); // Si se produce un error
        }
    }

    
    public static LinkedList<String> getCorreos() { return correos;  }
    public void setCorreos(LinkedList<String> correos) { this.correos = correos;  }
    public static boolean isHotmailOrGmail() { return hotmailOrGmail; }
    public static void setHotmailOrGmail(boolean hotmailOrGmail) { SendMail.hotmailOrGmail = hotmailOrGmail;  }
    public String getMensage() { return mensage; }
    public void setMensage(String mensage) { this.mensage = mensage; }
    public int getContador() { return contador; }

}
