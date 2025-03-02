package com.anu3dev.backend.service;

import com.anu3dev.backend.model.EmailOTP;

public interface IEmailOTPService {
	String sendOTPEmail(EmailOTP email) throws Exception;
}
