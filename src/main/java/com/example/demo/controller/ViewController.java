package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.EmployeeRepository;

@Controller
public class ViewController {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	
	@Autowired
	CompanyRepository companyRepo;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		
		List<Employee> listEmployees = employeeRepo.findAll();
		model.addAttribute("listEmployees", listEmployees);
		
		List<Company> listCompany = companyRepo.findAll();
		model.addAttribute("listCompany", listCompany);
		
		return "index";
	}
	

}
