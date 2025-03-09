package com.anu3dev.backend.service;

import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.User;

import java.util.List;

public interface ILoginService {
    String registerCompany(Company company) throws Exception;
    List<Company> getCompanyList();
    List<Company> getApprovedCompanyList();
    String registerUser(User user) throws Exception;
}
