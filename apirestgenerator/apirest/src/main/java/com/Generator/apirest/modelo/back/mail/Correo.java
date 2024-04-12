package com.Generator.apirest.modelo.back.mail;

import org.springframework.stereotype.Component;

@Component
public class Correo {

	private String to;
	private String subject;
	private String content;
	
	public Correo() {}
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
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
	@Override
	public String toString() {
		return "Correo [to=" + to + ", subject=" + subject + ", content=" + content + "]";
	}
	
	
}
