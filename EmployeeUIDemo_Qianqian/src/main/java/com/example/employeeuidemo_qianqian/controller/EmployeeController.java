package com.example.employeeuidemo_qianqian.controller;

import com.example.employeeuidemo_qianqian.model.Employee;
import com.example.employeeuidemo_qianqian.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Controller
public class EmployeeController {
    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/backToHome")
    public String backToHome(){
        return "redirect:/";
    }

    @GetMapping("/displayAll")
    public String displayAll(Model model){
        model.addAttribute("employees",service.getAllEmployees());
        return "displayAll";
    }

    @GetMapping("/check/{id}")
    public String check(@PathVariable long id,Model model){
        model.addAttribute("checkEmployee",service.findById(id));
        System.out.println(service.findById(id));
        return "checkEmployee";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable long id,Model model){
        model.addAttribute("updateEmployee",service.updateEmployee(service.findById(id)));
        return "updateEmployee";
    }

    @PostMapping("/processUpdate")
    public String processUpdate(Employee employee, Model model){
        service.updateEmployee(employee);
        return "redirect:/displayAll";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        service.deleteEmployee(id);
        return "redirect:/displayAll";
    }

    @GetMapping("addEmployee")
    public String addEmployee(){
        return "addEmployee";
    }

    @PostMapping("/processAdd")
    public String processAdd(Employee e, Model model) {
        if(e.getFirstName().equals("") || e.getLastName().equals("") || e.getSalary() == null || e.getState().equals("") || e.getCountry().equals("")) {
            model.addAttribute("addError","All fields are required");
            return "forward:/addEmployee";
        }

        service.createEmployee(e);
        model.addAttribute("addEmployee", e);
        return "redirect:/displayAll";
    }

    @PostMapping("/addEmployee")
    public String forwardAddEmployee() {
        return "addEmployee";
    }

    @GetMapping("/searchById")
    public String searchById() {
        return "searchById";
    }

    @PostMapping("/processSearchId")
    public String processSearchId(@RequestParam Long id, Model model) {
        Employee employee = service.findById(id);
        if(employee == null) {
            model.addAttribute("findByIdError", "Employee not exists");
            return "forward:/searchById";
        }
        model.addAttribute("findById", employee);
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
        Collection<Employee> list = service.findByFirstNameOrLastName(name);
        model.addAttribute("list", list);
        if(list.size() == 0) {
            model.addAttribute("noRecord", "No Record Found");
        }
        return "resultFindByName";
    }
}
