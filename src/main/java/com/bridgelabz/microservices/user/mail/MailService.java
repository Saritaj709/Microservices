package com.bridgelabz.microservices.user.mail;

import com.bridgelabz.microservices.user.model.MailDTO;

public interface MailService {
	public boolean sendMail(MailDTO mail);	
}
