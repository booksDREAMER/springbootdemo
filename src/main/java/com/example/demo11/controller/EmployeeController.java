package com.example.demo11.controller;

import com.example.demo11.pojo.Employee;
import com.example.demo11.service.EmployeeService;
import com.example.demo11.util.AjaxResult;
import com.example.demo11.util.DateUtils;
import com.example.demo11.util.GeneratID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/employees")
@Api(tags = "员工控制")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/findById")
    @ApiOperation("通过ID查询单个")
    @ResponseBody
    public AjaxResult findById(HttpServletRequest request) {
        String id = request.getParameter("id");;
        AjaxResult ajaxResult = new AjaxResult();
        try {
            Employee employee = employeeService.findById(id);
            ajaxResult.setData(employee);
        }catch (Exception e){
            ajaxResult.setState(false);
        }
        return ajaxResult;
    }

    @GetMapping("/findList")
    @ApiOperation("查询全部")
    @ResponseBody
    public List<Employee> findList() {
        return employeeService.findList();
    }

    @PostMapping("/insert")
    @ApiOperation("新增")
    @ResponseBody
    public AjaxResult insert(@RequestBody Employee employee) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            employee.setUserId(GeneratID.getGeneratID());
            employeeService.insert(employee);
        }catch (Exception e){
            ajaxResult.setState(false);
        }
        return ajaxResult;
    }

    @PostMapping("/update")
    @ApiOperation("修改")
    @ResponseBody
    public AjaxResult update(@RequestBody Employee employee) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            employeeService.update(employee);
        }catch (Exception e){
            ajaxResult.setState(false);
        }
        return ajaxResult;

    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ApiOperation("通过ID删除单个")
    @ResponseBody
    public AjaxResult deleteById(HttpServletRequest request) {
      String id = request.getParameter("id");
        AjaxResult ajaxResult = new AjaxResult();
        try {
            employeeService.deleteById(id);
        }catch (Exception e){
            ajaxResult.setState(false);
        }
        return ajaxResult;
    }
}
