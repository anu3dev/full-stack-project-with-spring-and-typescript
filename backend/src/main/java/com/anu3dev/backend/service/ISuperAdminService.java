package com.anu3dev.backend.service;

import com.anu3dev.backend.model.SuperAdminList;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ISuperAdminService {
	List<SuperAdminList> getAllEmployee();
	SuperAdminList saveEmployeeData(SuperAdminList emp);
	ResponseEntity<SuperAdminList> getEmployeeData(Long id);
	ResponseEntity<SuperAdminList> updateEmpoyeeData(Long id, SuperAdminList emp);
	ResponseEntity<Map<String, Boolean>> deleteEmployeeData(Long id);
}
