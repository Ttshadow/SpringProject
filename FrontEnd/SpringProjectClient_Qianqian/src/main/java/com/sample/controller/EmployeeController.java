package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.model.Employee;
import com.sample.service.EmployeeService;

@Controller
public class EmployeeController {
	private EmployeeService service;

	@Autowired
	public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}

	public EmployeeController() {
		super();
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/backToHome")
	public String backToHome() {
		return "redirect:/";
	}
	
	@GetMapping("/displayAll")
	public String displayAll(Model model) {
		List<Employee> employees = service.findAll();
		model.addAttribute("employees", employees);
		return "displayAll";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee() {
		return "addEmployee";
	}
	
	@PostMapping("/processAdd")
	public String processAdd(Employee e, Model model) {
		if(e.getFirstName().equals("") || e.getLastName().equals("") || e.getSalary() == null || e.getState().equals("") || e.getCountry().equals("")) {
			model.addAttribute("addError","All fields are required");
			return "forward:/addEmployee";
		}
		
		List<Employee> list = service.findNumberByEmployeeFirstNameAndLastName(e.getFirstName(), e.getLastName());
		if(list.size() != 0) {
			model.addAttribute("addError","Employee Already Exists");
			return "forward:/addEmployee";
		}
		
		service.save(e);
		model.addAttribute("addEmployee", e);
		return "addConfirm";
	}
	
	@PostMapping("/addEmployee")
	public String forwardAddEmployee() {
		return "addEmployee";
	}
	
	@GetMapping("/check/{id}")
	public String check(@PathVariable Long id, Model model) {
		Employee employee = service.findById(id);
		model.addAttribute("checkEmployee", employee);
		return "checkEmployee";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model){
		Employee employee = service.findById(id);
		model.addAttribute("updateEmployee", employee);
		return "updateEmployee";
	}
	
	@PostMapping("/processUpdate")
	public String processUpdate(Employee employee, Model model) {
		service.update(employee.getId(),employee);
		return "redirect:/displayAll";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		service.delete(id);
		return "redirect:/displayAll";
	}
	
	@GetMapping("/searchById")
	public String searchById() {
		return "searchById";
	}
	
	@PostMapping("/processSearchId")
	public String processSearchId(@RequestParam Long id, Model model) {
		List<Employee> list = service.checkExistById(id);
		if(list.size() == 0) {
			model.addAttribute("findByIdError", "Employee not exists");
			return "forward:/searchById";
		}
		model.addAttribute("findById", list.get(0));
		return "resultFindById";
	}
	
	@PostMapping("/searchById")
	public String forwardSearchById() {
		return "searchById";
	}
	
	@GetMapping("/searchByName")
	public String searchByName() {
		return "searchByName";
	}
	
	@PostMapping("/processSearchName")
	public String processSearchName(@RequestParam String name,Model model) {
		List<Employee> list = service.findByName(name);
		model.addAttribute("list", list);
		if(list.size() == 0) {
			model.addAttribute("noRecord", "No Record Found");
		}
		return "resultFindByName";
	}
}
