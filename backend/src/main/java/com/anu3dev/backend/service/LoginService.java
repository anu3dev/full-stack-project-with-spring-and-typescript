package com.anu3dev.backend.service;

import com.anu3dev.backend.dao.CompanyDao;
import com.anu3dev.backend.dao.UserDao;
import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private CompanyDao loginDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTService jwtService;
    @Autowired
    AuthenticationManager authManager;

    @Override
    public String registerCompany(Company company) throws Exception {
        if(loginDao.existsByCompanyEmail(company.getCompanyEmail())) {
            return "Company with email " + company.getCompanyEmail() + " already exists.";
        }
        company.setCompanyUniqueId(generateUniqueCompanyId());
        loginDao.save(company);
        return "Company registered successfully with unique id: " + company.getCompanyUniqueId();
    }

    private String generateUniqueCompanyId() {
        String uniqueId;
        do {
            uniqueId = generateRandom6DigitNumber();
        } while (loginDao.existsByCompanyUniqueId(uniqueId));
        return uniqueId;
    }

    private String generateRandom6DigitNumber() {
        final SecureRandom random = new SecureRandom();
        int number = random.nextInt(900000) + 100000; // Generates a number between 100000 and 999999
        return String.valueOf(number);
    }

    public List<Company> getCompanyList() {
        List<Company> companyList = loginDao.findAll();
        return companyList.stream().filter(company -> !company.getCompanyUniqueId().equals("630448"))
                .collect(Collectors.toList());
    }

    public List<Company> getApprovedCompanyList() {
        List<Company> companyList = getCompanyList();
        return companyList.stream().filter(company -> "true".equals(company.getApprovalStatus()))
                .collect(Collectors.toList());
    }

    public String registerUser(User user) throws Exception {
        if(userDao.existsByEmailId(user.getEmailId())) {
            return "User with email " + user.getEmailId() + " already exists.";
        }
        user.setUniqueId(generateUniqueId());
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
        return "User registered successfully.";
    }

    private long generateUniqueId() {
        long uniqueId;
        do {
            uniqueId = Long.parseLong(generateRandom10DigitNumber());
        } while (userDao.existsByUniqueId(uniqueId));
        return uniqueId;
    }

    private String generateRandom10DigitNumber() {
        final SecureRandom random = new SecureRandom();
        long number = random.nextLong(9000000000L) + 1000000000L; // Generates a number between 100000 and 999999
        return String.valueOf(number);
    }

    public String verifyUserLogin(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getEmailId());
        } else {
            return "failure";
        }
    }
}
