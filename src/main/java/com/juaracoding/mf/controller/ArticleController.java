package com.juaracoding.mf.controller;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 07/03/2023 20:22
@Last Modified 07/03/2023 20:22
Version 1.1
*/

import com.juaracoding.mf.model.Article;
import com.juaracoding.mf.service.ArticleService;
import com.juaracoding.mf.utils.MappingAttribute;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/articles/")
public class ArticleController {
    private ArticleService articleService;

    private String[] strExcep = new String[2];

    private MappingAttribute mappingAttribute = new MappingAttribute();

    private Map<String,Object> objectMapper = new HashMap<String,Object>();

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("v1/list")
    public String viewHomePage(Model model, WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }
//        return findPaginated(1, "firstName", "asc", model,request);
        model.addAttribute("articles", articleService.getAllArticles());
        return "articles";
    }

    @PostMapping("v1/sv")
    public ResponseEntity<Object> saveProduct(@Valid
                                              @RequestBody Article article
    )
    {
        return articleService.saveArticle(article);
    }


}
