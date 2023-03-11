package com.juaracoding.mf.model;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 07/03/2023 20:22
@Last Modified 07/03/2023 20:22
Version 1.1
*/

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MstArticle")
public class Article {
    private static final long serialversionUID = 1L;

    @Id
    @Column(name = "IDArticle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    @NotEmpty
    @NotNull
    @Column(name = "TitleArticle")
    private String titleArticle;

    @Column(name = "Slug")
    private String slug;

    @NotEmpty
    @NotNull
    @Column(name = "BodyArticle")
    private String bodyArticle;

    @NotEmpty
    @NotNull
    @Column(name = "ImageArticle")
    private String imageArticle;

    @ManyToOne
    @JoinColumn(name = "IDUser")
    private Userz user;

    @ManyToOne
    @JoinColumn(name = "IDCategoryArticle")
    private CategoryArticle categoryArticle;


    /*
       start audit trails
    */
    @Column(name ="CreatedDate" , nullable = false)
    private Date createdDate = new Date();

    @Column(name = "CreatedBy", nullable = false)
    private Integer createdBy;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "IsShow", nullable = false)
    private Byte isShow = 1;
    /*
        end audit trails
     */

    public Long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitleArticle() {
        return titleArticle;
    }

    public void setTitleArticle(String titleArticle) {
        this.titleArticle = titleArticle;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBodyArticle() {
        return bodyArticle;
    }

    public void setBodyArticle(String bodyArticle) {
        this.bodyArticle = bodyArticle;
    }

    public String getImageArticle() {
        return imageArticle;
    }

    public void setImageArticle(String imageArticle) {
        this.imageArticle = imageArticle;
    }

    public Userz getUser() {
        return user;
    }

    public void setUser(Userz user) {
        this.user = user;
    }

    public CategoryArticle getCategoryArticle() {
        return categoryArticle;
    }

    public void setCategoryArticle(CategoryArticle categoryArticle) {
        this.categoryArticle = categoryArticle;
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

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }
}
