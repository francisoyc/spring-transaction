package com.francis.transaction.dao;

import com.francis.transaction.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: francis
 * @description:
 * @date: 2020/7/13 20:01
 */
@Mapper
@Repository
public interface StudentDao {
    @Insert("insert into student(name, age, no) values(#{name}, #{age}, #{no})")
    Integer insert(Student student);
}
