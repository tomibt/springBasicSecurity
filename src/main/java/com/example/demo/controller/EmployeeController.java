package com.example.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	CompanyRepository companyRepo;
	
	@Autowired
	EmployeeService empService;
	
//	@GetMapping("/")
//	public String viewHomePage(Model model) {
//		
//		List<Employee> listEmployees = employeeRepo.findAll();
//		model.addAttribute("listEmployees", listEmployees);
//		
//		return "index";
//	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee")Employee employee) {//, @ModelAttribute("company")Company company
//		employee.setCompany(company);
		employeeRepo.save(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		List<Company> listCompany = companyRepo.findAll();
		model.addAttribute("listCompany", listCompany);
		
		return "new_employee";
	}
		
	@GetMapping("/showUpdateForm/{id}")
	public String showUpdateForm(@PathVariable("id")Integer id, Model model) {
		Employee employee = employeeRepo.findById(id).get();
		model.addAttribute("employee", employee);
		model.addAttribute("company", employee.getCompany());
		
		List<Company> listCompany = companyRepo.findAll();
		model.addAttribute("listCompany", listCompany);
		
		
		return "update_employee";
	}
	
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable("id")Integer id) {
		employeeRepo.deleteById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNumber}")
	public String findPage(@PathVariable("pageNumber")Integer pageNumber,@RequestParam("pageSize")Integer pageSize, @RequestParam("sortFiled")String sortField, @RequestParam("sortDirection")String sortDirection, Model model ) {
		pageSize = 1;
		
		Page<Employee> page = empService.findPage(pageNumber, pageSize, sortField, sortDirection);
		
		List<Employee> res = page.getContent();
		
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listEmployees", res);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
		
		
		return "index";
		
		
		
	}
	
	
	
	

}
