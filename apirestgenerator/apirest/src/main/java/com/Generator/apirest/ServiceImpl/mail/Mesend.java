package com.Generator.apirest.ServiceImpl.mail;


import com.Generator.apirest.modelo.back.mail.Emailcfg;
import com.Generator.apirest.services.mail.MensendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class Mesend implements MensendService {

    @Autowired
    private Emailcfg emailcfg;

    private static String destino;
    private static String envia;
    private static String pass;
    private static String mensage;
    private static String asunto;

    private String a2,a6;
    private static boolean hotmailOrGmail;
    private int contador;
    private String enviadoACorreo;

    public static String jsoncuerpo;

    
    
	@Override
	public void sendMailResponse() {
		/*destino =  "alguien@servidor.com"; //A quien le quieres escribir.
	    asunto = "Correo de prueba enviado desde Java";
	    mensage = "Esta es una prueba de correo...";*/

        destino="alejandrosubero53@gmail.com";
        asunto="Primer mensaje 2 ";
        envia = emailcfg.getUsername();
        pass = emailcfg.getPassword();


        mensage = "ANACODE, Notifica \r\n" +
                " \r\n" +
                "Mi nombre es Alejandro Subero, Gracias por usar el servicio AnaCode para generar su proyecto " +
                "espero que le sea util.\r\n" +
                "\r\n" +
                "Mis objetivos profesionales se centran en seguir creciendo en puestos de responsabilidad dentro de la industria informática.\r\n" +
                "\r\n" +
                "\r\n" + jsoncuerpo +
                "\r\n" +
                "Best regards;\r\n" +
                "\r\n" +
                "Alejandro Subero\r\n" +
                "\r\n";
        enviarConGMail(destino, asunto, mensage);
		
	}

	
	
	@Override
	public boolean sendMailContacto(String asunto, String mensage) {
		
    	boolean respuesta = false;

    	try {
        	destino="alejandrosubero53@gmail.com";//castillocrismary@gmail.com
        	envia = emailcfg.getUsername();
            pass = emailcfg.getPassword();        
            enviarConGMail(destino, asunto, mensage);
            respuesta = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return respuesta;
		}
		
	}

	
    
    
    private void enviarConGMail(String destino, String asunto, String mensage) {


        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", envia);
        props.put("mail.smtp.clave", "miClaveDeGMail"); //*** //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(envia));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(mensage);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", envia, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
   }

}
