package com.anu3dev.backend.service;

import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.EmailOTP;

public interface IRegistrationService {
	String sendOTPEmail(EmailOTP email) throws Exception;
	String verifyOTPEmail(EmailOTP email) throws Exception;
	String registerCompany(Company company) throws Exception;
}
