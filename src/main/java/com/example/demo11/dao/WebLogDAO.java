package com.example.demo11.dao;

import com.example.demo11.pojo.WebLog;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface WebLogDAO{
    //添加
    @Insert("INSERT INTO `weblog`(`webLogId`, `username`, `description`, `startTime`, `spendTime`, `basePath`, `url`, `method`, `ip`, `parameter`) VALUES" +
            " (#{webLogId}, #{username}, #{description}, #{startTime}, #{spendTime}, #{basePath}, #{url}, #{method}, #{ip}, #{parameter})")
    void saveWeblog(WebLog webLog);
}
