package com.example.demo11.service;

import com.example.demo11.pojo.Employee;

import java.util.List;

/**
 *
 */
public interface EmployeeService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link Employee}
     */
    Employee findById(String id);

    /**
     * 分页查询
     *
     * @return {@link Employee}
     */
    List<Employee> findList();

    /**
     * 新增
     * @param employee
     */
    void insert(Employee employee);

    /**
     * 修改
     *
     * @param employee
     */
    void update(Employee employee);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(String id);

}