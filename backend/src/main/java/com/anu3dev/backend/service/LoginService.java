package com.anu3dev.backend.service;

import com.anu3dev.backend.dao.CompanyDao;
import com.anu3dev.backend.dao.UserDao;
import com.anu3dev.backend.exception.ResourceNotFoundException;
import com.anu3dev.backend.model.Company;
import com.anu3dev.backend.model.LoginApiResponse;
import com.anu3dev.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    public LoginApiResponse verifyUserLogin(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            User userObj = userDao.findByEmailId(user.getEmailId());

            String firstName;
            if (userObj.getName() == null || userObj.getName().isEmpty()) {
                firstName = "";
            } else {
                String firstNameFromDB = userObj.getName().split(" ")[0];
                firstName = firstNameFromDB.substring(0, 1).toUpperCase() + firstNameFromDB.substring(1);
            }

            String role;
            if(Objects.equals(userObj.getIsAdmin(), "true") && Objects.equals(userObj.getCompanyId(), "290905")) {
                role = "superAdmin";
            } else if (Objects.equals(userObj.getIsAdmin(), "true") && !Objects.equals(userObj.getCompanyId(), "290905")) {
                role = "admin";
            } else if (!Objects.equals(userObj.getIsAdmin(), "true") && Objects.equals(userObj.getCompanyId(), "290905")) {
                role = "superUser";
            } else {
                role = "user";
            }
            return new LoginApiResponse("Success", jwtService.generateToken(user.getEmailId()), firstName, role);
        } else {
            return new LoginApiResponse("Invalid email ID or password.", "", "", "");
        }
    }


















    public List<Company> getCompanyList() {
        List<Company> companyList = loginDao.findAll();
        return companyList.stream().filter(company -> !company.getUniqueId().equals("630448"))
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
        //user.setUniqueId(generateUniqueId());
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
        return "User registered successfully.";
    }

//    private long generateUniqueId() {
//        long uniqueId;
//        do {
//            uniqueId = Long.parseLong(generateRandom10DigitNumber());
//        } while (userDao.existsByUniqueId(uniqueId));
//        return uniqueId;
//    }

    private String generateRandom10DigitNumber() {
        final SecureRandom random = new SecureRandom();
        long number = random.nextLong(9000000000L) + 1000000000L; // Generates a number between 100000 and 999999
        return String.valueOf(number);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User saveUserData(User user) {
        return userDao.save(user);
    }

    public ResponseEntity<User> getUserData(int id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: "+ id));
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> updateUserData(int id, User user) {
        User userDetail = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: "+ id));
        userDetail.setName(user.getName());
        userDetail.setEmailId(user.getEmailId());

        User updatedUser = userDao.save(userDetail);
        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<Map<String, Boolean>> deleteUserData(int id) {
        User emp = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: "+ id));
        userDao.delete(emp);
        Map<String, Boolean> res = new HashMap<String, Boolean>();
        res.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(res);
    }
}
