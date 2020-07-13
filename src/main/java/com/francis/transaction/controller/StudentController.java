package com.francis.transaction.controller;

import com.francis.transaction.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author: francis
 * @description:
 * @date: 2020/7/13 20:00
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping(value = "/test/{type}")
    public String testTransaction(@PathVariable("type") int type) {
        switch (type) {
            case 1:
                service.testPropagationRequired();
                break;
            case 2:
                service.testPropagationRequiresNew();
                break;
            case 3:
                service.testPropagationMandatory();
                break;
            case 4:
                service.testPropagationSupports();
                break;
            case 5:
                service.testPropagationNested();
                break;
            case 6:
                service.testPropagationNever();
                break;
            case 7:
                service.testPropagationNotSupported();
                break;
            case 8:
                service.testPrivateMethod();
                break;
            case 9:
                service.testInnerMethod();
                break;
            case 10:
                try {
                    service.testExceptionNotMatch();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 11:
                service.testExceptionCatched();
                break;
            default:
                service.testOk();
                break;
        }
        return "ok";
    }
}
