package com.example.demo11.dao;

import com.example.demo11.pojo.Employee;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface EmployeeDAO{

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
    Page<Employee> findList();

    /**
     * 新增
     *
     * @param employee
     */
    int insert(Employee employee);

    /**
     * 修改
     *
     * @param employee
     */
    int update(Employee employee);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    int deleteById(String id);

}