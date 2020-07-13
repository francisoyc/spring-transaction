package com.francis.transaction.service;

import java.io.IOException;

/**
 * @author: francis
 * @description:
 * @date: 2020/7/13 20:01
 */
public interface StudentService {

    /**
     *  如果当前已经存在事务，那么加入该事务，如果不存在事务，创建一个事务，这是默认的传播属性值
     */
    void testPropagationRequired();

    /**
     * 开启新事物，如果当前已存在事务，将当前事务挂起，新事物不影响旧事物的回滚
     */
    void testPropagationRequiresNew();

    /**
     * 嵌套事务，内部事务的回滚会影响外部事务回滚
     */
    void testPropagationNested();

    /**
     * 以非事务方式进行，如果当前存在事务，挂起当前事务
     */
    void testPropagationNotSupported();

    /**
     * 以非事务方式进行，如果存在事务则抛异常
     */
    void testPropagationNever();

    /**
     * 当前必须存在一个事务，否则抛出异常
     */
    void testPropagationMandatory();

    /**
     * 如果当前已经存在事务，那么加入该事务，如果不存在事务，以非事务方式进行
     */
    void testPropagationSupports();

    /**
     * 测试 private 或 protected 修饰的方法，正常来说加了@Transactional注解也不会回滚
     */
    void testPrivateMethod();

    /**
     * 测试同一个类内部的方法之间的调用
     */
    void testInnerMethod();

    /**
     * 测试需要回滚的异常和抛出的异常不匹配的场景
     */
    void testExceptionNotMatch() throws IOException;

    /**
     * 测试异常被捕捉的场景
     */
    void testExceptionCatched();

    /**
     * 测试正常情况
     */
    void testOk();
}
