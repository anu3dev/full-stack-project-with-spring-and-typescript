package com.anu3dev.backend.service;

import com.anu3dev.backend.model.EmailContact;

public interface IEmailContactService {
	String sendContactEmail(EmailContact email) throws Exception;
}
