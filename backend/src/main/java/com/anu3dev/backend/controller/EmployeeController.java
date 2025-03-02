package com.anu3dev.backend.controller;

import com.anu3dev.backend.model.SuperAdminList;
import com.anu3dev.backend.service.ISuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee/v1/")
public class EmployeeController {
	@Autowired
	private ISuperAdminService empSer;
	
	@GetMapping("/employees")
	public List<SuperAdminList> getAllEmp(){
		return empSer.getAllEmployee();
	}
	
	@PostMapping("/addEmployee")
	public SuperAdminList addEmp(@RequestBody SuperAdminList emp) {
		return empSer.saveEmployeeData(emp);
	}
	
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<SuperAdminList> getEmp(@PathVariable Long id) {
		return empSer.getEmployeeData(id);
	}
	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<SuperAdminList> updateEmp(@PathVariable Long id, @RequestBody SuperAdminList emp) {
		return empSer.updateEmpoyeeData(id, emp);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmp(@PathVariable Long id){
		return empSer.deleteEmployeeData(id);
	}
}
