package com.francis.transaction.service.impl;

import com.francis.transaction.dao.StudentDao;
import com.francis.transaction.entity.Student;
import com.francis.transaction.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author: francis
 * @description:
 * @date: 2020/7/13 20:02
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ApplicationContext applicationContext;

    private Student getStudent(String name) {
        Student student = new Student();
        int random = (int) (Math.random() * 200);
        student.setName(name);
        student.setAge(random);
        student.setNo("No" + random);
        return student;
    }

    private StudentServiceImpl getService() {
        final StudentServiceImpl service = applicationContext.getBean(StudentServiceImpl.class);
        return service;
    }



    public void test() {
        studentDao.insert(getStudent("REQUIRED_B"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagationRequired() {
        studentDao.insert(getStudent("REQUIRED_A"));
        test();
        int i = 1/0;
    }



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test2() {
        studentDao.insert(getStudent("REQUIRES_NEW"));
        // 如果需要演示内部事务不影响外部事务，在这里加上 int i = 1/0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagationRequiresNew() {
        studentDao.insert(getStudent("REQUIRED"));
        getService().test2();
        int i = 1/0;

        // 如果需要演示内部事务不影响外部事务，注释上面的代码，放开下面的代码
        /*try {
            getService().test2();
        } catch (Exception e) {

        }
        studentDao.insert(getStudent("REQUIRED"));*/
    }



    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void testPropagationNested() {
        studentDao.insert(getStudent("NESTED"));
        getService().testPropagationRequired();
    }



    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void test3() {
        studentDao.insert(getStudent("NOT_SUPPORTED"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagationNotSupported() {
        studentDao.insert(getStudent("REQUIRED"));
        getService().test3();
        int i = 1/0;
    }



    @Transactional(propagation = Propagation.NEVER)
    public void test4() {
        studentDao.insert(getStudent("NEVER"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagationNever() {
        getService().test4();
    }



    @Transactional(propagation = Propagation.MANDATORY)
    public void test5() {
        studentDao.insert(getStudent("MANDATORY"));
    }

    @Override
    public void testPropagationMandatory() {
        getService().test5();
    }



    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void testPropagationSupports() {
        studentDao.insert(getStudent("SUPPORTS"));
        int i = 1/0;
    }



    @Transactional(propagation = Propagation.REQUIRED)
    protected void test6() {
        studentDao.insert(getStudent("ProtectedMethod"));
        int i = 1/0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void test66() {
    }

    @Override
    public void testPrivateMethod() {
        getService().test6();
    }




    @Transactional(propagation = Propagation.REQUIRED)
    public void test7() {
        studentDao.insert(getStudent("InnerMethod"));
        int i = 1/0;
    }

    @Override
    public void testInnerMethod() {
        test7();
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public void testExceptionNotMatch() throws IOException {
        studentDao.insert(getStudent("ExceptionNotMatch"));
        if (true) {
            throw new IOException();
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testExceptionCatched() {
        try {
            studentDao.insert(getStudent("ExceptionCatched"));
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void testOk() {
        studentDao.insert(getStudent("ok"));
    }
}
