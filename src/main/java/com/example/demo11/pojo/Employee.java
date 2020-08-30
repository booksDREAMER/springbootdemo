package com.example.demo11.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
public class Employee implements Serializable {
    /**
     *用户id
     */
    private String userId;
    /**
     *用户名
     */
    private String username;
    /**
     *性别
     */
    private String sex;
    /**
     *生日
     */
    private Date birthday;
    /**
     *国家
     */
    private String national;
    /**
     *地址
     */
    private String address;
    /**
     *联系方式
     */
    private String mobile;
}