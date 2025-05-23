package com.anu3dev.backend.service;

import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.EmailOTP;
import com.anu3dev.backend.model.User;

import java.util.List;

public interface IRegistrationService {
	String sendOTPEmail(EmailOTP email) throws Exception;
	String verifyOTPEmail(EmailOTP email) throws Exception;
	String registerCompany(Company company) throws Exception;
	String registerUser(User user) throws Exception;
	List<String> approvedCompanyNameList();
}
