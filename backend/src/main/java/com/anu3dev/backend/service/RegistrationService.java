package com.anu3dev.backend.service;

import com.anu3dev.backend.dao.CompanyDao;
import com.anu3dev.backend.dao.EmailDaoOTP;
import com.anu3dev.backend.dao.UserDao;
import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.EmailOTP;
import com.anu3dev.backend.model.User;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.anu3dev.backend.service.CommonUtils.*;
import static com.anu3dev.backend.service.Constants.*;

@Service
public class RegistrationService implements IRegistrationService {
	@Autowired
	private JavaMailSender sender;
	@Value("{spring.mail.username}")
	private String fromEmailid;
	@Autowired
	private EmailDaoOTP daoEmailOTP;
	@Autowired
	private CompanyDao daoCompany;
	@Autowired
	private UserDao daoUser;

	@Override
	public String sendOTPEmail(EmailOTP email) throws Exception {
		if(daoCompany.existsByEmailId(email.getEmailId())){
			return "Company with email " + email.getEmailId() + " already exists.";
		} else if(daoUser.existsByEmailId(email.getEmailId())){
			return "User with email " + email.getEmailId() + " already exists.";
		} else {
			String otpValue = generateRandomNumber(OTPLength, NumericForOTP);

			MimeMessage mimeMessage = sender.createMimeMessage();

			MimeMessageHelper emailMessage = new MimeMessageHelper(mimeMessage, true);
			emailMessage.setFrom(fromEmailid);
			emailMessage.setTo(email.getEmailId());
			emailMessage.setSubject("OTP for Registration");
			emailMessage.setText("Your OTP is: " + otpValue);
			sender.send(mimeMessage);

			if(daoEmailOTP.existsByEmailId(email.getEmailId())) {
				EmailOTP existingEmailOTP = daoEmailOTP.findByEmailId(email.getEmailId());
				existingEmailOTP.setOtpValue(otpValue);
				daoEmailOTP.save(existingEmailOTP);
			} else {
				email.setOtpValue(otpValue);
				daoEmailOTP.save(email);
			}
			return "It's time to check your inbox for OTP.";
		}
	}

	@Override
	public String verifyOTPEmail(EmailOTP email) throws Exception {
		if(daoEmailOTP.existsByEmailId(email.getEmailId())) {
			EmailOTP existingEmailOTP = daoEmailOTP.findByEmailId(email.getEmailId());
			if(existingEmailOTP.getOtpValue().equals(email.getOtpValue())) {
				daoEmailOTP.delete(existingEmailOTP);
				return "OTP verified successfully.";
			} else {
				return "Entered OTP is invalid.";
			}
		} else {
			return "Email ID not found.";
		}
	}

	@Override
	public String registerCompany(Company company) throws Exception {
		if(daoCompany.existsByEmailId(company.getEmailId())) {
			return company.getEmailId() + " is already registered with company ID: " + daoCompany.findByEmailId(company.getEmailId()).getUniqueId();
		} else if (daoUser.existsByEmailId(company.getEmailId())) {
			return company.getEmailId() + " is already registered with user ID: " + daoUser.findByEmailId(company.getEmailId()).getUniqueId();
		} else {
			// Generate a unique ID for the company also check if it already exists
			String uniqueCompanyId;
			do {
				uniqueCompanyId = generateRandomNumberInLimit(SixDigitStart, SixDigitEnd);
			} while (daoCompany.existsByUniqueId(uniqueCompanyId));
			company.setUniqueId(uniqueCompanyId);
			daoCompany.save(company);

			// Generate a unique ID for the user also check if it already exists
			String uniqueUserId;
			do {
				uniqueUserId = generateRandomNumberInLimit(EightDigitStart, EightDigitEnd);
			} while (daoUser.existsByUniqueId(uniqueUserId));

			String tempPassword = generateRandomNumber(PasswordLength, AlphaNumericForPassword);
			final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

			// Create a new user and save it to the database
			User user = new User();
			user.setCompanyId(uniqueCompanyId);
			user.setIsAdmin("true");
			user.setApprovedBy("self");
			user.setApprovalStatus("true");
			user.setEmailId(company.getEmailId());
			user.setPhoneNo(company.getPhoneNo());
			user.setName(company.getRegisteredBy());
			user.setUniqueId(uniqueUserId);
			user.setPassword(encoder.encode(tempPassword));
			daoUser.save(user);

			MimeMessage mimeMessage = sender.createMimeMessage();

			MimeMessageHelper emailMessage = new MimeMessageHelper(mimeMessage, true);
			emailMessage.setFrom(fromEmailid);
			emailMessage.setTo(company.getEmailId());
			emailMessage.setSubject("Temp password for admin user");
			emailMessage.setText("Your temp password is: " + tempPassword);
			sender.send(mimeMessage);

			return "Company registered successfully with unique ID: " + company.getUniqueId();
		}
	}

	@Override
	public String registerUser(User user) throws Exception {
		if(daoCompany.existsByEmailId(user.getEmailId())) {
			return user.getEmailId() + " is already registered with company ID: " + daoCompany.findByEmailId(user.getEmailId()).getUniqueId();
		} else if (daoUser.existsByEmailId(user.getEmailId())) {
			return user.getEmailId() + " is already registered with user ID: " + daoUser.findByEmailId(user.getEmailId()).getUniqueId();
		} else {
			// Generate a unique ID for the user also check if it already exists
			String uniqueUserId;
			do {
				uniqueUserId = generateRandomNumberInLimit(EightDigitStart, EightDigitEnd);
			} while (daoUser.existsByUniqueId(uniqueUserId));

			String tempPassword = generateRandomNumber(PasswordLength, AlphaNumericForPassword);
			final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

			user.setIsAdmin("");
			user.setApprovedBy("");
			user.setApprovalStatus("");
			user.setUniqueId(uniqueUserId);
			user.setPassword(encoder.encode(tempPassword));
			daoUser.save(user);

			MimeMessage mimeMessage = sender.createMimeMessage();

			MimeMessageHelper emailMessage = new MimeMessageHelper(mimeMessage, true);
			emailMessage.setFrom(fromEmailid);
			emailMessage.setTo(user.getEmailId());
			emailMessage.setSubject("Temp password for user");
			emailMessage.setText("Your temp password is: " + tempPassword);
			sender.send(mimeMessage);

			return "User registered successfully with unique ID: " + user.getUniqueId();
		}
	}
}
