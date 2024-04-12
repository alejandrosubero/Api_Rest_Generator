package com.Generator.apirest.services.mail;

public interface MensendService {

    public void sendMailResponse();
    public boolean sendMailContacto(String asunto, String mensage);
    // public void enviarConGMail(String destino, String asunto, String mensage);
}
