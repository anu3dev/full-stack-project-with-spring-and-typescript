package com.anu3dev.backend.service;

import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ILoginService {
    String registerCompany(Company company) throws Exception;
    List<Company> getCompanyList();
    List<Company> getApprovedCompanyList();
    String registerUser(User user) throws Exception;
    String verifyUserLogin(User user);
    List<User> getAllUsers();
    User saveUserData(User user);
    ResponseEntity<User> getUserData(int id);
    ResponseEntity<User> updateUserData(int id, User user);
    ResponseEntity<Map<String, Boolean>> deleteUserData(int id);
}
