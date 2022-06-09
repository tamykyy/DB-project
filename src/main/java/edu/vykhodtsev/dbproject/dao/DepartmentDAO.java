package edu.vykhodtsev.dbproject.dao;

import edu.vykhodtsev.dbproject.entity.Department;

import java.util.List;

public interface DepartmentDAO {
    public List<Department> getAllDepartments();

    public Department getDepartment(int id);

    public void saveDepartment(Department department);

    public void deleteDepartment(int id);
}
