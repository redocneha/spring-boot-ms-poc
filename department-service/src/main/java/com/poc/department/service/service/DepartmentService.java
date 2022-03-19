package com.poc.department.service.service;

import com.poc.department.service.entity.Department;
import com.poc.department.service.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside save department of service");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long id){
        log.info("Inside find department of service");
        return departmentRepository.findByDeptId(id);
    }
}
