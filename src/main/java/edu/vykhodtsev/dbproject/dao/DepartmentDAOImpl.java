package edu.vykhodtsev.dbproject.dao;

import edu.vykhodtsev.dbproject.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departmentList = entityManager.createQuery("from Department").getResultList();
        return departmentList;
    }

    @Override
    public void saveDepartment(Department department) {
        Department newDep = entityManager.merge(department);
        department.setId(newDep.getId());
    }


    @Override
    public Department getDepartment(int id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public void deleteDepartment(int id) {
        Query query = entityManager.createQuery("delete from Department where id =: depId");
        query.setParameter("depId", id);
        query.executeUpdate();
    }
}
