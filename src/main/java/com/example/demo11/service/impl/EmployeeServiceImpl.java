package com.example.demo11.service.impl;

import com.example.demo11.dao.EmployeeDAO;
import com.example.demo11.pojo.Employee;
import com.example.demo11.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional(readOnly = true)
    @Override
    public Employee findById(String id) {
        return employeeDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findList() {
        List<Employee> list = employeeDAO.findList();
        return list;
    }
    @Override
    public void insert(Employee employee) {
        employeeDAO.insert(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void deleteById(String id) {
     employeeDAO.deleteById(id);
    }

}