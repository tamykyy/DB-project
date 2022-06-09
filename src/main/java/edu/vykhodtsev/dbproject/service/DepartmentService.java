package edu.vykhodtsev.dbproject.service;

import edu.vykhodtsev.dbproject.entity.Department;

import java.util.List;

public interface DepartmentService {

    public List<Department> getAllDepartments();

    public Department getDepartment(int id);

    public void saveDepartment(Department department);

    public void updateDepartment(Department department);

    public void deleteDepartment(int id);
}
