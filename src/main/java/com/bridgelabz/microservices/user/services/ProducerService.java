package com.bridgelabz.microservices.user.services;

import com.bridgelabz.microservices.user.model.MailDTO;

public interface ProducerService {
public void sender(MailDTO dto);
}
