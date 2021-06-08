package com.IOStarter.scansale;

public class ClassBasket {
    private String Name;
    private String cost;
    private Integer count;

    /* renamed from: id */
    private Integer f51id;
    private Integer item_count = Integer.valueOf(1);
    private String photo;
    private String uniqueNum;

    public String getUniqueNum() {
        return this.uniqueNum;
    }

    public Integer getItem_count() {
        return this.item_count;
    }

    public void setItem_count(Integer num) {
        this.item_count = num;
    }

    public void setUniqueNum(String str) {
        this.uniqueNum = str;
    }

    public Integer getId() {
        return this.f51id;
    }

    public void setId(Integer num) {
        this.f51id = num;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer num) {
        this.count = num;
    }

    public String getCost() {
        return this.cost;
    }

    public void setCost(String str) {
        this.cost = str;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public ClassBasket(Integer num, String str, Integer num2, String str2, String str3, String str4) {
        this.f51id = num;
        this.Name = str;
        this.count = num2;
        this.cost = str2;
        this.photo = str3;
        this.uniqueNum = str4;
    }
}
