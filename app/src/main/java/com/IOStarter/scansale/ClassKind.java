package com.IOStarter.scansale;

public class ClassKind {

    /* renamed from: Id */
    private Integer f53Id;
    private String Image;
    private String Name;

    public String getName() {
        return this.Name;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public String getImage() {
        return this.Image;
    }

    public void setImage(String str) {
        this.Image = str;
    }

    public Integer getId() {
        return this.f53Id;
    }

    public void setId(Integer num) {
        this.f53Id = num;
    }

    public ClassKind(Integer num, String str, String str2) {
        this.Name = str;
        this.Image = str2;
        this.f53Id = num;
    }
}
