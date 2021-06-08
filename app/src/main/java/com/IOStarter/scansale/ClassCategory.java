package com.IOStarter.scansale;

public class ClassCategory {

    /* renamed from: Id */
    private Integer f52Id;
    private String Name;

    public ClassCategory(Integer num, String str) {
        this.f52Id = num;
        this.Name = str;
    }

    public Integer getId() {
        return this.f52Id;
    }

    public void setId(Integer num) {
        this.f52Id = num;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String str) {
        this.Name = str;
    }
}
