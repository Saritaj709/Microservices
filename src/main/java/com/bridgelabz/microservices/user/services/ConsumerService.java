package com.bridgelabz.microservices.user.services;

import com.bridgelabz.microservices.user.model.MailDTO;

public interface ConsumerService {
public void receiver(MailDTO dto);
}
