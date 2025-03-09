package com.anu3dev.backend.service;

import com.anu3dev.backend.dao.LoginDao;
import com.anu3dev.backend.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public String registerCompany(Company company) throws Exception {
        if(loginDao.existsByCompanyEmail(company.getCompanyEmail())) {
            return "Company with email " + company.getCompanyEmail() + " already exists.";
        }
        company.setUniqueId(generateUniqueCompanyId());
        loginDao.save(company);
        return "Company registered successfully with unique id: " + company.getUniqueId();
    }

    private String generateUniqueCompanyId() {
        String uniqueId;
        do {
            uniqueId = generateRandom6DigitNumber();
        } while (loginDao.existsByUniqueId(uniqueId));
        return uniqueId;
    }

    private String generateRandom6DigitNumber() {
        final SecureRandom random = new SecureRandom();
        int number = random.nextInt(900000) + 100000; // Generates a number between 100000 and 999999
        return String.valueOf(number);
    }

    public List<Company> getCompanyList() {
        List<Company> companyList = loginDao.findAll();
        return companyList.stream().filter(company -> !company.getUniqueId().equals("724643"))
                .collect(Collectors.toList());
    }

    public List<Company> getApprovedCompanyList() {
        List<Company> companyList = getCompanyList();
        return companyList.stream().filter(company -> "true".equals(company.getApprovalStatus()))
                .collect(Collectors.toList());
    }
}
