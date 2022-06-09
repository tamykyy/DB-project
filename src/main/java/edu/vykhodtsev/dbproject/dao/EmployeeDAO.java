package edu.vykhodtsev.dbproject.dao;

import edu.vykhodtsev.dbproject.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> getAllEmployees();

    public Employee getEmployee(int id);

    public void saveEmployee(Employee employee);

    public void deleteEmployee(int id);
}
