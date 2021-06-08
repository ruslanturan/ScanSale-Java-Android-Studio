package com.IOStarter.scansale;

public class ClassProducts {

    /* renamed from: Id */
    private Integer f55Id;
    private String Photo;
    private String ProductName;
    private String Sale;
    private String SaledCost;
    private String UniqueNum;

    public ClassProducts(Integer num, String str, String str2, String str3, String str4, String str5) {
        this.ProductName = str4;
        this.Photo = str5;
        this.f55Id = num;
        this.Sale = str;
        this.SaledCost = str2;
        this.UniqueNum = str3;
    }

    public String getUniqueNum() {
        return this.UniqueNum;
    }

    public void setUniqueNum(String str) {
        this.UniqueNum = str;
    }

    public String getProductName() {
        return this.ProductName;
    }

    public void setProductName(String str) {
        this.ProductName = str;
    }

    public String getPhoto() {
        return this.Photo;
    }

    public void setPhoto(String str) {
        this.Photo = str;
    }

    public Integer getId() {
        return this.f55Id;
    }

    public void setId(Integer num) {
        this.f55Id = num;
    }

    public String getSale() {
        return this.Sale;
    }

    public void setSale(String str) {
        this.Sale = str;
    }

    public String getSaledCost() {
        return this.SaledCost;
    }

    public void setSaledCost(String str) {
        this.SaledCost = str;
    }
}
