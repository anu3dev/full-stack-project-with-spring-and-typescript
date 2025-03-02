package com.anu3dev.backend.service;

import com.anu3dev.backend.dao.SuperAdminListDAO;
import com.anu3dev.backend.exception.ResourceNotFoundException;
import com.anu3dev.backend.model.SuperAdminList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService implements ISuperAdminService {
	@Autowired
	private SuperAdminListDAO dao;
	
	@Override
	public List<SuperAdminList> getAllEmployee() {
		return dao.findAll();
	}

	@Override
	public SuperAdminList saveEmployeeData(SuperAdminList emp) {
		return dao.save(emp);
	}

	@Override
	public ResponseEntity<SuperAdminList> getEmployeeData(Long id) {
		SuperAdminList emp = dao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+ id));
		return ResponseEntity.ok(emp);
	}

	@Override
	public ResponseEntity<SuperAdminList> updateEmpoyeeData(Long id, SuperAdminList emp) {
		SuperAdminList employee = dao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+ id));
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setEmailId(emp.getEmailId());

		SuperAdminList updatedEmp = dao.save(employee);
		return ResponseEntity.ok(updatedEmp);
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deleteEmployeeData(Long id) {
		SuperAdminList emp = dao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+ id));
		dao.delete(emp);
		Map<String, Boolean> res = new HashMap<String, Boolean>();
		res.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(res);
	}
}
