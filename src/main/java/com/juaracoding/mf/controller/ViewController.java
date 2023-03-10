package com.juaracoding.mf.controller;
/*
Created By IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Fadhil a.k.a. Mauizatul Fadhillah
Java Developer
Created on 3/7/2023 5:20 PM
@Last Modified 3/7/2023 5:20 PM
Version 1.0
*/

import com.juaracoding.mf.configuration.OtherConfig;
import com.juaracoding.mf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;
import com.juaracoding.mf.utils.MappingAttribute;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ViewController {
    @Autowired
    private StudentService studentService;
    private MappingAttribute mappingAttribute = new MappingAttribute();
    private Map<String,Object> objectMapper = new HashMap<String,Object>();

    @GetMapping("/aaa")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "home";
    }

    @GetMapping("/students/{id}")
    public String editStudentForm(@PathVariable("id") Long Id, Model model, WebRequest request) {
        model.addAttribute("students", studentService.getStudentById(Id));
        return "artikel_detail";
    }

//    @GetMapping("/students/{id}")
//    public String editStudentForm(@PathVariable("id") Long Id, Model model, WebRequest request) {
//        if(OtherConfig.getFlagSessionValidation().equals("y")) {
//            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
//            if (request.getAttribute("USR_ID", 1) == null) {
//                return "redirect:/api/check/logout";
//            }
//        }
//        model.addAttribute("student", studentService.getStudentById(Id));
//        return "edit_student";
//    }
}
