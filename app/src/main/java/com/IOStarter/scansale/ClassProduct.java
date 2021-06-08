package com.IOStarter.scansale;

import org.json.JSONArray;

public class ClassProduct {
    private String Description;
    private JSONArray Galleries;

    /* renamed from: Id */
    private Integer f54Id;
    private String PartnerName;
    private String ProductName;
    private String Sale;
    private String SaledCost;
    private String count;

    public JSONArray getGalleries() {
        return this.Galleries;
    }

    public void setGalleries(JSONArray jSONArray) {
        this.Galleries = jSONArray;
    }

    public ClassProduct(Integer num, String str, String str2, String str3, String str4, String str5, String str6, JSONArray jSONArray) {
        this.f54Id = num;
        this.Sale = str;
        this.SaledCost = str2;
        this.count = str3;
        this.ProductName = str4;
        this.PartnerName = str5;
        this.Description = str6;
        this.Galleries = jSONArray;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }

    public Integer getId() {
        return this.f54Id;
    }

    public void setId(Integer num) {
        this.f54Id = num;
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

    public String getProductName() {
        return this.ProductName;
    }

    public void setProductName(String str) {
        this.ProductName = str;
    }

    public String getPartnerName() {
        return this.PartnerName;
    }

    public void setPartnerName(String str) {
        this.PartnerName = str;
    }

    public String getDescription() {
        String str = this.Description;
        return str.substring(2, str.length() - 2);
    }

    public void setDescription(String str) {
        this.Description = str;
    }
}
