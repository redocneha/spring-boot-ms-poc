package com.poc.department.service.controller;

import com.poc.department.service.entity.Department;
import com.poc.department.service.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody  Department department){
        log.info("Inside save department of controller",department.getDeptCode());
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long deptId){
        log.info("Inside find department of controller");
        return departmentService.findDepartmentById(deptId);
    }
}
