package com.Generator.apirest.modelo.back.mail;

import org.springframework.stereotype.Component;

@Component
public class CuerpoDeCorreo {

    //esta clase es para extender la funcionabilidad de envio de correos con listas de correos para envio masivo de correos

    private String subject;
    private String content;
    private String categoria;


    public CuerpoDeCorreo() { }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria;}

	@Override
	public String toString() {
		return "CuerpoDeCorreo{" +
				"subject='" + subject + '\'' +
				", content='" + content + '\'' +
				", categoria='" + categoria + '\'' +
				'}';
	}
}
