package com.elmoledmol.www;

public class vouchersmodel {
    String title,code,date,percentage;

    public vouchersmodel(String title, String code, String date, String percentage) {
        this.title = title;
        this.code = code;
        this.date = date;
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
