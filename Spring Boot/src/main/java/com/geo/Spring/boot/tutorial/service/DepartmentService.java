package com.geo.Spring.boot.tutorial.service;

import com.geo.Spring.boot.tutorial.entity.Department;
import com.geo.Spring.boot.tutorial.error.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
   public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    void deleteDepartmentById(Long departmentId);

     public Optional<Department> fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public Department updateDepartment(Long departmentId, Department department);

    Department fetchDepartmentName(String departmentName);
}
