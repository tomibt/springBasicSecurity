package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import com.example.demo.repository.CompanyRepository;

@Controller
public class CompanyController {

	@Autowired
	CompanyRepository companyRepo;

	@GetMapping("/showNewCompany")
	public String showNewCompany(Model model) {
		Company company = new Company();
		model.addAttribute("company", company);
		return "new_company";
	}

	@PostMapping("/saveCompany")
	public String saveCompany(@ModelAttribute("company") Company company) {
		companyRepo.save(company);
		return "redirect:/";
	}
	
	
	@GetMapping("/showUpdateCompanyForm/{id}")
	public String showUpdateForm(@PathVariable("id")Integer id, Model model) {
		Company company = companyRepo.findById(id).get();
		model.addAttribute("company", company);
		return "update_company";
	}
	
	
	@GetMapping("/deleteCompany/{id}")
	public String deleteCompany(@PathVariable("id")Integer id) {
		companyRepo.deleteById(id);
		return "redirect:/";
	}

}
