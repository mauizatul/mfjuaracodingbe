package com.juaracoding.mf.service;

/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IU-222.4345.14, built on October 5, 2022
@Author Asus a.k.a. muhammad abdul fajar
Java Developer
Created on 3/8/2023 8:10 PM
@Last Modified 3/8/2023 8:10 PM
Version 1.0
*/


import com.juaracoding.mf.configuration.OtherConfig;
import com.juaracoding.mf.handler.ResourceNotFoundException;
import com.juaracoding.mf.handler.ResponseHandler;
import com.juaracoding.mf.model.Article;
import com.juaracoding.mf.model.CategoryArticle;
import com.juaracoding.mf.repo.CategoryArticleRepo;
import com.juaracoding.mf.utils.ConstantMessage;
import com.juaracoding.mf.utils.LoggingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import java.io.IOError;
import java.io.IOException;
import java.sql.SQLDataException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@Transactional
public class CategoryArticleService {
    private CategoryArticleRepo categoryArticleRepo;
    private String [] strExceptionArr = new String[2];

    @Autowired
    public CategoryArticleService(CategoryArticleRepo categoryArticleRepo) {
        strExceptionArr[0] = "CategoryArticleService";
        this.categoryArticleRepo = categoryArticleRepo;
    }

    public Map<String, Object> saveCategory(CategoryArticle categoryArticle, WebRequest request) {
        Object strUserIdz = request.getAttribute("USR_ID",1);
        try {
            if(strUserIdz==null)
            {
                return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_FLOW_INVALID,
                        HttpStatus.NOT_ACCEPTABLE,null,"FV02001",request);
            }
            categoryArticle.setCreatedBy(Integer.parseInt(strUserIdz.toString()));
            categoryArticle.setCreatedDate(new Date());
            categoryArticleRepo.save(categoryArticle);
        } catch (Exception e) {
            strExceptionArr[1] = "saveCategory(CategoryArticle categoryArticle, WebRequest request) --- LINE 63";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST, null, "FE02001", request);
        }
        return new ResponseHandler().generateModelAttribut(ConstantMessage.SUCCESS_SAVE,
                HttpStatus.CREATED, null, null, request);
    }

    public CategoryArticle getCategoryById(long id) {
        Optional<CategoryArticle> optional = categoryArticleRepo.findById(id);
        CategoryArticle categoryArticle = null;
        if (optional.isPresent()) {
            categoryArticle = optional.get();
        } else {
            throw new RuntimeException(" Category not found for id :: " + id);
        }
        return categoryArticle;
    }

    public Map<String, Object> updateCategory(CategoryArticle categoryArticle, WebRequest request) {
        Object strUserIdz = request.getAttribute("USR_ID",1);
        try {
            if(strUserIdz==null)
            {
                return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_FLOW_INVALID,
                        HttpStatus.NOT_ACCEPTABLE,null,"FV02001",request);
            }
            categoryArticle.setModifiedBy(Integer.parseInt(strUserIdz.toString()));
            categoryArticle.setModifiedDate(new Date());
            categoryArticleRepo.save(categoryArticle);
        } catch (Exception e) {
            strExceptionArr[1] = "updateCategory(CategoryArticle categoryArticle, WebRequest request) --- LINE 95";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST, null, "FE02001", request);
        }
        return new ResponseHandler().generateModelAttribut(ConstantMessage.SUCCESS_SAVE,
                HttpStatus.CREATED, null, null, request);
    }

    public Page<CategoryArticle> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.categoryArticleRepo.findAll(pageable);
    }

    public List<CategoryArticle> findAllCategory()
    {
        return categoryArticleRepo.findAll();
        /*
            SELECT * FROM MstCategoryProduct
         */
    }


//    public void saveDataCategory(CategoryArticle categoryArticle){
//        categoryArticleRepo.save(categoryArticle);
//    }

//    @Transactional(rollbackFor = Exception.class)
//    public void saveAllCategory(List<CategoryArticle> listCategoryArticle){
//        categoryArticleRepo.saveAll(listCategoryArticle);
//    }

//    @Transactional(rollbackFor = Exception.class)
//    public List<CategoryArticle> saveUploadFile(List<CategoryArticle> listCategoryArticle){
//        return categoryArticleRepo.saveAll(listCategoryArticle);
//    }

//    @Transactional(rollbackFor = Exception.class)
//    public void updateCategory(CategoryArticle categoryArticle,Long id) throws  Exception
//    {
//        CategoryArticle cArticle = categoryArticleRepo.findById(id).orElseThrow (
//                ()->  new ResourceNotFoundException("Data tidak ditemukan")
//        );
//
//        /*
//            SELECT * FROM MstCategoryProduct WHERE IDCategoryProduct = ?
//            cProduct.getNameCategoryProduct();//ALAT ELEKTRONIK
//            cProduct.getStrDescCategoryProduct();//seluruh peralatan yang disentuh nanti nyetrum
//         */
//        if(cArticle!=null){
//            cArticle.setNameCategoryArticle(categoryArticle.getNameCategoryArticle());
//            cArticle.setModifiedBy(categoryArticle.getModifiedBy());
//            cArticle.setModifiedDate(new Date());
//            cArticle.setStrDescCategoryArticle(categoryArticle.getStrDescCategoryArticle());
//        }
//
//    }


//    public Page<CategoryArticle> findAllCategoryByPage(Pageable pageable)
//    {
//        return categoryArticleRepo.findAll(pageable);
//        /*
//            SELECT * FROM MstCategoryProduct Page = ? , Sort = ? , Record = ?
//
//            totalRecord = 100
//            page = 0
//            Record = 10
//            data balikan = dari index ke 0 s.d index ke 9
//
//            page = 1
//            record = 10
//            data balikan = dari index 10 s.d index 19
//
//            page = 2
//            record = 10
//            data balikan = dari index 20 s.d index 19
//
//         */
//    }
//
//    public Optional<CategoryArticle> findById(Long id)
//    {
//        return categoryArticleRepo.findById(id);
//
//        /*
//            SELECT * FROM MstCategoryProduct WHERE IDCategoryProduct = ?
//         */
//    }
}


