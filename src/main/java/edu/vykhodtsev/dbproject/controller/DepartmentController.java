package edu.vykhodtsev.dbproject.controller;


import edu.vykhodtsev.dbproject.entity.Department;
import edu.vykhodtsev.dbproject.entity.Employee;
import edu.vykhodtsev.dbproject.service.DepartmentService;
import edu.vykhodtsev.dbproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String showAllDepartments(Model model) {
        List<Department> departmentList = departmentService.getAllDepartments();
        model.addAttribute("departmentList", departmentList);
        return "all-departments";
    }

    @RequestMapping("/addDepartment")
    public String addDepartment(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "info-department";
    }

    @RequestMapping("/changeDepartment")
    public String changeDepartment(@RequestParam("depId") int id, Model model) {
        Department department = departmentService.getDepartment(id);
        model.addAttribute("department", department);
        return "info-department";
    }

    @RequestMapping("/saveDepartment")
    public String saveDepartment(@ModelAttribute("department") Department department) {
        int id = department.getId();
        if (id == 0) {
            departmentService.saveDepartment(department);
        } else {
            departmentService.saveDepartment(department);
        }
        return "redirect:/";
    }

    @RequestMapping("/deleteDepartment")
    public String deleteDepartment(@RequestParam("depId") int id) {
        Department department = departmentService.getDepartment(id);
        List<Employee> employeeList = department.getEmps();
        for (Employee emp : employeeList) {
            emp.setDepartment(null);
        }
        department.setEmps(null);
        departmentService.deleteDepartment(id);
        return "redirect:/";
    }

    @RequestMapping("/addToDepartment")
    public String addToDepartment(@RequestParam("depId") int id, Model model) {
        List<Employee> employeeList = employeeService.getAllEmployees();
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("depId", id);
        return "add-emp-to-dep";
    }

    @RequestMapping("/addingEmployee")
    public String addingEmployee(@RequestParam(value = "employee") int empId, @RequestParam(value = "depId") int depId) {
        Employee employee = employeeService.getEmployee(empId);
        Department department = departmentService.getDepartment(depId);
        department.addEmployeeToDepartment(employee);
        departmentService.saveDepartment(department);
        return "redirect:/";
    }

}
