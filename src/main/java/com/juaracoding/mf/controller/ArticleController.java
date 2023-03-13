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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/articles2")
public class ArticleController {
    private ArticleService articleService;
    private MappingAttribute mappingAttribute = new MappingAttribute();
    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // display list of article
    @GetMapping("/show")
    public String showArticles(Model model, WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }
//        return findPaginated(1, "firstName", "asc", model,request);
        model.addAttribute("listArticles", articleService.getAllArticles());
        return "article/article";
    }

    @GetMapping("/showNewArticleForm")
    public String showNewArticleForm(Model model, WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }

        // create model attribute to bind form data
        Article article = new Article();
        model.addAttribute("article", article);
        return "article/create_article";
    }

    @PostMapping("/saveArticle")
    public String saveArticle(@ModelAttribute("article") Article article,Model model, WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }
        // save article to database
        articleService.saveArticle(article);
        return "redirect:/api/articles2/show";
    }



//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model,WebRequest request) {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        if(request.getAttribute("USR_ID",1)==null){
//            return "redirect:/api/check/logout";
//        }
//        int pageSize = 5;
//
//        Page<Article> page = articleService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List<Article> listArticles = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("listArticles", listArticles);
//        return "index";
//    }
}
