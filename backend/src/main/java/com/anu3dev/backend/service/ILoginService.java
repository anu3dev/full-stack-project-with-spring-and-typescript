package com.anu3dev.backend.service;

import com.anu3dev.backend.model.Company;

import java.util.List;

public interface ILoginService {
    String registerCompany(Company company) throws Exception;
    List<Company> getCompanyList();
    List<Company> getApprovedCompanyList();
}
