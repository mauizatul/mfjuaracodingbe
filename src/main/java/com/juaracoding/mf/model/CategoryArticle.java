package com.juaracoding.mf.model;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 07/03/2023 21:02
@Last Modified 07/03/2023 21:02
Version 1.1
*/

import com.juaracoding.mf.utils.ConstantMessage;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "MstCategoryArticle")
public class CategoryArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCategoryArticle")
    private Long idCategoryArticle;

    @Length(message = ConstantMessage. WARNING_CATPROD_MAX_LENGTH_NAME, max = 40)
    @NotEmpty(message = ConstantMessage.WARNING_CATPROD_NAME_CANNOT_EMPTY)
    @Column(name = "NameCategoryArticle", nullable = false, length = 40)
    private String nameCategoryProduct;


    /*
        start audit trails
     */
    @Column(name ="CreatedDate")
    private Date createdDate = new Date();

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "IsDelete")
    private Byte isDelete = 1;
    /*
        end audit trails
     */

    public Long getIdCategoryArticle() {
        return idCategoryArticle;
    }

    public void setIdCategoryArticle(Long idCategoryArticle) {
        this.idCategoryArticle = idCategoryArticle;
    }

    public String getNameCategoryProduct() {
        return nameCategoryProduct;
    }

    public void setNameCategoryProduct(String nameCategoryProduct) {
        this.nameCategoryProduct = nameCategoryProduct;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}
